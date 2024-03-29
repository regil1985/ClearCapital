import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
public class WebAutomationTest {
private WebDriver driver;
private Map<String, Object> vars;
JavascriptExecutor js;
@Before
public void setUp() throws Exception{
	System.setProperty("webdriver.gecko.driver", "C:\\Windows\\geckodriver-v0.31.0-win64\\geckodriver.exe");
	//System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver_win32\\chromedriver.exe");
 driver = new FirefoxDriver();
 //driver = new ChromeDriver();
 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
 js = (JavascriptExecutor) driver;
 vars = new HashMap<String, Object>();
}
/*@After
public void tearDown() {
 driver.quit();
 System.out.println("Test Completed Successfully");
}*/
@Test
public void webAutomation() {
	//250 ms - default wait for implicit wait
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 // Test name: WebAutomation
 // Step # | name | target | valuee
 // 1 | open | /us/en/ | 
 // Open a ikea URL
 driver.get("https://www.ikea.com/us/en/");
 //maximize the window
 driver.manage().window().maximize();
 // accept the "cookie" modal 
 driver.findElement(By.id("onetrust-accept-btn-handler")).click();
 // 2 | click | name=q | 
 // Click on the Search Box
 driver.findElement(By.name("q")).click();
 // 3 | type | name=q | sofa
 // Type "sofa" in the search bar
 driver.findElement(By.name("q")).sendKeys("sofa");
 // 4 | click | id=search-box__searchbutton | 
 // Click on the lens icon to search
 driver.findElement(By.id("search-box__searchbutton")).sendKeys(Keys.RETURN);
 // 5 | click | css=.serp-grid__item:nth-child(1) .product-fragment__image--alt | 
 // Click on the first result from returned query
 driver.findElement(By.cssSelector(".serp-grid__item:nth-child(1) .product-fragment__image--alt")).click();
 // 6 | click | css=.pip-btn:nth-child(1) .pip-btn__label | 
 // Click on "Add to bag" button
 driver.findElement(By.cssSelector(".pip-btn:nth-child(1) .pip-btn__label")).click();
 // 7 | click | css=.rec-btn--icon-primary-inverse > .rec-btn__inner | 
 // Click on the "X" button for sliding menu on the right side
 driver.findElement(By.cssSelector(".rec-btn--icon-primary-inverse > .rec-btn__inner")).click();
 //Refresh the page because Ikea site has issue where search bar disappears after performing step prior to this
 driver.navigate().refresh();
 // 8 | click | name=q | 
 // Click on the Search Box
 //driver.findElement(By.name("q")).click();
 // 9 | type | name=q | table
 // Type "table" in the search bar
 driver.findElement(By.name("q")).sendKeys("table");
 // 10 | click | id=search-box__searchbutton | 
 // Click on the lens icon to search
 driver.findElement(By.id("search-box__searchbutton")).click();
 // 11 | click | css=.serp-grid__item:nth-child(3) .product-fragment__image--alt | 
 // Click on the third result from returned query
 driver.findElement(By.cssSelector(".serp-grid__item:nth-child(3) .product-fragment__image--alt")).click();
 // 12 | click | css=.pip-btn:nth-child(1) > .pip-btn__inner | 
 // Click on the "Add to bag" button
 driver.findElement(By.cssSelector(".pip-btn:nth-child(1) > .pip-btn__inner")).click();
 // 13 | click | css=.rec-btn--icon-primary-inverse > .rec-btn__inner | 
 // Click on the "X" button for sliding menu on the right side
 driver.findElement(By.cssSelector(".rec-btn--icon-primary-inverse > .rec-btn__inner")).click();
 // 14 | click | css=.js-shopping-cart-icon | 
 // Click on the "Shoopping Cart" icon on top right
 driver.findElement(By.cssSelector(".js-shopping-cart-icon")).click();
 // 15 | storeAttribute | //span[@data-label-default="Shopping bag"]@data-count | ShoppingCartCount
 // Store the attribut value from the Shopping Cart in a Variable
 {
   WebElement element = driver.findElement(By.xpath("//span[@data-label-default=\"Shopping bag\"]"));
   String attribute = element.getAttribute("data-count");
   vars.put("ShoppingCartCount", attribute);
 }
 // 16 | assert | ShoppingCartCount | 2
 // validate that 2 items are added to the cart
 assertEquals(vars.get("ShoppingCartCount").toString(), "2");
 // 17 | click | css=.cart-ingka-accordion-item-header__title | 
 // Click on the link for "Use a discount code"
 driver.findElement(By.cssSelector(".cart-ingka-accordion-item-header__title")).click();
 // 18 | click | id=discountCode | 
 // Click on the "Discount Code" text box
 driver.findElement(By.id("discountCode")).click();
 // 19 | executeScript | return Math.random(). toString(36).substring(0,20) | DiscountCode
 // Crate a random Text for "Discount Code" and store it in a variable
 vars.put("DiscountCode", js.executeScript("return Math.random(). toString(36).substring(0,20)"));
 // 20 | type | id=discountCode | ${DiscountCode}
 // Type the stored Discount Code Variable value in the Discount Code text box
 driver.findElement(By.id("discountCode")).sendKeys(vars.get("DiscountCode").toString());
 // 21 | click | css=.cart-ingka-btn--secondary .cart-ingka-btn__label | 
 // Click on the "Apply discount" button
 driver.findElement(By.cssSelector(".cart-ingka-btn--secondary .cart-ingka-btn__label")).click();
 // 22 | assertText | css=.cart-ingka-form-field__message | You've added an invalid coupon code. Please try again.
 // Validate that "invalid coupon code" error message is displayed
 assertThat(driver.findElement(By.cssSelector(".cart-ingka-form-field__message")).getText(), is("You\\\'ve added an invalid coupon code. Please try again."));
}
}
