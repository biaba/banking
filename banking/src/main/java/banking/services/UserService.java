package banking.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import banking.dtos.CrmUser;
import banking.entities.Account;
import banking.entities.User;

public interface UserService extends UserDetailsService {
	
	void saveTheUser(CrmUser crmUser);
	
	User readTheUser(int id);
	
	User readTheUserByName(String userName);
	
	List<User> readTheAllUsers();
	
	void deleteTheUser(int id);
	
	public List<Account> getAllUserAccounts(String userName);
	
	public User checkIfExists(String userName);

}
