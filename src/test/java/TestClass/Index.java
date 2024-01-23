package TestClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.SearchPage;
import pages.SelectedItemPage;

import java.time.Duration;
import java.util.ArrayList;

public class Index {
    String path= "/home/shashi/IdeaProjects/SeleniumLearning/src/test/resource/chromedriver";
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test(priority = 0)
    public  void TestHomePage(){
        driver.get("https://www.amazon.in/");
    }
    @Test(priority = 1)
    public void testLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAmazon("9110164834","Shashi@123");
    }
    @Test(priority = 2)
    public  void searchItems() throws InterruptedException {
        SearchPage searchPage= new SearchPage(driver);
        String searchItem="poco f1";
        searchPage.SearchElement(searchItem);
        searchPage.verifySearchedProduct(searchItem);
        searchPage.selectSearchItem();
    }
    @Test(priority = 3)
    public  void selectQuantityFromDropdown() throws Exception {
        SelectedItemPage selectedItemPage= new SelectedItemPage(driver);
        selectedItemPage.buyTheItem();
    }

    @Test(priority = 4)
    public  void validateCheckout() throws InterruptedException {
        Thread.sleep(5000);
        CheckoutPage checkoutPage= new CheckoutPage(driver);
        checkoutPage.checkTextPage();

    }
    @Test(priority = 5, dependsOnMethods = "testLogin")
    public  void logOut(){
        LoginPage loginPage = new LoginPage(driver);
        ArrayList<String> tabs= new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        loginPage.logout();
    }
    @AfterClass
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();
    }
}