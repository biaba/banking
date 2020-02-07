package banking.dtos;


import javax.validation.constraints.NotNull;

public class CrmUser {
	
	@NotNull(message="field required")
	private String userName;
	
	@NotNull(message="field required")
	private String firstName;

	@NotNull(message="field required")
	private String lastName;
	
	@NotNull(message="field required")
	private String password;
	
	@NotNull(message="field required")
	private String matchingPassword;

	@NotNull(message="field required")
	private Integer personalId;

	@NotNull(message="field required")
	private String email;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "CrmUser [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", personalId=" + personalId + ", email=" + email + "]";
	}
	
	
	

}
