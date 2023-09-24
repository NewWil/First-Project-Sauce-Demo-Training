package FunctionalTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddToCart extends BaseTestSD {

	@BeforeMethod
	public void cred() {
		
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("#login-button")).click();
	}

									///--TEST CASE SCENARIO 3: ADD PRODUCT TO CART--///
								    ///--VERIFY ADD TO CART BUTTON FUNCTIONALITY--///
	@Test
	public void AddtoCart() throws InterruptedException {
		
		// Click Add to All Cart button

		List<WebElement> inventoryList = driver.findElements(By.cssSelector("div[class='inventory_item']"));

		for (int i = 0; i < inventoryList.size(); i++) {

			driver.findElement(By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']")).click();
		}

		// Go to Your Cart page to check all added products
		driver.findElement(By.cssSelector("#shopping_cart_container")).click();

		List<WebElement> getsize = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));

		String ActualResult;
		String ExpectedResult;

		for (WebElement we : getsize) {

			ActualResult = we.getText();
			ExpectedResult = we.getText();

			Assert.assertEquals(ActualResult, ExpectedResult, "No Record Found");
			softAssert.assertAll();
		}

	}
	
	
	
	    										///--TEST CASE SCENARIO 4: REMOVE PRODUCT TO CART--///
												///--VERIFY REMOVE BUTTON FUNCTIONALITY--///
	@Test
	public void validateRemovedProductsFromCart() throws InterruptedException {
	    // Add products to the cart
	    List<WebElement> addtocart = driver.findElements(By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']"));
	    
	    for (WebElement click : addtocart) {
	    	
	        click.click();
	    }

	    // Go to the cart page
	    driver.findElement(By.cssSelector("#shopping_cart_container")).click();

	    // Get the names of the products in the cart
	    List<WebElement> itemname = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
	    List<String> productNamesInCart = new ArrayList<String>();
	    for (WebElement item : itemname) {
	        productNamesInCart.add(item.getText());
	    }

	    // Remove the products from the cart
	   List <WebElement> removed = driver.findElements(By.cssSelector("button[class='btn btn_secondary btn_small cart_button']"));
	   for(WebElement re: removed) {
		   re.click();
	   }
	    // Wait for the page to update
	    //Thread.sleep(2000);

	    // Get the names of the products after removal
	    List<WebElement> listafterremoved = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));

	    // Validate if all products have been removed
	    Assert.assertTrue(listafterremoved.isEmpty(), "All products have been removed from the cart");
	}

											
						
	@Test(enabled = false)
	public void Product() throws InterruptedException {

		List<WebElement> items = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));

		int initialItemCount = items.size();
		int processedItemCount = 0;

		while (processedItemCount < initialItemCount) {
			try {
				WebElement item = items.get(processedItemCount);
				item.click();

				driver.navigate().back();
				// Add your assertions here
				// Example assertion: Assert that the item's text is not empty
				Assert.assertFalse(item.getText().isEmpty(), "NO Products Found!");

			} catch (StaleElementReferenceException e) {
				items = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
				initialItemCount = items.size();
			}

			processedItemCount++; // Increment processedItemCount here
			continue; // Proceed to the next iteration
		}
		
	}
						
	
	
													///-TEST CASE SCENARIO 8: BACK HOME BUTTON BUTTON--///
														///--VERIFY BACKHOME BUTTON FUNCTIONALITY--///
	
	@Test()
	public void BackHome() {
		// click shopping cart icon
		driver.findElement(By.cssSelector("#shopping_cart_container")).click();

		// click checkout button
		driver.findElement(By.cssSelector("#checkout")).click();

		// Enter First Name
		driver.findElement(By.cssSelector("#first-name")).sendKeys("Wilbert");

		// Enter Last Name
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Lacuesta");

		// Enter Postal
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("2306");

		// Click Continue button
		driver.findElement(By.cssSelector("#continue")).click();

		// Click Continue button
		driver.findElement(By.cssSelector("#finish")).click();

		// click backhome
		driver.findElement(By.cssSelector("#back-to-products")).click();

		WebElement ac = driver.findElement(By.cssSelector("span[class='title']"));

		String actualtitle = ac.getText();
		String expectedtitle = "Products";

		softAssert.assertEquals(actualtitle, expectedtitle, "Page NOT FOUND");
		softAssert.assertAll();
	}

}