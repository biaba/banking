package banking.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnderEighteenValidator implements ConstraintValidator<UnderEighteen, Integer> {

	Integer checkingNumber;

	@Override
	public void initialize(UnderEighteen constraintAnnotation) {
		this.checkingNumber = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		boolean isValid;
		if (value >= checkingNumber) {
			isValid=false;
		} else {
			isValid=true;
		}
		return isValid;
	}
}
