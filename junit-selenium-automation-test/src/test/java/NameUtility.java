import org.junit.Assert;
import org.testng.annotations.Test;

public class NameUtility {
	public static String convertNameToInitials(String name) {
		int firstSpace = name.indexOf(" ");
		int lastSpace = name.lastIndexOf(" ");
		//Initializing variables
		String initials;
		String firstName = "";
		String lastName = "";
		String middleName = "";
		// Check if after first name contains any space
		if (firstSpace <= 0) {
			firstName = name;
		} else {
			firstName = name.substring(0, firstSpace);
		}
		// Check if last name contains any space at all
		if (firstSpace != lastSpace) {
			middleName = name.substring(firstSpace+1, lastSpace);
		}
		//This condition is needed if middle name is not present
		if (lastSpace > 0) {
			lastName = name.substring(lastSpace+1, name.length());
		}
		// Conditions to do output only if middle name and last name is valid. 
		if (middleName.length() > 0 && lastName.length() > 0) {
			initials = firstName.substring(0,1) + "." + middleName.substring(0,1) + "." + lastName.substring(0,1) + ".";
		} else if (middleName.length() <= 0 && lastName.length() > 0) {
			initials = firstName.substring(0,1) + "." + lastName.substring(0,1) + ".";
		} else {
			initials = firstName.substring(0,1) + ".";
		}
		return initials;
	}
	@Test
	void testBrunoMars() {
		Assert.assertEquals("B.M.", convertNameToInitials("Bruno Mars"));
	}
	@Test
	void testDaveMJones() {
		Assert.assertEquals("D.M.J.", convertNameToInitials("Dave M Jones"));
	}
	@Test
	void testMichaelSmith() {
		Assert.assertEquals("M.", convertNameToInitials("MichaelSmith"));
	}
}
