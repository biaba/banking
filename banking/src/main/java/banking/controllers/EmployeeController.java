package banking.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import banking.daos.AccountDao;
import banking.daos.TransactionDao;
import banking.dtos.CrmUser;
import banking.entities.Account;
import banking.entities.Transaction;
import banking.entities.User;
import banking.exceptions.UserNotFoundException;
import banking.services.UserService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	UserService userService;

	@Autowired
	AccountDao accountDao;

	@Autowired
	TransactionDao transactionDao;

// employee main page
	@GetMapping("/main")
	public String admin(Principal principal, Model model) {
		model.addAttribute("employeeName", principal.getName());
		return "employee";
	}

// New Customer registration page
	@GetMapping("/register")
	public String registration(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		return "newCustomerRegistration";
	}
	
	// ajax call to check is username exists
	@RequestMapping("/checkUserName")
	public @ResponseBody String checkIfUserNameExists(@RequestParam("userName") String userName) {
		User user = userService.checkIfExists(userName);
		System.out.println("HERE "+ user);
		if(user==null) {
			return "";
		} else {
			return "exists";
		}
	}

	// New Customer registration form processing
	@PostMapping("/register/processing")
	public String processingCustomerRegistration(@Valid @ModelAttribute("crmUser") CrmUser crmUser, BindingResult br,
			Model model) {
		if (br.hasErrors()) {
			System.out.println("NUMBER OF ERRORS" + br.getErrorCount());
			System.out.println("MESSAGE" + br.toString());
			return "newCustomerRegistration";
		} else {
			userService.saveTheUser(crmUser);
			model.addAttribute("successMssg", "Customer has been succesfully registered");
			model.addAttribute("actionResult", "first name: " + crmUser.getFirstName() + " last name: "
					+ crmUser.getLastName() + " email: " + crmUser.getEmail() + " username: " + crmUser.getUserName());
			return "employee";
		}
	}

// New Account Opening
	@GetMapping("/open-account")
	public String openingAccount(Model model) {
		model.addAttribute("account", new Account());
		return "newAccountRegistration";
	}

	// Finding a Customer when opening Account - ajax
	@RequestMapping("/register/findCustomer")
	public @ResponseBody String findCustomer(@RequestParam("userName") String userName) {
		User user = null;
		String result = new String();

		try {
			user = userService.readTheUserByName(userName);
			result = Integer.toString(user.getId());

		} catch (UsernameNotFoundException | NoResultException e) {
			result = "no user found";
			return result;

		}

		return result;

	}

	// New Account opening processing
	@PostMapping("/open-account/processing")
	public String processingAccountOpening(@ModelAttribute("account") Account account, BindingResult br, Model model)
			throws Exception {
		if (br.hasErrors()) {
			// model.addAttribute("noSuccessMsg", br.toString());
			return "newAccountRegistration";
		} else {
			accountDao.saveAccount(account);
			model.addAttribute("successMssg", "Account has been opened");
			model.addAttribute("actionResult",
					account.getAccountNumber() + " deposit: " + account.getBalance() + " type: " + account.getType());
			return "employee";
		}
	}

// Deposit money on Customers account
	@GetMapping("/deposit")
	public String depositMoney(Model model) {
		model.addAttribute("transaction", new Transaction());

		return "deposit";
	}

	// Processing deposit
	@PostMapping("/deposit/processing")
	public String depositMoneyProcessing(@ModelAttribute("transaction") Transaction transaction, Model model)
			throws Exception {

		Account account = accountDao.getAccountByAccountNumber(transaction.getString_credit_account());
		account.setBalance(account.getBalance() + transaction.getAmount());

		accountDao.updateAccount(account);
		model.addAttribute("successMssg", "Deposit has been made");
		model.addAttribute("actionResult", "Money on Account: " + transaction.getString_credit_account() + " is: "
				+ accountDao.getAccountByAccountNumber(transaction.getString_credit_account()).getBalance());
		return "employee";
	}

