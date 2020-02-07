package banking.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banking.entities.Account;
import banking.services.UserService;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserService userService;
	
	@Override
	@Transactional
	public void saveAccount(Account account) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		
	// create Account number
		Query<Account> query = session.createQuery("FROM Account ORDER BY id DESC", Account.class);
		query.setMaxResults(1);
		String lastAccount	= query.getSingleResult().getAccountNumber();
		for(int x=9; x<16; x++){
			if(Character.toString(lastAccount.charAt(x)).equals(Integer.toString(0))){ 
			continue;
			} else{
				String prefix = lastAccount.substring(0, Math.min(lastAccount.length(), x));
				String stringNumbers = lastAccount.substring(x);
				int intNumbers = Integer.parseInt(stringNumbers);
				if(intNumbers==99999999){
					throw new Exception("Account numbers has reached a limit");
				}
				intNumbers++;
				String newStringNumbers = Integer.toString(intNumbers);
				String newAccountNumber = prefix.concat(newStringNumbers);
				account.setAccountNumber(newAccountNumber);
	// assign Customer to Account
				System.out.println("CAN I GET USER? "+ account.getAccountNumber());
				System.out.println("CAN I GET USER? "+ account.getType());
				System.out.println("CAN I GET USER? "+ account.getUserId());
				account.addUser(userService.readTheUser(account.getUserId()));
				
				
				break;
			}

		}
		
		session.save(account);

	}
	
	@Override
	@Transactional
	public void updateAccount(Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.update(account);
	}

	@Override
	@Transactional
	public Account readAccount(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Account.class, id);
	}


	@Override
	@Transactional
	public Account getAccountByAccountNumber(String accountNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<Account> query = session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class);
		query.setParameter("accountNumber", accountNumber);
		Account account = query.getSingleResult();
		
		return account;
	}
	@Override
	@Transactional
	public List<Account> readAllAccounts() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Account> query = session.createQuery("from Account", Account.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteAccount(int id) {
		Session session = sessionFactory.getCurrentSession();
		Account account = session.get(Account.class, id);
		session.delete(account);

	}

}
