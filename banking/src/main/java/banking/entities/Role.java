package banking.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(mappedBy="roles")
	Collection<User> users;
	
	public Role() {
		
	}

	public Role(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	
	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}
	

// helper methods to add/remove RestUser


	public void addUser(User user) {
		System.out.println("ADDING USER INSIDE ROLE_ROLE addUser() "+ user);
		this.users.add(user);
		System.out.println("ADDING Role INSIDE RestUser addRole() "+ user.getRoles());
		user.getRoles().add(this);
	}
	
	public void deleteUser(User user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}
	
	
	

}
