package ro.btrl.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<ValidNumber, String>{

	private Pattern pattern;
	private Matcher matcher;
	private static final String NUMBER_PATTERN = "^([0-9]{6})$";

	@Override
	public boolean isValid(final String nr, final ConstraintValidatorContext context) {
		pattern = Pattern.compile(NUMBER_PATTERN);
		if (nr == null) {
			return false;
		}
		matcher = pattern.matcher(nr);
		return matcher.matches();
	}
}
