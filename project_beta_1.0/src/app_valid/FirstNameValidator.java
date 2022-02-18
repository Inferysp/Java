package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstNameValidator extends AbstractValidationClass {
	
	private static String patternString = "(\\w{0})?([A-Z��ƥ�ʣ��][A-Za-z�����깜��ƥ�ʣ��]{2,29}\s*)?";
	private static Pattern patternObject = Pattern.compile(patternString);

	public boolean validate(Object arg) {
		Matcher matcherObject = patternObject.matcher((CharSequence) arg);
		if (matcherObject.matches()) {
			return true ;
		} else {
			return false;
		}
	}
	
	public boolean validateString(String arg) {
		Matcher matcherObject = patternObject.matcher((CharSequence) arg);
		if (matcherObject.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
