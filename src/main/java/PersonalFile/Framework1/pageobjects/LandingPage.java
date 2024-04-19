package PersonalFile.Framework1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PersonalFile.Framework1.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		//giving life to driver from original class
		this.driver=driver;
		//to initialise the ebelement id,password etc
		PageFactory.initElements(driver, this);
		
	}
	//Page factory - another way of writing friver.findelements	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorCode;
	
	public ProductCatalog loginpage(String mailid, String pass) {
		
		useremail.sendKeys(mailid);
		password.sendKeys(pass);
		submit.click();
		ProductCatalog prodObj = new ProductCatalog(driver);
		return prodObj;
	}
	
	public void gotopage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
public String errorMessage() {
		
		waitForWebElementToAppear(errorCode);
		return errorCode.getText();
		
	}

}
