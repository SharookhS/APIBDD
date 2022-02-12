package parallelapi.api;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"json:target/jsonReports/cucumber-report.json"},
		glue = { "parallelapi.api"},
		features = { "src/test/java/features" }
		)
public class TestRunner {
	
//	tags= "@DeletePlace"
//tags= {"@DeletePlace"}  compile test verify
	
//	mvn test
//	mvn test -Dcucumber.options="--tags @AddPlace"
//	mvn test -Dcucumber.options="--tags @DeletePlace"
	
	/**
	 * maven-cucumber-reporting
	 * https://github.com/damianszczepanik/maven-cucumber-reporting
	 * 
	 * > Step1 : Open Command Prompt:
	 * mvn test verify
	 * 
	 * Refresh--> Target --> cucumer-html-reports --> overview-features.html 
	 * 
	 */
}
