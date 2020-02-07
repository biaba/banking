package banking.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value= {"roles", "accounts"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "first_name")
	public String firstName;

	@Column(name = "last_name")
	private String lastName;

	@NotNull
	private String password;

	@Column(name = "personal_id")
	@NotNull
	private Integer personalId;

	private String email;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles = new ArrayList<>();

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "user_account", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "account_id") })
	private Set<Account> accounts = new HashSet<>();

	public User() {
	}

	public User(@NotNull String userName, @NotNull String firstName, @NotNull String lastName, @NotNull String password,
			@NotNull(message = "must contain 3 digits") @Min(value = 3, message = "must contain 3 digits") @Max(value = 3, message = "must contain 3 digits") Integer personalId,
			String email) {
		char firstLetter = firstName.charAt(0);
		firstLetter = Character.toUpperCase(firstLetter);
		firstName = firstName.replace(firstName.charAt(0), firstLetter);
		this.firstName = firstName;

		char fLetter = lastName.charAt(0);
		fLetter = Character.toUpperCase(fLetter);
		lastName = firstName.replace(lastName.charAt(0), fLetter);
		this.lastName = lastName;
		this.password = password;
		this.personalId = personalId;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getPersonalId() {
		return personalId;
	}

	public void setPersonalId(Integer personalId) {
		this.personalId = personalId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public int getId() {
		return this.id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "RestUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", personalId=" + personalId
				+ ", email=" + email + "]";
	}

// helper methods to add/remove account
	public void addAccount(Account account) {
		this.accounts.add(account);
		account.getUsers().add(this);
	}

	public void deleteAccount(Account account) {
		this.accounts.remove(account);
		account.getUsers().remove(this);
	}

// helper methods to add/remove role	
	public void addRole(Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}

	public void deleteRole(Role role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}

}
