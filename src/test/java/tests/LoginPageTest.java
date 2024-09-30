package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPageTest extends Base{
	
	WebDriver driver;
	String url = "https://tutorialsninja.com/demo/";
	
	@Test
	public void login_In_Chrome_Browser() {
		xtest.info("test started on chrome browser");
		driver = startBrowserAndGoToURL("chrome", url);
		xtest.info("chrome browser started successfully");
		xtest.info("page titile: "+driver.getTitle());
		System.out.println("page title: "+driver.getTitle());		
	}
	
	@Test
	public void login_In_FireFox_Browser() {
		xtest.info("test started on firefox browser");
		driver = startBrowserAndGoToURL("firefox", url);
		xtest.info("firefox browser started successfully");
		xtest.info("page titile: "+driver.getTitle());
		System.out.println("page title: "+driver.getTitle());		
	}
	
	@Test
	public void login_In_Edge_FoxBrowser() {
		xtest.info("test started on edge browser");
		driver = startBrowserAndGoToURL("edge", url);
		xtest.info("edge browser started successfully");
		xtest.info("page titile: "+driver.getTitle());
		System.out.println("page title: "+driver.getTitle());		
	}
}
