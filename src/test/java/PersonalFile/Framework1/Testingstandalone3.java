package PersonalFile.Framework1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.beust.jcommander.JCommander.Builder;

import PersonalFile.Framework1.pageobjects.LandingPage;
import PersonalFile.Framework1.pageobjects.ProductCatalog;
import PersonalFile.Framework1.pageobjects.cartPage;
import PersonalFile.Framework1.pageobjects.checkOutPage;
import PersonalFile.Framework1.pageobjects.conformationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testingstandalone3 {
	
	
	public static void main(String [] args) {
		
		
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage obj = new LandingPage(driver);
		obj.gotopage();
		ProductCatalog prodObj = obj.loginpage("dirash@gmail.com", "Dirash@1234");
		
		/* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-sm-10")));
		List<WebElement> items = driver.findElements(By.cssSelector(".col-sm-10"));*/
		
		//replacing the above with the code
		List<WebElement>  items = prodObj.getProductList();
		
		/*Filtering only ADIDAS ORIGINAL from the list of items
		WebElement cartItem = items.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//clicking the add to cart
		cartItem.findElement(By.cssSelector(".card-body button:last-of-type")).click();*/
		prodObj.addToCart(productName);
	    
		
		
		/*clicking on cart to view ordered items
	     driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();*/
		cartPage cartObj = 	prodObj.goToCart();
		
		/*to check if the cart as selected item
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean check = cartItems.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(check);*/
		
		Boolean match = cartObj.verifyProductdisplay(productName);
		Assert.assertTrue(match);
		
		
		//click on check out
		checkOutPage checkObj =cartObj.goTocheckOut();
		
		//typing country through Actions
		//Actions a = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build().perform();
		checkObj.selectionCountry("india");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//to slect india in the second position
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//to select the place order
		//driver.findElement(By.cssSelector(".action__submit")).click();
		conformationPage confirmobj = checkObj.submitOrder();
		//to get the successfull text
		
		String successname = confirmobj.getMessage();
		Assert.assertTrue(successname.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		//driver.quit();
		
		
			
		
	}

}

