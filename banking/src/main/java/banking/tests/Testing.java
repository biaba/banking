package banking.tests;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import banking.entities.Account;
import banking.entities.Transaction;

public class Testing {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		Transaction account = om.readValue(new File("src/main/webapp/resources/json/sample.json"), Transaction.class);
		System.out.println(account + "HERE");
		
		Account credit_account = new Account();
		credit_account.setId(1);
		credit_account.setBalance(222);
		credit_account.setType("saving");
		credit_account.setAccountNumber("555");
		
		om.enable(SerializationFeature.INDENT_OUTPUT);
		
		om.writeValue(new File("src/main/webapp/resources/json/sample.json"), credit_account);
		
		Account debit_account = new Account();
		debit_account.setId(11);
		debit_account.setBalance(0);
		debit_account.setType("saving");
		debit_account.setAccountNumber("8885");
		
		om.enable(SerializationFeature.INDENT_OUTPUT);
		
		om.writeValue(new File("src/main/webapp/resources/json/sample.json"), debit_account);
		
		Transaction tx = new Transaction();
		tx.setId(4);
		tx.setAmount(123);
		tx.setStatus("completed");
		tx.setDate(null);
		tx.setCredit_account(credit_account);
		tx.setDebit_account(debit_account);
		
		om.writeValue(new File("src/main/webapp/resources/json/sample.json"), tx);
		
		
	}

}
