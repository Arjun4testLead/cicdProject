package tests;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v115.overlay.model.ColorFormat;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base {
	
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest xtest;
	
	@BeforeSuite
	public void setup() {
		
		//Extent report setup
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("target/Spark.html");
		extent.attachReporter(spark);
		
		//add System info to extent report
		extent.setSystemInfo("machine", "My PC");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("user", "Jawad");
		
		//configuaration for look and feel in report
		spark.config().setDocumentTitle("CICD Project");
		spark.config().setReportName("e-commerce login page");
		spark.config().setTheme(Theme.DARK);
		spark.config().setTimeStampFormat("EEE MMM dd, yyyy. hh : mm : ss a '('zzz')'");
	}
	@BeforeMethod
	public void setupMethodrequirements(Method method) {
		xtest = extent.createTest(method.getName());
	}
	@AfterMethod
	public void afterTestMethod(Method method) throws Exception {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"./screenShots/"+method.getName()+" login page.jpg");
		FileUtils.copyFile(srcFile, destFile);
		String screenPath = destFile.getAbsolutePath();
		//xtest.addScreenCaptureFromPath(screenPath);
		
		//screenshot as base64
		FileInputStream fis = new FileInputStream(destFile);
		byte[] byteArray = IOUtils.toByteArray(fis);
		//String screenPath2 = Base64.getEncoder().encodeToString(byteArray);
		//xtest.addScreenCaptureFromBase64String(screenPath2);
		//xtest.addScreenCaptureFromBase64String(screenPath2, "screenBase64");
		//xtest.pass(MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build());
		
		//base64
		String base64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		//xtest.addScreenCaptureFromBase64String(base64, "Base64 Screen");
		//xtest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
		//xtest.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
		xtest.addScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64));
		xtest.fail("test failed",MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
		xtest.addScreenCaptureFromBase64String(base64, "Home Page");
		xtest.info(MarkupHelper.createLabel("Test is successfully completed", ExtentColor.GREEN));
		String[][] data = new String[][] {{"name", "id", "address"},{"john", "401", "NY"},{"johnas", "402", "MI"},{"johanthan", "403", "DC"}};
		xtest.info(MarkupHelper.createTable(data));
		xtest.pass(MarkupHelper.createLabel("test passed", ExtentColor.CYAN));
		
		
		driver.quit();
		driver = null;
	}
	@AfterTest
	public void afterTestSuite() {
		extent.flush();
	}
	
	public WebDriver startBrowserAndGoToURL(String browserName, String url) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		return driver;
	}
	
	

}
