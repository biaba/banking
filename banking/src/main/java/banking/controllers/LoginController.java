package banking.controllers;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import banking.daos.AccountDao;
import banking.daos.RoleDao;
import banking.daos.UserDao;

@Controller
public class LoginController {

	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	AccountDao accountDao;

// landing page - access to everybody	
	@GetMapping("/")
	public String login() {
		return "landing";
	}
	
// login page
	@GetMapping("/login")
	public String loginForm(){
		return "login";
	}

// login processing redirect to "success" page	
	@GetMapping("/success")
	public String success(Model model, Authentication auth) {
	
		model.addAttribute("userName", auth.getName());
		return "success";
	}
	
// logout page
		@GetMapping("/logout")
		public String logout(){
			return "landing";
		}

	
// access denied custom page
	@GetMapping("accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

}
