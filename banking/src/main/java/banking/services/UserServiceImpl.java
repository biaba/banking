package banking.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import banking.daos.RoleDao;
import banking.daos.UserDao;
import banking.dtos.CrmUser;
import banking.entities.Account;
import banking.entities.Role;
import banking.entities.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired 
	BCryptPasswordEncoder passwordEncoder;

// Overriding UserService method for Spring Security implementation
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;		
		try {
			user = userDao.readUserByName(username);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e + " Please, enter a valid username and password");
		}
		
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public User checkIfExists(String userName){
		
		return userDao.readUserByName(userName);
	}



	@Override
	@Transactional
	public void saveTheUser(CrmUser crmUser) {
		User user = new User();
		user.setUserName(crmUser.getUserName());
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setPersonalId(crmUser.getPersonalId());
		user.setEmail(crmUser.getEmail());	
		Role role =roleDao.readRole(1);
		user.addRole(role);
		roleDao.saveRole(role);
		
		userDao.saveUser(user);

	}

	@Override
	@Transactional
	public User readTheUser(int id) {
		return userDao.readUser(id);
	}

	@Override
	@Transactional
	public User readTheUserByName(String userName) {
		return userDao.readUserByName(userName);
	}

	@Override
	@Transactional
	public List<User> readTheAllUsers() {
		return userDao.readAllUsers();
	}

	@Override
	@Transactional
	public void deleteTheUser(int id) {
		userDao.deleteUser(id);
	}
	
	@Override
	@Transactional
	public List<Account> getAllUserAccounts(String userName){
		return userDao.getUserAccounts(userName);
	}

}
