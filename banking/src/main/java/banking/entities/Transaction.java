package banking.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true, value= {"id"})
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Min(value=1, message="Transactions minimal value is 1 EUR")
	private Integer amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
		
	// pending, completed, cancelled
	// Pending - when created. Completed or cancelled - when checked if debiting account is possible
	private String status;
	
	@OneToOne
	private Account credit_account;

// field used to get from form in the String format and convert to Account object
	@Transient
	private String string_credit_account;
	
	@OneToOne
	private Account debit_account;

// field used to get from form in the String format and convert to Account object
	@Transient
	private String string_debit_account;

	public Transaction() {
	}

	public Transaction(@NotNull @Min(value=1, message="Transactions minimal value is 1 EUR") Integer amount, Account credit_account, Account debit_account) {
		this.amount = amount;
		this.credit_account = credit_account;
		this.debit_account = debit_account;
		this.status="pending";
		this.date= new Date();
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Account getCredit_account() {
		return credit_account;
	}

	public void setCredit_account(Account credit_account) {
		this.credit_account = credit_account;
	}

	public Account getDebit_account() {
		return debit_account;
	}

	public void setDebit_account(Account debit_account) {
		this.debit_account = debit_account;
	}
	

	public String getString_credit_account() {
		return string_credit_account;
	}

	public void setString_credit_account(String string_credit_account) {
		this.string_credit_account = string_credit_account;
	}

	public String getString_debit_account() {
		return string_debit_account;
	}

	public void setString_debit_account(String string_debit_account) {
		this.string_debit_account = string_debit_account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", status=" + status
				+ ", credit_account=" + credit_account + ", debit_account=" + debit_account + "]";
	}
	


}
