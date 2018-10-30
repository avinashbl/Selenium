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

public class HomePage {

	 WebDriver driver;	
	 	 
	 @FindBy(id="shell-header-hdr-shell-title")
	 WebElement WelcomePage;
	 
	 @FindBy(id="__text0")
	 WebElement Home;
	 
	 @FindBy(id="__text1")
	 WebElement Register;
	 
	 @FindBy(id="__text2")
	 WebElement ForgotPassword;
	 
	 @FindBy(xpath="//*[contains(@class,'sapUshellHeadTitle')]")
	 WebElement Title;
	 	 
	 public HomePage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public String CheckCurrentPage() {
		WebDriverWait waiting = new WebDriverWait(driver, 100);
		waiting.until(ExpectedConditions.visibilityOf(WelcomePage));
		if(((WebElement) WelcomePage).isDisplayed()) {
			System.out.println("BP Supplier Invoice Status Portal page is displayed");
		}
		return WelcomePage.getText();	
	 }	
	 
	 public void ClickForgotPassword() { 
		 WebDriverWait waiting = new WebDriverWait(driver, 130);
	     waiting.until(ExpectedConditions.visibilityOf(ForgotPassword));
	     if(ForgotPassword.isDisplayed()) {
	    	 System.out.println("Forgot Password link is displayed in BP Supplier Portal page");
	         ForgotPassword.click();
	     }
	 }
	 
	 public void ClickRegister() { 
		 WebDriverWait waiting = new WebDriverWait(driver, 130);
	     waiting.until(ExpectedConditions.visibilityOf(Register));
	     if(Register.isDisplayed()) {
	    	 System.out.println("Register link is displayed in BP Supplier Portal page");
	    	 Register.click();
	     }
	 }
	 
	 public void ClickHome() { 
		 WebDriverWait waiting = new WebDriverWait(driver, 130);
	     waiting.until(ExpectedConditions.visibilityOf(Home));
	     if(Home.isDisplayed()) {
	    	 System.out.println("Home link is displayed in BP Supplier Portal page");
	    	 Home.click();
	     }
	     driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	     System.out.println("Check if title of page Home is opened");  
		 Assert.assertEquals(driver.getTitle(),"Home");	     
	 }
	 	 
}

