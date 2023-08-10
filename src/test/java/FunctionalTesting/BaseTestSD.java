package FunctionalTesting;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

public class BaseTestSD {
	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeMethod
	public void Base(){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
	}
	
	@AfterMethod
	public void afterme() {
		driver.quit();
	}
	
	@DataProvider(name = "dataset1")
	public Object[][] dataset(Method m){
		
		
		Object[][] testdata= null;
		
		if(m.getName().equals("login")) {
			
			testdata = new Object[][]
			{
				{"standard_user", "secret_sauce"},
				{"standard_user", "invalid_pass"}, 
				{"invalid_User", "secret_sauce"},
				{"invalid_User", "Invalid_pass"}
		
			};
		}
		
		else if(m.getName().equals("checkout"))  {
			testdata = new Object[][]
					{
						{"Wilbert", "Lacuesta", "2306"},
						{"", "Lacuesta", "2306"},
						{"Wilbert", "", "2306"}, 
						{"Wilbert", "Lacuesta", ""},
						{"", "", "2306"}, 
						{"","Lacuesta", ""},
						{"Wilbert", "", ""},
							 
					};
		}
		return testdata;
	
	}
}
