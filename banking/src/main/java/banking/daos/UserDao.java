package banking.daos;

import java.util.List;

import banking.entities.Account;
import banking.entities.User;

public interface UserDao {
	
	void saveUser(User user);
	
	User readUser(int id);
	
	User readUserByName(String userName);
	
	List<User> readAllUsers();
	
	void deleteUser(int id);
	
	public List<Account> getUserAccounts(String userName);
	

}
