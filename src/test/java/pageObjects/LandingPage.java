package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

	public LandingPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(id = "location")
	WebElement location;

	@FindBy(xpath = "//span[text()='FIND FOOD']")
	WebElement findFoodButton;
	
	@FindBy(xpath ="(//div[@class='_1oLDb']//button)[1]")
	WebElement locationOption;
	
	/**
	 * Description: Use this method to click Location search box
	 */

	public void clickLocation() {
		location.click();
	}

	/**
	 * Description: Use this method to click Find Food button
	 */
	public void clickFindFood() {
		findFoodButton.click();
	}

	/**
	 * Description: Use this method to set the location
	 * 
	 * @param locationName
	 */
	public void enterLocation(String locationName) // For Search Location
	{
		location.sendKeys(locationName);
	}

	public void selectLocationOption() {
		locationOption.click();
	}
}
