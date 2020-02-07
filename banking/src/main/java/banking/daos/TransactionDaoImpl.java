package banking.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banking.entities.Account;
import banking.entities.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	
	@Autowired
	SessionFactory sessionFactory; 
	
	@Autowired	
	AccountDao accountDao;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	// reading date string -> converting to date
	public static Date stringToDate(String dateString) throws ParseException {
		Date theDate = formatter.parse(dateString);

		return theDate;
	}

	@Override
	@Transactional
	public void saveTx(Transaction tx) {
		Session session = sessionFactory.getCurrentSession();
		Date date = new Date();
		
		tx.setDate(date);
		
		// checking if funds are enough
		
		if(tx.getDebit_account().getBalance()<tx.getAmount()) {
			tx.setStatus("rejected");
			session.saveOrUpdate(tx);
		} else {
			Account debit_account = tx.getDebit_account();
			Account credit_account = tx.getCredit_account();
			debit_account.setBalance(tx.getDebit_account().getBalance()-tx.getAmount());
			credit_account.setBalance(tx.getCredit_account().getBalance()+ tx.getAmount());
			tx.setStatus("completed");
			session.saveOrUpdate(debit_account);
			session.saveOrUpdate(credit_account);
			session.saveOrUpdate(tx);
			
		}
		

	}

	@Override
	@Transactional
	public Transaction readTx(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Transaction.class, id);
	}

	@Override
	@Transactional
	public List<Transaction> readAllTransactions() {
		Session session = sessionFactory.getCurrentSession();
		Query<Transaction> query = session.createQuery("from Transaction", Transaction.class);
		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteTransaction(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.get(Transaction.class, id);
		session.delete(tx);
	}
	
// All Transactions for Account starting from year X
	@Override
	@Transactional
	public List<Transaction> getTxFromYear(int year, Account account) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Transaction as t WHERE (t.credit_account = :owner OR t.debit_account = :owner) AND t.date > :date";
		Query<Transaction> query = session.createQuery(hql, Transaction.class);
		query.setParameter("owner", account);
	
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.YEAR, year-currentYear);
		Date period = cal.getTime();
		
		query.setParameter("date", period);
		return query.getResultList();
	}
	
// All Transactions 
// for Account between two Dates
		@Override
		@Transactional
		public List<Transaction> getTxByDate(String from, String to, Account account) {
			Session session = sessionFactory.getCurrentSession();
			
			String hql = "FROM Transaction as t WHERE (t.credit_account = :owner OR t.debit_account = :owner) AND (t.date > :fromDate AND t.date< :toDate)";
			Query<Transaction> query = session.createQuery(hql, Transaction.class);
			query.setParameter("owner", account);
			
			
			try {
				Date fromDate = stringToDate(from);
				Date toDate = stringToDate(to);
				query.setParameter("toDate", toDate);
				query.setParameter("fromDate", fromDate);
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			return query.getResultList();
		}

// All Transactions between
// Owner / second part - last year
	@Override
	@Transactional
	public List<Transaction> getTxBySecondPart(Account owner, Account secondPart) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Transaction as t WHERE (t.credit_account = :owner AND t.debit_account = :secondPart) OR (t.credit_account= :secondPart AND t.debit_account= :owner) AND t.date < :date ";
		Query<Transaction> query = session.createQuery(hql, Transaction.class);
		query.setParameter("owner", owner);		
		query.setParameter("secondPart", secondPart);
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		query.setParameter("owner", owner);	
		query.setParameter("date", cal.getTime());
		return query.getResultList();
	}

// All transactions 
// for account - last year from the current date
	@Override
	@Transactional 
	public List<Transaction> getAllTx(Account owner) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Transaction as t WHERE (t.credit_account = :owner OR t.debit_account = :owner) AND t.date < :date";
		Query<Transaction> query = session.createQuery(hql, Transaction.class);
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		query.setParameter("owner", owner);	
		query.setParameter("date", cal.getTime());
		return query.getResultList();
	}

}
