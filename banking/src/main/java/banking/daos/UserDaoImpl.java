package banking.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banking.entities.Account;
import banking.entities.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory; 

	@Override
	@Transactional
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("SAVING USER "+ user);
		session.saveOrUpdate(user);

	}

	@Override
	public User readUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}
	
	@Override
	public User readUserByName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("From User where userName = :userName", User.class);
		query.setParameter("userName", userName);
		int id = query.getSingleResult().getId();
		return session.get(User.class, id);
	}

	@Override
	public List<User> readAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User", User.class);
		
		return query.getResultList();
	}

	@Override
	public void deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		session.delete(user);
	}
	
	@Override
	public List<Account> getUserAccounts(String userName){
		
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("From User where userName = :userName", User.class);
		query.setParameter("userName", userName);
		Set<Account> accSet =  query.getSingleResult().getAccounts();
		List<Account> accounts = new ArrayList<>(accSet);
		System.out.println(accounts + "HERE HERE HERE");
		return accounts;
		
	}

}
