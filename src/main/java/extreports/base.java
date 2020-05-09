package extreports;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class base {
	public WebDriver driver;

	@BeforeMethod
	public void testStarted() {
		System.out.println("test started");
	}

	@AfterMethod
	public void quitDriver() {
		driver.quit();
	}

	public WebDriver initializeBrowser() {
		System.setProperty("webdriver.chrome.driver", "D:\\chrome driver  80\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		return driver;
	}

	public String getScreenshotPath(String TestCaseName, WebDriver driver) throws IOException {
		TakesScreenshot takeScreenshot = ((TakesScreenshot) driver);
		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir") + "\\reports\\" + TestCaseName + ".png";
		File file = new File(destFile);
		FileUtils.copyFile(srcFile, file);
		return destFile;
	}

}