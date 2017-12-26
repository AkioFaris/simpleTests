package jdiTestSite;

import static utils.DataHandler.readPersInfFromFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DatesPageTest extends JdiSiteInitializer {

	@BeforeMethod
	public void testPreparation() {
		/* Open test site by URL */
		naigateToApiUrl(jdiTestSiteUrl);

		Assert.assertTrue(header.loginForm.caret.isDisplayed());

		/* Perform login */
		header.loginForm.login(userLogin, userPass);

		Assert.assertTrue(header.contactFormBtn.isDisplayed());

		/* Open Dates page */
		header.openDatesPage();
				
		// Doesn't work:
		pageHndl.zoomOutByNumb(3);
		
		// Works, but stops further execution:
		pageHndl.zoomOutByPct(75);

		Assert.assertTrue(rightSect.logPanel.isDisplayed());
		Assert.assertTrue(rightSect.resultPanel.isDisplayed());
	}

	@Test
	public void verifyInfoSubmitting() throws IOException, URISyntaxException {
		String iPersInfoPath = "\\src\\test\\resources\\personal_info.txt";
		String iPicPath = "\\src\\test\\resources\\avatar.jpg";
		List<String> personalInfo = readPersInfFromFile(iPersInfoPath);
		Assert.assertTrue(personalInfo.size() >= 3);
		String firstName = personalInfo.get(0);
		String lastName = personalInfo.get(1);
		String descr = personalInfo.get(2);
		Integer[] range1 = {60, 80};
		Integer[] range2 = {20, 80};
		

		Assert.assertTrue(persInfoForm.name.isDisplayed());
		Assert.assertTrue(persInfoForm.lastName.isDisplayed());
		Assert.assertTrue(persInfoForm.descr.isDisplayed());
		Assert.assertTrue(persInfoForm.submitBtn.isDisplayed());

		/* Fill the form with first name, last name and description */
		persInfoForm.fill(firstName, lastName, descr);

		/* Use waits to check that files information shows up in log field*/
		Assert.assertTrue(rightSect.logContains(firstName));
		Assert.assertTrue(rightSect.logContains(lastName));
		Assert.assertTrue(rightSect.logContains(descr));
				
		/* Press submit */
		persInfoForm.submit();
		
		/* Check the result: assert values for range 1 and range 2 and Name */
		Assert.assertTrue(rightSect.resultContains(range2[0].toString()));
		Assert.assertTrue(rightSect.resultContains(range2[1].toString()));
	}
}