package banking.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banking.entities.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	@Transactional
	public void saveRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);

	}

	@Override
	@Transactional
	public Role readRole(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Role.class, id);
	}

	@Override
	@Transactional
	public List<Role> readAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Role> query = session.createQuery("from Role", Role.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteRole(int id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, id);
		session.delete(role);

	}


}
