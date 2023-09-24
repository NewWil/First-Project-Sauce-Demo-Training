package FunctionalTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Filter extends BaseTestSD {
	

	@BeforeMethod
	public void cred() {
		
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("#login-button")).click();
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

}
