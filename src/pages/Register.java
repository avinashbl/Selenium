/**
 * @author avinash.bl
 * This page is Home page from where we can navigate to various other Pages
 *
 */

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register {

	 WebDriver driver;	
	 
	 @FindBy(xpath="//bdi[contains(text(),'I Agree')]")
	 WebElement IAgree;
	 
	 @FindBy(xpath="//*[contains(text(),'Legal Notice')]")
	 WebElement LegalNotice;
	 
	 @FindBy(xpath="//*[contains(text(),'Supplier Registration')]")
	 WebElement SupplierReg;

	 @FindBy(xpath="//bdi[contains(text(),'Submit')]")
	 WebElement SubmitBtn;
	 
	 public Register(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void LegalNoticeTab() throws InterruptedException {		 
		  Thread.sleep(4000);		 
		  WebDriverWait waiting = new WebDriverWait(driver, 300);
		  waiting.until(ExpectedConditions.visibilityOf(LegalNotice));
		  if(LegalNotice.isDisplayed()) {
			  System.out.println("Legal Notice pdf tab opened");
		  }
		  
		  WebDriverWait waiting1 = new WebDriverWait(driver, 300);
		  waiting1.until(ExpectedConditions.visibilityOf(IAgree));
		  if(IAgree.isDisplayed()) {
		    	System.out.println("Clicked 'I Agree' button in Legal Notice tab");
		    	IAgree.click();
		  }  
	 }	  
		  
      public void SupplierRegistration() throws InterruptedException { 
    	  Thread.sleep(4000);
    	  WebDriverWait waiting2 = new WebDriverWait(driver, 300);
    	  waiting2.until(ExpectedConditions.visibilityOf(SupplierReg));
		  if(SupplierReg.isDisplayed()) {
		    	System.out.println("Supplier Registration page displayed");
		  }
		  
		  WebDriverWait waiting3 = new WebDriverWait(driver, 300);
    	  waiting3.until(ExpectedConditions.visibilityOf(SubmitBtn));
		  if(SubmitBtn.isDisplayed()) {
		    	System.out.println("Submit button on Supplier Registration page displayed");
		  }
	 }
	       
}

