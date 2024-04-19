package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
                 // fetaures files      =   glue files  , print them in readable formats , generate report of html plugin
@CucumberOptions(features="src\\test\\java\\cucumber",glue="PersonalFile.stepDefinitions", 
monochrome=true,tags ="@errorvalidation" ,  plugin = {"html:target/cucumber.html"})

public class TestngTestRunner extends AbstractTestNGCucumberTests{

}
