package banking.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 652282322427475005L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
