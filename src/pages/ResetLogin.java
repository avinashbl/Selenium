/**
 * @author avinash.bl
 * This page is Home page from where we can navigate to various other Pages
 *
 */

package pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ResetLogin {

	 WebDriver driver;	
	 
	 @FindBy(xpath="//span[contains(text(),'Submit')]")
	 WebElement SubmitBtn;
	 
	 public ResetLogin(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void ResetLoginDetails() throws InterruptedException {		 
		  Thread.sleep(400);
		  driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		  for (String handle1 : driver.getWindowHandles()) {
			 driver.switchTo().window(handle1);
			 System.out.println(handle1);
		 }
		 System.out.println("Check if Reset your login details tab opened");  
		 Assert.assertEquals(driver.getTitle(),"Reset your login details");
		
		WebDriverWait wait = new WebDriverWait(driver,150);
		wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
		if(SubmitBtn.isDisplayed()) {
			 System.out.println("Submit button on Reset your login details page displayed");
		}
		driver.close();
	 }
}

