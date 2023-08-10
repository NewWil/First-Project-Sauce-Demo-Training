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

public class AllFunctionSD extends BaseTestSD {

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

											///--TEST CASE SCENARIO 5: SORTING EVERY PRODUCTS--//
							///--VERIFY IF THE PRODUCT FILTER IS DISPLAYING ACCURATE RESULTS IN ASCENDING PRICE ORDER--///
	@Test(groups = "Filter")
	public void ascendingPrice() throws InterruptedException {

		List<WebElement> beforeFilterPrice = driver.findElements(By.cssSelector("div[class='inventory_item_price']"));

		// Remove Dollar sign and convert string to double
		List<Double> beforeFilterPricelist = new ArrayList<Double>();

		for (WebElement we : beforeFilterPrice) {
			beforeFilterPricelist.add(Double.valueOf(we.getText().replace("$", "")));
		}

		// Filter the value
		Select select = new Select(driver.findElement(By.cssSelector("select[class='product_sort_container']")));
		select.selectByIndex(2);

		// after filter
		List<WebElement> AfterFilterPrice = driver.findElements(By.cssSelector("div[class='inventory_item_price']"));

		// remove dollar sign in the products
		List<Double> AfterFilterPricelist = new ArrayList<Double>();

		for (WebElement we : AfterFilterPrice) {
			AfterFilterPricelist.add(Double.valueOf(we.getText().replace("$", "")));
		}

		// Sort the Values
		Collections.sort(beforeFilterPricelist);
		softAssert.assertEquals(beforeFilterPricelist, AfterFilterPricelist, "Sort Ascending Price is NOT working!");
		softAssert.assertAll();
		
	}
												///--VERIFY IF THE PRODUCT FILTER IS DISPLAYING ACCURATE RESULTS IN DESCENDING PRICE ORDER--///
	@Test(groups =  "Filter" )
	public void DescendingPrice() throws InterruptedException {

		
		List<WebElement> beforeFilterPrice = driver.findElements(By.cssSelector("div[class='inventory_item_price']"));

		// Remove Dollar sign and convert string to double
		List<Double> beforeFilterPricelist = new ArrayList<Double>();

		for (WebElement we : beforeFilterPrice) {
			beforeFilterPricelist.add(Double.valueOf(we.getText().replace("$", "")));
		}

		// Filter the value
		Select select = new Select(driver.findElement(By.cssSelector("select[class='product_sort_container']")));
		select.selectByIndex(3);

		// after filter
		List<WebElement> AfterFilterPrice = driver.findElements(By.cssSelector("div[class='inventory_item_price']"));

		// remove dollar sign in the products
		List<Double> AfterFilterPricelist = new ArrayList<Double>();

		for (WebElement we : AfterFilterPrice) {
			AfterFilterPricelist.add(Double.valueOf(we.getText().replace("$", "")));
		}

		// Sort the Values
		Collections.sort(beforeFilterPricelist);
		Collections.reverse(beforeFilterPricelist);
		;

		softAssert.assertEquals(beforeFilterPricelist, AfterFilterPricelist, "Sort descending Price is NOT working!");
		softAssert.assertAll();

		
	}
	 								///--VERIFY IF THE PRODUCT FILTER IS DISPLAYING ACCURATE RESULTS IN DESCENDING ORDER--///
	@Test(groups =  "Filter" )
	public void sortZtoA() {
		
		// Store the original list of Products in arraylist
		List<WebElement> beforeFilter = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		List<String> bfilter = new ArrayList<String>();

		for (WebElement BF : beforeFilter) {
			bfilter.add(BF.getText());
		}

		// Click Z to A option
		Select select = new Select(driver.findElement(By.cssSelector("select[class='product_sort_container']")));
		select.selectByIndex(1);

		// Store the product after sorting
		List<WebElement> afterFilter = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		List<String> afilter = new ArrayList<String>();

		for (WebElement AF : afterFilter) {
			afilter.add(AF.getText());
		}

		Collections.sort(bfilter);
		Collections.reverse(bfilter);

		softAssert.assertEquals(bfilter, afilter, "Sort descending Price is NOT working!");
		softAssert.assertAll();
		
		
	}
										///--VERIFY IF THE PRODUCT FILTER IS DISPLAYING ACCURATE RESULTS IN ASCENDING ORDER--///
	@Test(groups =  "Filter" )
	public void sortAtoZ() {
		// Store the original list of Products in arraylist
		List<WebElement> beforeFilter = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		List<String> bfilter = new ArrayList<String>();

		for (WebElement BF : beforeFilter) {
			bfilter.add(BF.getText());
		}

		// Click Z to A option
		Select select = new Select(driver.findElement(By.cssSelector("select[class='product_sort_container']")));
		select.selectByIndex(0);

		// Store the product after sorting
		List<WebElement> afterFilter = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		List<String> afilter = new ArrayList<String>();

		for (WebElement AF : afterFilter) {
			afilter.add(AF.getText());
		}

		// why do I sort a sorted List???????
		Collections.sort(bfilter);

		softAssert.assertEquals(bfilter, afilter, "Sort descending is NOT working!");
		softAssert.assertAll();
		
		
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
						
	
												///--TEST CASE SCENARIO 8: CANCEL BUTTON--///
												///--VERIFY CANCEL BUTTON FUNCTIONALITY IN CHECKOUT PAGE--///
	@Test(groups = { "Checkout" })
	
	public void Cancel() {

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
		driver.findElement(By.cssSelector("#cancel")).click();

		WebElement ac = driver.findElement(By.cssSelector("span[class='title']"));

		String actualtitle = ac.getText();
		String expectedtitle = "Products";

		softAssert.assertEquals(actualtitle, expectedtitle, "Page NOT FOUND");
		softAssert.assertAll();
		
		
	}
														
														///--TEST CASE SCENARIO 7: FINISH BUTTON IN CHECK OUT PAGE--///
												///--VERIFY FINISH BUTTON FUNCTIONALITY IN CHECKOUT PAGE--///
	@Test
	public void Finish() {
		
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

		WebElement ac = driver.findElement(By.cssSelector("span[class='title']"));

		String actualtitle = ac.getText();
		String expectedtitle = "Checkout: Complete!";

		softAssert.assertEquals(actualtitle, expectedtitle, "Page NOT FOUND");
		softAssert.assertAll();
		
	}
	
													///--TEST CASE SCENARIO 8: BACK HOME BUTTON BUTTON--///
														///--VERIFY BACKHOME BUTTON FUNCTIONALITY--///
	
	@Test(dependsOnMethods = { "Cancel" })
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