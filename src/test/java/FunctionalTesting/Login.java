package FunctionalTesting;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Login extends BaseTestSD {
	
												///--TEST CASE SCENARIO 2: VALIDATING LOGIN FUNCTIONALITY--///
	
																///--VERIFY LOGIN FUNCTIONALITY--///
	
	@Test(dataProvider = "dataset1", dataProviderClass = BaseTestSD.class)
	public void login(String username, String password) {
		
		driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();

		if (username.equals("standard_user") && password.equals("secret_sauce")) {
			Assert.assertEquals(driver.getTitle(), "Swag Labs", "Not Found");
		}
		
		else if (username.equals("standard_user") && password.equals("invalid_pass")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(), 
			"Epic sadface: Username and password do not match any user in this service", "Not Found");
		}
		
		else if (username.equals("invalid_User") && password.equals("secret_sauce")) {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(), 
			"Epic sadface: Username and password do not match any user in this service", "Not Found");
		}
		
		else {
			Assert.assertEquals(driver.findElement(By.cssSelector("div[class='error-message-container error']")).getText(),
			"Epic sadface: Username and password do not match any user in this service", "Not Found");
		}

		driver.quit();
	}
		
}
