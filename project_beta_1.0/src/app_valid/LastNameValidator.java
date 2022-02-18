package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastNameValidator extends AbstractValidationClass {
	
	private static String patternString = "(\\w{0})?(([A-Z¯Æ¥ŒÊ£ÓÑ][A-Za-z¿Ÿæñó³ê¹œ¯Æ¥ŒÊ£ÓÑ]{3,30}\s*))?";
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
