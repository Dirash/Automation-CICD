package PersonalFile.Framework1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PersonalFile.Framework1.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver){
		
		//to give driver details to super class
		super(driver);
		//giving life to driver from original class
		this.driver=driver;
		//to initialise the ebelement id,password etc
		PageFactory.initElements(driver, this);
		
	}
	//Page factory - another way of writing friver.findelements	
	//List<WebElement> items = driver.findElements(By.cssSelector(".col-sm-10"));
	
	@FindBy(css=".col-sm-10")
	List<WebElement> items;
	
	@FindBy(css=".ng-animating")
	WebElement animating;
	
	By produtsby = By.cssSelector(".col-sm-10");
	By addtoCart = By.cssSelector(".card-body button:last-of-type");
	By Toastmessange = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(produtsby);
		return items;
	}
	
	public WebElement findProductName(String productName) {
		WebElement cartItem = items.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return cartItem;
      
	}
	
	public void addToCart(String productName) {
		
		WebElement cartItem = findProductName(productName);
		cartItem.findElement(addtoCart).click();
		waitForElementToAppear(Toastmessange);
		waitforElementToDissapear(animating);
	}
	
	
	
	
	

}
