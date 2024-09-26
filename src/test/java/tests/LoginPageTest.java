package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPageTest extends Base{
	
	WebDriver driver;
	String url = "https://tutorialsninja.com/demo/";
	
	@Test
	public void loginInChromeBrowser() {
		driver = startBrowserAndGoToURL("chrome", url);
		System.out.println("page title: "+driver.getTitle());		
	}
	
	@Test
	public void loginInFireFoxBrowser() {
		driver = startBrowserAndGoToURL("firefox", url);
		System.out.println("page title: "+driver.getTitle());		
	}
	
	@Test
	public void loginInEdgeFoxBrowser() {
		driver = startBrowserAndGoToURL("edge", url);
		System.out.println("page title: "+driver.getTitle());		
	}
}
