package PersonalFile.Framework1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PersonalFile.Framework1.AbstractComponents.AbstractComponents;

public class conformationPage extends AbstractComponents {
	
	WebDriver driver;
	
	public conformationPage(WebDriver driver){
			
			//to give driver details to super class
			super(driver);
			//giving life to driver from original class
			this.driver=driver;
			//to initialise the ebelement id,password etc
			PageFactory.initElements(driver, this);
			
		}
	
	
	@FindBy(css=".hero-primary")
    WebElement confirmMessage; 
	
	public String getMessage() {
		return confirmMessage.getText();
		
	}
	
	
}
