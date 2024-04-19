package PersonalFile.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PersonalFile.Framework1.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {
	
	public WebDriver driver;
	public LandingPage obj;
	public WebDriver initializeDriver() throws IOException {
		
		
		Properties propObj = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
		propObj.load(fis);
		
		String browserDetails = System.getProperty("browser")!=null ? System.getProperty("browser") : propObj.getProperty("browser");
		//String browserDetails = propObj.getProperty("browser");
		
		if(browserDetails.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		}
		else if (browserDetails.equalsIgnoreCase("firefox"))
		{
			//Firefox data
		}
		else if (browserDetails.equalsIgnoreCase("edge")) {
			
			//Edge data
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
public List<HashMap<String, String>> getJSONDatatoMap(String filePath) throws IOException {
		
		//read JSON to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
	
	   //String to hasmap using jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		obj = new LandingPage(driver);
		obj.gotopage();
		return obj;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void shutDown() {
		driver.quit();
	}
	
	public String takeScreenshot(String testcasename, WebDriver driver) throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot)driver;	
	    File source = ss.getScreenshotAs(OutputType.FILE);
	    File destination = new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}


}
