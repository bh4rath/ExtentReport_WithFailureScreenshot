package extent;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import extreports.base;

public class TestCase2 extends base {
	public WebDriver driver;
	
	@Test
	public void testPageTitle() {
		driver = initializeBrowser();
		driver.get("http://hourglass.surge.sh/");
		String result = driver.getTitle();
		Assert.assertEquals(result, "Hour Glass", "String mismatch");
	}
}