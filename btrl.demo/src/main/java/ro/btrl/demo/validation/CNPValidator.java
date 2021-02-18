package ro.btrl.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CNPValidator implements ConstraintValidator<ValidCNP, String>{

	private Pattern pattern;
	private Matcher matcher;
	private static final String CNP_PATTERN = "^([0-9]{13})$";

	@Override
	public boolean isValid(final String cnp, final ConstraintValidatorContext context) {
		pattern = Pattern.compile(CNP_PATTERN);
		if (cnp == null) {
			return false;
		}
		matcher = pattern.matcher(cnp);
		return matcher.matches();
	}

}
