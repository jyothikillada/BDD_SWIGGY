package com.swiggy.cucumber.stepdefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CheckOutPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import utilities.DataReader;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class OrderItem {

	WebDriver driver;
	
	LandingPage landingPage;
	OrderPage orderPage;
	CheckOutPage checkOutPage;
	
	List<HashMap<String, String>> datamap; //Data driven
	
	Logger logger; //for logging
    ResourceBundle resourceBundle; // To Read properties file
    String browser; //to store browser name
    String expectedResult;
	
	@Before
    public void setup()    //Cucumber hook - executes once before starting
    {
		//for logging
        logger= LogManager.getLogger(this.getClass());
        //Reading config.properties (for browser)
        resourceBundle=ResourceBundle.getBundle("config");
        browser=resourceBundle.getString("browser");     
    }
	
	@After
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario status ======>"+scenario.getStatus());
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        }
       driver.quit();
    }
	
	 @Given("User Launch Browser")
	    public void user_launch_browser() {
		 if(browser.equals("chrome"))
	        {
	           driver=new ChromeDriver();
	        }
	        else if (browser.equals("firefox")) {
	            driver = new FirefoxDriver();
	        }
	        else if (browser.equals("edge")) {
	            driver = new EdgeDriver();
	        }
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	         }
	 
	 @Given("open Swiggy {string}")
	 public void open_swiggy(String url) {
		 driver.get(url);
	     driver.manage().window().maximize();
	 }

	 @When("Set location {string}")
	 public void set_location_as(String location) {
		 	landingPage=new LandingPage(driver);
	    	logger.info("Enter lcoation as " + location );
	    	landingPage.enterLocation(location);
	 }

	 @When("Selects first location from suggested location in suggestion-box")
	 public void selects_first_location_from_suggested_location_in_suggestion_box() {
		 logger.info("Select Suggested Location ");
		 landingPage.selectLocationOption();
	 }

	 @When("Click Search")
	 public void click_search() {
		 orderPage = new OrderPage(driver);
		 orderPage.clickSearch();
	     
	 }
	 
	 //*******   Data Driven test method    **************
	 @Then ("Search and select for an Item from excel row {string}")
	 public void searchAndSelectItem(String rows) throws InterruptedException {
		 datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Order_Data.xlsx", "Sheet1");
		 int index=Integer.parseInt(rows)-1;
	     String item= datamap.get(index).get("item");
	     expectedResult = datamap.get(index).get("res");
	     orderPage.searchFood(item); 
	     orderPage.selectSuggestion();
	     orderPage.clickAdd();
	     //orderPage.clickAddItem();
	     orderPage.clickCart();
	     
	 }
	 @Then("User should be able to redirect Checkout page")
	 public void validateCheckoutPage() {
		 checkOutPage = new CheckOutPage(driver);
		 boolean isLoginButtonDisplayed =
			 checkOutPage.isLoginDisplayed();
		 if(expectedResult.equals("Valid")) {
			 if (isLoginButtonDisplayed) { 
				 logger.info("Checkout Page Displayed  ");
			  Assert.assertTrue(true); }
			 else {
			  logger.info("Checkout Page NOT Displayed  "); 
			  Assert.assertTrue(false); 
			  } 
		 }
		 
		 if(expectedResult.equals("Invalid")) {
			 if (isLoginButtonDisplayed) { 
				 logger.info("Checkout Page Displayed  ");
			  Assert.assertTrue(false); }
			 else {
			  logger.info("Checkout Page NOT Displayed  "); 
			  Assert.assertTrue(true); 
			  }
		 }
				 
				  
		driver.close();

	 }

		/*
		 * @When("User Enters Item {string}") public void user_enters_item_as(String
		 * item) throws InterruptedException {
		 * 
		 * orderPage.searchFood(item); orderPage.selectSuggestion();
		 * 
		 * }
		 * 
		 * @When("User Adds first suggested Item in cateogory to cart") public void
		 * user_adds_first_suggested_item_in_biryani_cateogory_to_cart() throws
		 * InterruptedException {
		 * 
		 * orderPage.clickAdd();
		 * 
		 * }
		 * 
		 * @When("User Clicks Add Item") public void user_clicks_add_item() throws
		 * InterruptedException { orderPage.clickAddItem(); }
		 * 
		 * @When("User Clicks Cart") public void user_clicks_cart() {
		 * orderPage.clickCart(); }
		 * 
		 * @Then("User should be able to redirect Checkout page") public void
		 * user_should_be_able_to_redirect_checkout_page() {
		 * 
		 * 
		 * checkOutPage = new CheckOutPage(driver); boolean isLoginButtonDisplayed =
		 * checkOutPage.isLoginDisplayed();
		 * 
		 * if (isLoginButtonDisplayed) { logger.info("Checkout Page Displayed  ");
		 * Assert.assertTrue(true); }else {
		 * logger.info("Checkout Page NOT Displayed  "); Assert.assertTrue(false); }
		 * 
		 * //driver.close();
		 * 
		 * }
		 */
	 }
	 

	
