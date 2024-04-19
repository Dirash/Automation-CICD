package PersonalFile.Framework1;
import PersonalFile.TestComponents.Retry;

import java.io.IOException;
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
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.beust.jcommander.JCommander.Builder;
import PersonalFile.Framework1.pageobjects.LandingPage;
import PersonalFile.Framework1.pageobjects.ProductCatalog;
import PersonalFile.Framework1.pageobjects.cartPage;
import PersonalFile.Framework1.pageobjects.checkOutPage;
import PersonalFile.Framework1.pageobjects.conformationPage;
import PersonalFile.TestComponents.Basetest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends Basetest {
	
	@Test(groups = {"errorHandling"},retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException {
		
		
		String productName = "ADIDAS ORIGINAL";
		obj.loginpage("diraswwh@gmail.com", "gg");
		Assert.assertEquals("Incorrect email or password.", obj.errorMessage());
	}

}

