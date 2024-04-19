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
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	
	public static void main(String [] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage obj = new LandingPage(driver);
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("dirash@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dirash@1234"); 
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-sm-10")));
		
		//finding the list of items
		List<WebElement> items = driver.findElements(By.cssSelector(".col-sm-10"));
		
		//Filtering only ADIDAS ORIGINAL from the list of items
		WebElement cartItem = items.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		
		//clicking the add to cart
		cartItem.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//waiting for element to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//clicking on cart to view ordered items
		driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
		
		//to check if the cart as selected item
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean check = cartItems.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(check);
		
		//click on check out
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//typing country through Actions
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//to slect india in the second position
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//to select the place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//to get the successfull text
		
		String successname = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(successname.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		driver.quit();
		
		
			
		
	}

}

