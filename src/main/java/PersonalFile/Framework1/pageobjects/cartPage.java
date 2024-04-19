package PersonalFile.Framework1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PersonalFile.Framework1.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public cartPage(WebDriver driver){
		
		//to give driver details to super class
		super(driver);
		//giving life to driver from original class
		this.driver=driver;
		//to initialise the ebelement id,password etc
		PageFactory.initElements(driver, this);
		
	}
	//Page factory - another way of writing friver.findelements	
	//List<WebElement> items = driver.findElements(By.cssSelector(".col-sm-10"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartSelection;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	


	public boolean verifyProductdisplay(String ProductName) {
		
		Boolean match = cartSelection.stream().anyMatch(cartSelection->cartSelection.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
        return match;
	}
	
	public checkOutPage goTocheckOut() {
		checkOut.click();
		return new checkOutPage(driver);
		
	}
	

		
	}
	
	
