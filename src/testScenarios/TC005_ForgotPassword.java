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
import pages.ResetLogin;

public class TC005_ForgotPassword
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
			caps.setCapability("name","TestChrome");
			caps.setCapability("platform", "Windows 10");	
			caps.setCapability("browserName","chrome");
			caps.setCapability("version", "latest");
			caps.setCapability("passed",true);
			caps.setCapability("build","CrossBrowser_Test_Build1"); 
			driver = new RemoteWebDriver(new URL(Constant.SauceLabsURL),caps);
		}
		DesiredCapabilities caps1 = DesiredCapabilities.internetExplorer();
		if(browser.equalsIgnoreCase("InternetExplorer1"))
		{
			System.out.println("Launching IE");
			caps1.setCapability("name","IETest");
			caps1.setCapability("platformName", "Windows 7");
			caps1.setCapability("version","11.0");
			caps1.setCapability("passed",true);
			caps1.setCapability("build","CrossBrowser_Test_Build1");
			driver = new RemoteWebDriver(new URL(Constant.SauceLabsURL),caps1);
		}
		
		if(browser.equalsIgnoreCase("Firefox"))
		{
			System.out.println("Launching Firefox");	
			caps.setCapability("name","TestFirefox");
			caps.setCapability("platform", "Windows 10");	
			caps.setCapability("browserName","Firefox");
			caps.setCapability("version", "latest");
			caps.setCapability("passed",true);
			caps.setCapability("build","CrossBrowser_Pilot"); 
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