// Withdraw money from Customers account
	@GetMapping("/withdraw")
	public String withdrawMoney(Model model) {
		model.addAttribute("transaction", new Transaction());

		return "withdraw";
	}

	// Processing withdrawal
	@PostMapping("/withdraw/processing")
	public String withdrawMoneyProcessing(@ModelAttribute("transaction") Transaction transaction, Model model) {

		Account account = accountDao.getAccountByAccountNumber(transaction.getString_debit_account());
		if (account.getBalance() < transaction.getAmount()) {
			model.addAttribute("transactionMssg", "not enough funds. Available balance " + account.getBalance());
			return "employee";
		} else {

			account.setBalance(account.getBalance() - transaction.getAmount());
			accountDao.updateAccount(account);
			model.addAttribute("successMssg", "Money has been withdrawn");
			model.addAttribute("actionResult", "Money on Account: " + transaction.getString_debit_account() + " is: "
					+ accountDao.getAccountByAccountNumber(transaction.getString_debit_account()).getBalance());

			return "employee";

		}
	}

// Transferring funds main page
	@GetMapping("/transfer-funds")
	public String employeeTransferFunds(Model model) {
		model.addAttribute("transaction", new Transaction());
		return "empTransferFunds";
	}

// Processing fund transfer
	@PostMapping("/transfer-funds/processing")
	public String transferProcessing(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult br,
			Model model) {
		if (br.hasErrors()) {
			model.addAttribute("errmsg", "please, check all fields carefully");
			return "empTransferFunds";
		}
		transaction.setCredit_account(accountDao.getAccountByAccountNumber(transaction.getString_credit_account()));
		transaction.setDebit_account(accountDao.getAccountByAccountNumber(transaction.getString_debit_account()));
		transactionDao.saveTx(transaction);
		model.addAttribute("transactionMssg", "Transaction status: " + transaction.getStatus());
		model.addAttribute("actionResult",
				transaction.getAmount() + " from Account: " + transaction.getDebit_account().getAccountNumber()
						+ " to Account: " + transaction.getCredit_account().getAccountNumber());

		return "employee";
	}

// Ajax - looking up for Account when transferring funds/depositing/withdrawing
	@RequestMapping(value = { "/transfer_funds/findAccount", "/search-transactions/findAccount", "/findAccount" })
	public @ResponseBody String findAccount(@RequestParam("userName") String userName) {
		Account account = null;
		String result = new String();

		try {
			List<Account> accountList = new ArrayList<Account>(userService.readTheUserByName(userName).getAccounts());
			// getting the first account from customers accounts
			account = accountList.get(0);
			result = account.getAccountNumber();

		} catch (NoResultException e) {
			result = "no account found";
			return result;

		}

		return result;

	}

// Searching Transactions main page
	@GetMapping("/search-transactions")
	public String searchTransactions() {

		return "empSearchTransactions";
	}

	// Return Transactions for Customer Account within last year - json
	@GetMapping(path = "/search-transactions/account", produces = "application/json")
	public @ResponseBody List<Transaction> getTxByUser(@RequestParam("string_account") String accountNumber) {
		return transactionDao.getAllTx(accountDao.getAccountByAccountNumber(accountNumber));
	}

	// Return Transactions for Customer Account between two dates
	@GetMapping(path = "/search-transactions/account-by-dates")
	public String getTxBetweenDates(@RequestParam("string_account") String accountNumber,
			@RequestParam("from") String from, @RequestParam("to") String to, Model model) {
		List<Transaction> txList = transactionDao.getTxByDate(from, to,
				accountDao.getAccountByAccountNumber(accountNumber));
		model.addAttribute("txList", txList);
		return "empSearchTransactions";
	}
	
	
// Finding Customer details by Id - json
	@GetMapping(path = "/users/{id}", produces = "application/json")
	public @ResponseBody User getCustomerById(@PathVariable int id) {
		User user = userService.readTheUser(id);
		if (user == null) {
			throw new UserNotFoundException("NO USER REALLY");
		}
		return user;
	}

}
