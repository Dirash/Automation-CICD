package PersonalFile.Framework1.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PersonalFile.Framework1.pageobjects.cartPage;
import PersonalFile.Framework1.pageobjects.checkOrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartPage;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement checkOrders;

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
    public void waitForWebElementToAppear(WebElement dri) {
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dri));

	}

	
	public void waitforElementToDissapear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public PersonalFile.Framework1.pageobjects.cartPage goToCart() {
	
		cartPage.click();
		cartPage cartObj = new cartPage(driver);
		return cartObj;
	}
	
	public checkOrderPage gotoCheckorders() {
		checkOrders.click();
		return new checkOrderPage(driver);
	
	}
}
