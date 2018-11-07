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
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;


public class TC006_Register2Copy
{
	WebDriver driver;
	HomePage Hm;
					
	DesiredCapabilities caps = new DesiredCapabilities();
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser)throws Exception
	{
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.out.println("Launching Chrome");
			caps.setCapability("platform", "Windows 10");	
			caps.setCapability("browserName","chrome");
			caps.setCapability("version", "latest");
			caps.setCapability("passed",true);
			caps.setCapability("name", "TC006_Register");
			caps.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER")); 			
			driver = new RemoteWebDriver(new URL(Constant.SauceLabsURL),caps);
		}
		driver.get(Constant.URL);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@Test(priority=0)
	public void WelcomePage() {
		Hm=new HomePage(driver);
		Hm.CheckCurrentPage();			
	}
	
	@Test(priority=1)
	public void Register() {
		Hm=new HomePage(driver);
		Hm.ClickRegister();
	}
	
	@Test(priority=2)
	public void LegalNoticeTab() throws InterruptedException {
		Register RG=new Register(driver);
		RG.LegalNoticeTab();
	}
	
	@Test(priority=3)
	public void SupplierRegistration() throws InterruptedException {
		Register RG=new Register(driver);
		RG.SupplierRegistration();
	}
	
	private void printSessionId() {
		String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
				(((RemoteWebDriver) driver).getSessionId()), System.getenv("JOB_NAME")+ "__" + System.getenv("BUILD_NUMBER"));
	    System.out.println(message);
	}
	
	@AfterMethod(description = "Throw the test execution results into saucelabs")
	public void tearDown(ITestResult result) {
	    String txt = "sauce:job-result=" + (result.isSuccess() ? "passed" : "failed");
	    ((RemoteWebDriver) driver).executeScript(txt);
	    printSessionId();
	}
	
	void annotate(String text) {
		((RemoteWebDriver) driver).executeScript("sauce:context=" + text);
	  }
		
}

