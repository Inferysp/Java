package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalPhoneValidation extends AbstractValidationClass {
	
	private static String patternString = "(\\w{0})?((\\d{3}[- .]?){2}\\d{3})?";
	private static Pattern patternObject = Pattern.compile(patternString);

	public boolean validate(Object arg) {
		Matcher matcherObject = patternObject.matcher((CharSequence) arg);
		if (matcherObject.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateString(String arg) {
		Matcher matcherObject = patternObject.matcher(arg);
		if (matcherObject.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
}
