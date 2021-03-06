package jdiTestSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;

import initClasses.TestInitializer;
import jdiTestSite.pageObjects.ContFormPage;
import jdiTestSite.pageObjects.DatesPage;
import jdiTestSite.pageObjects.EvenOddForm;
import jdiTestSite.pageObjects.Header;
import jdiTestSite.pageObjects.PersonalInfoForm;
import jdiTestSite.pageObjects.RightSection;
import utils.JdiTestSiteTestsConfig;
import utils.WebPageHandler;

@ContextConfiguration(classes = JdiTestSiteTestsConfig.class)
public class JdiSiteInitializer extends TestInitializer {
	@Autowired
	private Environment env;
	
	@Autowired
	protected String jdiTestSiteUrl;

	@Autowired
	protected String homePageTitle;

	@Autowired
	protected String contactFormPageTitle;

	protected Header header;
	protected PersonalInfoForm persInfoForm;
	protected RightSection rightSect;
	protected EvenOddForm evenOddForm;
	protected ContFormPage contFormPage;
	protected DatesPage datesPage;
	protected String userLogin;
	protected String userPass;
	protected String userName;

	protected WebPageHandler pageHndl;

	@BeforeMethod
	public void beforeJdiSiteTest() {
		pageHndl = new WebPageHandler(driver);
		header = new Header(driver);
		persInfoForm = new PersonalInfoForm(driver);
		rightSect = new RightSection(driver);
		evenOddForm = new EvenOddForm(driver);
		contFormPage = new ContFormPage();
		datesPage = new DatesPage(driver);
		userLogin = env.getProperty("login");
		userPass = env.getProperty("password");
		userName = env.getProperty("name");
	}
}
