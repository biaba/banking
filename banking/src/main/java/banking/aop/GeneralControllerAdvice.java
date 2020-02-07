package banking.aop;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;


import banking.exceptions.NoUser;
import banking.exceptions.UserNotFoundException;


@ControllerAdvice
public class GeneralControllerAdvice {
	
// Removes empty spaces in registration form
		@InitBinder
		public void initBinder(WebDataBinder dataBinder) {
			StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
			dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		}
		
// Response if Customer not found
		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<NoUser> userNotFoundHandling(UserNotFoundException e){
			
			NoUser noUser = new NoUser();
			noUser.setMessage(e.getMessage());
			noUser.setStatus(HttpStatus.NOT_FOUND.value());
			noUser.setTime(System.currentTimeMillis());			
				
			return new ResponseEntity<>(noUser, HttpStatus.NOT_FOUND);
		}
	
// Replaces standard error page
		@ExceptionHandler(Exception.class)
		public String customizedExceptionHandler(Model model, Exception e) {
			String errmsg = e.toString();
			model.addAttribute("errmsg",  "An error ocurred"+errmsg);
			
			return "customError";
		}
		
	

}
