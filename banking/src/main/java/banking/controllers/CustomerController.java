package banking.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import banking.daos.AccountDao;
import banking.daos.TransactionDao;
import banking.entities.Account;
import banking.entities.Transaction;
import banking.services.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionDao transactionDao;

// customer main page
	@GetMapping("/main")
	public String customerMain(Principal principal, Model model) {
		model.addAttribute("customerName", principal.getName());
		return "customer";
	}

// page to see balances
	@GetMapping("/balances")
	public String seeBalances(Principal principal , Model model) {
		
		List<Account> accountList = userService.getAllUserAccounts(principal.getName());
		model.addAttribute("userName", principal.getName());
		model.addAttribute("accounts", accountList);
		return "custBalances";
	}

// page to search transactions
	
	@GetMapping("/transactions/search")
	public String seeTransactions(Model model, Principal principal) {
		model.addAttribute("userName", principal.getName());
		return "custSearchTransactions";
	}
	
	// search transactions displayed
	@GetMapping(path = "/transactions/search1")
	public String getTx(@RequestParam("string_account") String accountNumber, @RequestParam(value="from", required=false) String from, @RequestParam(value="to", required=false) String to, Model model, Principal principal) {
		List<Transaction> txList = null;
		if(from==null|| to==null){
			txList = transactionDao.getAllTx(accountDao.getAccountByAccountNumber(accountNumber));
		} else {
			txList = transactionDao.getTxByDate(from, to, accountDao.getAccountByAccountNumber(accountNumber));
		}	
			model.addAttribute("txList", txList);
			model.addAttribute("userName", principal.getName());
			
			return "custSearchTransactions";
		}
	
// ajax call to return all Accounts to choose from
	@GetMapping(value ="/transactions/findUserAccounts", produces="application/json")
	public @ResponseBody List<Account> findUserAccounts(@RequestParam("userName") String userName){

		return userService.getAllUserAccounts(userName);		
	}


}
