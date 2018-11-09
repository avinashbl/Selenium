/**
 * 
 * @author avinash.bl
 * Validate Forgot Password
  *
 */
package testScenarios;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parallelTest.Constant;
import pages.HomePage;
import pages.ResetLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class TC005_ForgotPassword
{
	HomePage Hm;
	
	public static final String SUSERNAME ="avinash.bl";
	public static final String SACCESS_KEY ="f9151132-0541-43cc-833a-6890e4f26d01";

				
	//ThreadLocal variable which contains @link WebDriver instance which is used to perform browser interactions with.
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	//ThreadLocal variable which contains the Sauce Job Id.
	private ThreadLocal<String> sessionId = new ThreadLocal<String>();
	
	//@return the {@link WebDriver} for the current thread
	public WebDriver getWebDriver() {
		return webDriver.get();
	}
		
	//@return the Sauce Job id for the current thread
	public String getSessionId() {
		return sessionId.get();
	}
	
	@BeforeTest
	@Parameters("browser")
	
	public void createRemoteDriver(String browser) throws Exception  {
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	Class<? extends TC005_ForgotPassword> SLclass = this.getClass();
    	
    	if(browser.equalsIgnoreCase("Chrome")) {
    		System.out.println("Launching Chrome");
    		capabilities.setCapability("browserName","chrome");
		}
    	        
    	capabilities.setCapability("version", "latest");
    	capabilities.setCapability("platform", "Windows 10");	
		capabilities.setCapability("passed",true);
		capabilities.setCapability("name", SLclass.getSimpleName());
		capabilities.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
		
		// Launch remote browser and set it as the current thread
		webDriver.set(new RemoteWebDriver( 
                new URL("https://" + SUSERNAME + ":" + SACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub"), 
                capabilities)); 
		// set current sessionId
		String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString(); 
		sessionId.set(id); 
		getURL();
	}
		
	private void getURL() {
		getWebDriver().get(Constant.URL);
		getWebDriver().manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
	}
	
	@Test(priority=0)
	public void WelcomePage() {
		Hm=new HomePage(getWebDriver());
		Hm.CheckCurrentPage();			
	}
	
	@Test(priority=1)
	public void ForgotPassword() {
		Hm=new HomePage(getWebDriver());
		Hm.ClickForgotPassword();	
	}
	
	@Test(priority=2)
	public void ResetLoginDetails() throws InterruptedException {
		ResetLogin RL=new ResetLogin(getWebDriver());
		RL.ResetLoginDetails();
	}
	
	private void printSessionId() {
		String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
				((RemoteWebDriver) getWebDriver()).getSessionId(), System.getenv("JOB_NAME")+ "__" + System.getenv("BUILD_NUMBER"));
	    System.out.println(message);
	}
	
	@AfterMethod(description = "Method that gets invoked after test. Gets and dumps browser log and closes the browser")
	public void tearDown(ITestResult result) {
	    ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
	    printSessionId();
	}
	
	void annotate(String text) {
		((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
	}
	
}

