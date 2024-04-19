package PersonalFile.Framework1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PersonalFile.Framework1.AbstractComponents.AbstractComponents;

public class checkOrderPage extends AbstractComponents{

	WebDriver driver;
	
	public checkOrderPage(WebDriver driver){
			
			//to give driver details to super class
			super(driver);
			//giving life to driver from original class
			this.driver=driver;
			//to initialise the ebelement id,password etc
			PageFactory.initElements(driver, this);
			
		}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> checkOrderElements;


	public boolean verifyOrderPage(String ProductName) {
		
		Boolean match = checkOrderElements.stream().anyMatch(cartSelection->cartSelection.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
        return match;
	}
}
