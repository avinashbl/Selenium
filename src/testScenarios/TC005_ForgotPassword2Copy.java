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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parallelTest.Constant;
import pages.HomePage;
import pages.ResetLogin;

public class TC005_ForgotPassword2Copy
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
			caps.setCapability("name", "TC005_ForgotPassword");
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
	public void ForgotPassword() {
		Hm=new HomePage(driver);
		Hm.ClickForgotPassword();	
	}
	
	@Test(priority=2)
	public void ResetLoginDetails() throws InterruptedException {
		ResetLogin RL=new ResetLogin(driver);
		RL.ResetLoginDetails();
	}
	
	
}

