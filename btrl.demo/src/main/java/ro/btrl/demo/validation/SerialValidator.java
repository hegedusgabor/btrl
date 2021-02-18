package ro.btrl.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SerialValidator implements ConstraintValidator<ValidSerial, String> {


		private Pattern pattern;
		private Matcher matcher;
		private static final String SERIAL_PATTERN = "^([A-Za-z]{2})$";

		@Override
		public boolean isValid(final String serial, final ConstraintValidatorContext context) {
			pattern = Pattern.compile(SERIAL_PATTERN);
			if (serial == null) {
				return false;
			}
			matcher = pattern.matcher(serial);
			return matcher.matches();
		}
	

}
