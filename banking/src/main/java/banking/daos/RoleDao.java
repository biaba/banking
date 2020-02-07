package banking.daos;

import java.util.List;

import banking.entities.Role;

public interface RoleDao {
	
	
	public void saveRole(Role tx);

	
	public Role readRole(int id);

	
	public List<Role> readAllRoles();

	
	public void deleteRole(int id);

}
