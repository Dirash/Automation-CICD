package PersonalFile.Framework1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PersonalFile.Framework1.AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {
	
	WebDriver driver;
	
public checkOutPage(WebDriver driver){
		
		//to give driver details to super class
		super(driver);
		//giving life to driver from original class
		this.driver=driver;
		//to initialise the ebelement id,password etc
		PageFactory.initElements(driver, this);
		
	}
      @FindBy(css="[placeholder='Select Country']")
      WebElement select;
      
      @FindBy(css=".action__submit")
      WebElement submit;
  
      @FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
      WebElement selectCountry;
      
      By result = By.cssSelector(".ta-results");
      
      public void selectionCountry(String country) {
    	  
    	  
    	  Actions a = new Actions(driver);
  		  a.sendKeys(select, country).build().perform();
  		  waitForElementToAppear(By.cssSelector(".ta-results"));
    	  selectCountry.click();
    	  
      }
      
      public conformationPage submitOrder() {
    	  submit.click();
    	  return new conformationPage(driver);
      }

}
