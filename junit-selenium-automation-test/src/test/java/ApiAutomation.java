import org.junit.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Splitter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashSet;
import java.util.List;


public class ApiAutomation {
	Response testToCheckAPIAutomation() {
		Response response = given()
				.queryParam("drilldowns", "State").queryParam("measures","Population").queryParam("year","latest"). //parameters
				when()
				.get("https://datausa.io/api/data"). // Endpoint
				then()
				.contentType(ContentType.JSON).extract().response();
		return response;


	}
	// Helper Function to check if fist element of list doesn't match with any other element of list.	
	public boolean verifyAllEqualUsingALoop(List<String> list) {
		for (String s : list) {
			//System.out.println(s);
			if (!s.equals(list.get(0)))
				return false;
		}
		return true;
	}

	@Test
	void yearIsSame() {
		// validates that "year" is the same in all elements of the "data" array
		String year = testToCheckAPIAutomation().jsonPath().getString("data['Year']"); //saving all the Years as string in variable year
		year = year.replaceAll("\\[", "").replaceAll("\\]","");//removing square brackets from beginning and end of string variable
		List<String> convertedYearList = Splitter.on(",").trimResults().splitToList(year);//converting String to String List
		boolean yearBollean = verifyAllEqualUsingALoop(convertedYearList); //function returning the value if first element of list matches other elements, return true if Yes else returns false
		Assert.assertEquals(yearBollean, true); // Assert to check if value matches expected value of true
	}
	@Test
	void stateIsUnique() {
		//validates that each state name is unique in "data" array
		String state = testToCheckAPIAutomation().jsonPath().getString("data['State']");//saving all the state as string in variable state
		state = state.replaceAll("\\[", "").replaceAll("\\]","");//removing square brackets from beginning and end of string variable
		List<String> convertedStateList = Splitter.on(",").trimResults().splitToList(state);//converting String to String List
		HashSet<String> stateHashSet = new HashSet<String>(convertedStateList);//converting state list to HashSet to capture only unique values from state list
		int stateSize = convertedStateList.size();// getting the size of stateSize variable
		int uniqeStateSize = stateHashSet.size(); // getting the size of stateHashSet variable    
		Assert.assertEquals(stateSize, uniqeStateSize);// Assert if stateSize matches stateHashSet. If they are unique then both should be same size else HashSet will be smaller size.
	}
}
