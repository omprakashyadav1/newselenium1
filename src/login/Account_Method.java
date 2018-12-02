package login;


import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Account_Method  {
	static WebDriver driver;
	static WebDriverWait wait;

	public String open_Browser(String browserName) throws InterruptedException {
		String result="";
		 try  {
			
			if (browserName.equals("Firefox")) {
	System.setProperty("webdriver.gecko.driver","C:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
	 driver= new FirefoxDriver();
	driver.manage().window().maximize();
	Thread.sleep(2000);
	result="Pass";
		System.out.println("TC_1=Open browser working properly");
			} else if (browserName.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "G:\\Others\\chromedriver.exe");
				ChromeOptions options=new ChromeOptions();
				
				options.addArguments("--start-maximized");

				 driver = new ChromeDriver(options);
				System.out.println("TC_1=Open browser working properly");
				result="Pass";
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver","D:/Jars/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				System.out.println("TC_1=Open browser working properly");
				result="Pass";
				}
			
		} catch (WebDriverException e) {
			System.out.println("open browser not successfully open "+e.getMessage());
			result="open browser not successfully --Passed";
			
		}
		return result;
		
	}

	public String enter_URL(String URL) throws InterruptedException {
		String result="";
		driver.navigate().to(URL);
		System.out.println("Url inserted");
		String pageTitle= driver.getTitle();
		System.out.println("Titile of page "+pageTitle);
		if(pageTitle.equals(pageTitle)){
			System.out.println("TC_2==Fox ministry  browser launched successfully--Passed");
			System.out.println("Navigate to " +driver.getTitle());
			Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='loginbtn']/a")).click();

		Thread.sleep(2000);
			result="Pass";
			
		}
		else{
			System.out.println("TC_2==eOffice browser launched successfully--Failed");
			result="Fail";
		}
		return result;
	}

	public By locatorValue(String locatorType, String value) {
		By by;
		switch (locatorType) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}
	public String click_Create_Account_Link(String locatorType, String value) throws InterruptedException{
		String result=""; 
		try  {
			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
			System.out.println("TC-3= Navigate to Create Account page ");
			result="Pass";
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
		return result;
	
	}	}


