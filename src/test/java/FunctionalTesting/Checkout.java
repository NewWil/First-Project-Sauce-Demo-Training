package FunctionalTesting;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Checkout extends BaseTestSD {
	
												///--TEST CASE SCENARIO 6: 
									   ///--VALIDATING INPUTS FROM CHECKOUT PAGE--///
	
	@Test(dataProvider = "dataset1", dataProviderClass = BaseTestSD.class)
	public void checkout(String fname, String lname, String zipcode) {
		
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("#login-button")).click();
		
		//click shopping cart
		driver.findElement(By.cssSelector("#shopping_cart_container")).click();
		
		//click check out button
		driver.findElement(By.cssSelector("#checkout")).click();
		
		//insert data on Firstname field
		driver.findElement(By.cssSelector("#first-name")).sendKeys(fname);
		
		//insert data on Lasttname field
		driver.findElement(By.cssSelector("#last-name")).sendKeys(lname);
		
		//insert data on zipcode
		driver.findElement(By.cssSelector("#postal-code")).sendKeys(zipcode);
		
		//click Continue button
		driver.findElement(By.cssSelector("#continue")).click();
		
		if(fname.equals("Wilbert") && lname.equals("Lacuesta") && zipcode.equals("2306")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("span[class='title']")).getText(),
			"Checkout: Overview", "Not Found");
			
		}
			
		else if(fname.equals("") && lname.equals("Lacuesta") && zipcode.equals("2306")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(),
					"Error: First Name is required", "Not Found");
			
		}
		
		else if(fname.equals("Wilbert") && lname.equals("") && zipcode.equals("2306")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(), 
			"Error: Last Name is required", "Not Found");
			
		}
		
		else if(fname.equals("Wilbert") && lname.equals("Lacuesta") && zipcode.equals("")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(), 
			"Error: Postal Code is required", "Not Found");
			
		}
		
		else if(fname.equals("") && lname.equals("") && zipcode.equals("2306")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(),
			"Error: First Name is required", "Not Found");
		}
		
		else if(fname.equals("") && lname.equals("Lacuesta") && zipcode.equals("")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(), 
			"Error: First Name is required", "Not Found");	
		}
		
		else {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(), 
			"Error: Last Name is required", "Not Found");
		}
		
		driver.quit();
	}
	
}
