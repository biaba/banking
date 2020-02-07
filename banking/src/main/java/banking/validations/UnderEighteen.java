package banking.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = { UnderEighteenValidator.class })
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UnderEighteen {
	
	public int value() default 18;
	
	public String message() default "must be under 18";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {}; 

}
