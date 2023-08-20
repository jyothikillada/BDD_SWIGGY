package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends BasePage{

	public OrderPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy (xpath = "//span[text()='Search']")
	WebElement search;
	
	@FindBy (xpath = "//input[@placeholder='Search for restaurants and food']")
	WebElement searchFoodXapth;
	
	@FindBy (xpath = "(//button[@data-testid='autosuggest-item'])[1]")
	WebElement autoSuggesstion;
	
	@FindBy (xpath = "//span[text()='Restaurants']")
	WebElement restaurants;
	
	@FindBy (xpath= "(//div[text()='ADD'])[1]")
	WebElement addButton;
	
	@FindBy (xpath = "(//span[text()='Add Item'])[1]")
	WebElement addItem;
	
	@FindBy (xpath ="(//span[text()='Cart'])[1]")
	WebElement cart;
	
	public void clickSearch() {
		search.click();
	}
	
	
	public void searchFood(String foodItem) {
		searchFoodXapth.sendKeys(foodItem);
		}
	public void clickRestaurants() {
		restaurants.click();
	}
	public void selectSuggestion() throws InterruptedException {
		autoSuggesstion.click();
		
	}
	
	public void clickAdd() throws InterruptedException {
		synchronized (addButton) {
            try {
            	addButton.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		addButton.click();
		
	}
	
	public void clickAddItem() throws InterruptedException {
		synchronized (addItem) {
            try {
            	addItem.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		addItem.click();
		
	}
	
	public void clickCart() {
		synchronized (cart) {
            try {
            	cart.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		cart.click();
	}
	
	
}

