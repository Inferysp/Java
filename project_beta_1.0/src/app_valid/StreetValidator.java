package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreetValidator extends AbstractValidationClass {
	
	private static String patternString = "(\\w{0})?"
			+ "("
			+ "(ul.\\s)?"
			+ "(\\w{1,2}\\.\\s)?"
			+ "(\\d{1,4}\u002D\\w{1,30}\\s)?"
			+ "([A-Z¯Æ¥ŒÊ£ÓÑ][A-Za-z¿Ÿæñó³ê¹œ¯Æ¥ŒÊ£ÓÑ]{3,30}\s{1})?"
			+ "([A-Z¯Æ¥ŒÊ£ÓÑ][A-Za-z¿Ÿæñó³ê¹œ¯Æ¥ŒÊ£ÓÑ]{3,30}\\s*)?"
			+ ")?";
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