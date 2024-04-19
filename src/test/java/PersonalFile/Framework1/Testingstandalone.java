package PersonalFile.Framework1;
import PersonalFile.TestComponents.Retry;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.beust.jcommander.JCommander.Builder;

import PersonalFile.Framework1.pageobjects.LandingPage;
import PersonalFile.Framework1.pageobjects.ProductCatalog;
import PersonalFile.Framework1.pageobjects.cartPage;
import PersonalFile.Framework1.pageobjects.checkOrderPage;
import PersonalFile.Framework1.pageobjects.checkOutPage;
import PersonalFile.Framework1.pageobjects.conformationPage;
import PersonalFile.TestComponents.Basetest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testingstandalone extends Basetest {
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData",groups = "purchase",retryAnalyzer=Retry.class )
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		
		
		ProductCatalog prodObj = obj.loginpage(input.get("email"),input.get("password"));
		List<WebElement>  items = prodObj.getProductList();
		prodObj.addToCart(input.get("product"));
		cartPage cartObj = 	prodObj.goToCart();
		Boolean match = cartObj.verifyProductdisplay(productName);
		Assert.assertTrue(match);
		checkOutPage checkObj =cartObj.goTocheckOut();
		checkObj.selectionCountry("india");
		conformationPage confirmobj = checkObj.submitOrder();
		String successname = confirmobj.getMessage();
		Assert.assertTrue(successname.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
					
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatalog prodObj = obj.loginpage("dirash@gmail.com", "Dirash@1234");
		checkOrderPage orders = prodObj.gotoCheckorders();
		Assert.assertTrue(orders.verifyOrderPage(productName));
	}
	
	
	public String takeScreenshot(String testcasename) throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot)driver;	
	    File source = ss.getScreenshotAs(OutputType.FILE);
	    File destination = new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}
	
	
    @DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJSONDatatoMap(System.getProperty("user.dir")+"//src//test//java//PersonalFile//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}
	
	/*@DataProvider
	public Object[][] getData() {

		// method 1
//		return new Object[][] {{"dirash@gmail.com","Dirash@1234","ADIDAS ORIGINAL"},{"dirash+1@gmail.com","Dirash@1234","ZARA COAT 3"}};
		
	//method 2	
				
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "dirash@gmail.com");
		map.put("password", "Dirash@1234");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "dirash+1@gmail.com");
		map2.put("password", "Dirash@1234");
	map2.put("product", "ZARA COAT 3");

		
		return new Object[][] {{map},{map2}};
		
	}*/


}

