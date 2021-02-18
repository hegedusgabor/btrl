package ro.btrl.demo.validation;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, Date>{

	
	@Override
	public boolean isValid(final Date value, final ConstraintValidatorContext context) {
		return  value != null && value.before(new Date());
	}

}
