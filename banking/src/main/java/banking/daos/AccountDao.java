package banking.daos;

import java.util.List;

import banking.entities.Account;


public interface AccountDao {
	
	public void saveAccount(Account account) throws Exception;
	
	public void updateAccount(Account account);
	
	public Account readAccount(int id);
	
	public Account getAccountByAccountNumber(String accountNumber);
	
	public List<Account> readAllAccounts();
	
	public void deleteAccount(int id);

}
