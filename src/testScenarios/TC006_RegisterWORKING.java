/**
 * 
 * @author avinash.bl
 * Validate Forgot Password
  *
 */
package testScenarios;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parallelTest.Constant;
import pages.HomePage;
import pages.Register;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class TC006_RegisterWORKING
{
	HomePage Hm;
	
	public static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	@BeforeTest
	@Parameters("browser")
	public void createRemoteDriver(String browser) throws Exception {
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	Class<? extends TC006_RegisterWORKING> SLclass = this.getClass();
    	
    	if(browser.equalsIgnoreCase("Chrome")) {
    		System.out.println("Launching Chrome");
    		capabilities.setCapability("browserName","chrome");
		}
    	        
    	capabilities.setCapability("version", "latest");
    	capabilities.setCapability("platform", "Windows 10");	
		capabilities.setCapability("passed",true);
		capabilities.setCapability("name", SLclass.getSimpleName());
		capabilities.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
		
		RemoteWebDriver rwd = new RemoteWebDriver(new URL(Constant.SauceLabsURL), capabilities);
	    driver.set(rwd);
	    getURL();
	}
				
	protected static RemoteWebDriver getDriver() {
	    return driver.get();
	}
	
	private void getURL() {
	    getDriver().get(Constant.URL);
	    getDriver().manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
	}
		
	@Test(priority=0)
	public void WelcomePage() {
		Hm= new HomePage(getDriver());
		Hm.CheckCurrentPage();			
	}
	
	@Test(priority=1)
	public void Register() {
		Hm=new HomePage(getDriver());
		Hm.ClickRegister();
	}
	
	@Test(priority=2)
	public void LegalNoticeTab() throws InterruptedException {
		Register RG=new Register(getDriver());
		RG.LegalNoticeTab();
	}
	
	@Test(priority=3)
	public void SupplierRegistration() throws InterruptedException {
		Register RG=new Register(getDriver());
		RG.SupplierRegistration();
	}
	
	private void printSessionId() {
		String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
				getDriver().getSessionId(),System.getenv("JOB_NAME")+ "__" + System.getenv("BUILD_NUMBER"));
	    System.out.println(message);
	}
	
	@AfterMethod(description = "Throw the test execution results into saucelabs")
	public void tearDown(ITestResult result) {
	    String txt = "sauce:job-result=" + (result.isSuccess() ? "passed" : "failed");
	    getDriver().executeScript(txt);
	    printSessionId();
	   // getDriver().quit();
	}
	
	void annotate(String text) {
		getDriver().executeScript("sauce:context=" + text);
	}
		
}

