package banking.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true, value= {"users"})
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// current or saving
	private String type;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Min(value=0, message="Overdraft not allowed")
	private int balance;
	
	@ManyToMany(mappedBy="accounts")
	private Set<User> users = new HashSet<>();
	
	// Customer field to look up in DB by opening Account and adding to Set if exists
	// this field is not used for entity mapping
	@Transient
	private int userId;
	
	public Account() {
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void addUser(User user) {
		this.users.add(user);
		user.getAccounts().add(this);
		
	}
	
	public void deleteUser(User user) {
		this.users.remove(user);
		user.getAccounts().remove(this);
	}
	
	

	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ "]";
	}
	
	

}
