package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NipValidator extends AbstractValidationClass {
	
	private static String patternString = "(\\d{3}[-]\\d{2}[-]\\d{2}[-]\\d{3})?";
	private static Pattern patternObject = Pattern.compile(patternString);

	public boolean validate(Object arg) {
		Matcher matcherObject = patternObject.matcher((CharSequence) arg);
		if (matcherObject.matches()) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean validateString(String arg) {
		Matcher matcherObject = patternObject.matcher(arg);
		if (matcherObject.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
