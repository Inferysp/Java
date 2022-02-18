package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyNameValidation extends AbstractValidationClass {
	
	private static String patternString = "";
	private static Pattern patternObject = Pattern.compile(patternString);

	public boolean validate(Object arg) {
		Matcher matcherObject = patternObject.matcher((CharSequence) arg);
		if (matcherObject.matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validateString(String arg) {
		Matcher matcherObject = patternObject.matcher(arg);
		if (matcherObject.matches()) {
			return false;
		} else {
			return true;
		}
	}
}
