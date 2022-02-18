package app_valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SexValidator extends AbstractValidationClass {

	private static Pattern patternMaleObj = Pattern.compile("(\\w{0})?(male)?");
	private static Pattern patternFemaleObj = Pattern.compile("(\\w{0})?(female)?");

	public boolean validate(Object arg) {
		Matcher matcherMale = patternMaleObj.matcher((CharSequence) arg);
		Matcher matcherfemale = patternFemaleObj.matcher((CharSequence) arg);

		if (matcherMale.matches()) {
			return true;
		} else if (matcherfemale.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validateString(String arg) {
		Matcher matcherMale = patternMaleObj.matcher((CharSequence) arg);
		Matcher matcherfemale = patternFemaleObj.matcher((CharSequence) arg);

		if (matcherMale.matches()) {
			return true;
		} else if (matcherfemale.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
