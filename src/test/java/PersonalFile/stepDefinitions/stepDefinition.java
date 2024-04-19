package PersonalFile.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PersonalFile.Framework1.pageobjects.LandingPage;
import PersonalFile.Framework1.pageobjects.ProductCatalog;
import PersonalFile.Framework1.pageobjects.cartPage;
import PersonalFile.Framework1.pageobjects.checkOutPage;
import PersonalFile.Framework1.pageobjects.conformationPage;
import PersonalFile.TestComponents.Basetest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition extends Basetest {
	
	public LandingPage landingPage;
	public ProductCatalog prodObj;
	conformationPage confirmobj;
	@Given("I landed on ecommernce page")
	public void I_landed_on_ecommernce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username, String password) {
	  prodObj = obj.loginpage(username,password);
	}
	
	@When ("^Add the product (.+) to cart$")
	public void Add_the_product_to_cart(String productname) {
		List<WebElement>  items = prodObj.getProductList();
		prodObj.addToCart(productname);
	}
	@When ("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productname) {
		cartPage cartObj = 	prodObj.goToCart();
		Boolean match = cartObj.verifyProductdisplay(productname);
		Assert.assertTrue(match);
		checkOutPage checkObj =cartObj.goTocheckOut();
		checkObj.selectionCountry("india");
		 confirmobj = checkObj.submitOrder();
	}
	
	
	@Then("{string} message to be displayed in confirmationpage")
	public void message_to_be_displayed_in_confirmationpage(String string) {
		
		String successname = confirmobj.getMessage();
		Assert.assertTrue(successname.equalsIgnoreCase(string));
		

	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		
		Assert.assertEquals(string, obj.errorMessage());
		
	}
}
