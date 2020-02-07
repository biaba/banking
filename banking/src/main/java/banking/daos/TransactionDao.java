package banking.daos;

import java.util.List;

import banking.entities.Account;
import banking.entities.Transaction;

public interface TransactionDao {
	
	public void saveTx(Transaction tx);

	
	public Transaction readTx(int id);

	
	public List<Transaction> readAllTransactions();

	
	public void deleteTransaction(int id);
	
	public List<Transaction> getTxByDate(String from, String to, Account account);
	
	public List<Transaction> getTxFromYear(int year, Account debitedAccount);
	
	public List<Transaction> getTxBySecondPart(Account owner, Account secondPart);
	
	public List<Transaction> getAllTx(Account owner);
}
