package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends BasePage{

	public CheckOutPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy (xpath="//div[@class='C6mS- y9pNN']")
	WebElement loginButton;
	
	public void clickLogin() {
		loginButton.click();
		
	}
	
	public boolean isLoginDisplayed() {
		return loginButton.isDisplayed();
		
	}
}

