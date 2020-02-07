package banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import banking.entities.User;
import banking.services.UserService;

@Controller
public class RestClientController {
	

	@Autowired
	UserService userService;
	
// Serves incoming REST client requests
	// For REST client - requesting all Customers
		@GetMapping(path = "/customers", produces = "application/json")
		public @ResponseBody List<User> getAllUsers() {
			return userService.readTheAllUsers();
		}

}
