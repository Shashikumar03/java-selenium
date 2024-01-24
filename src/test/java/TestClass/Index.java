package TestClass;

import org.example.ConfigReader;
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
    private WebDriver driver;
    private ConfigReader configReader;

    //setup;
    @BeforeClass
    public void setUp() {
        configReader = new ConfigReader();
        System.setProperty("webdriver.chrome.driver", configReader.getChromeDriverPath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //getting website url from config.properties
    @Test(priority = 0)
    public void TestHomePage() {
        driver.get(configReader.getWebsiteUrl());
    }

    //validating Login page with valid username and Password;
    @Test(priority = 1)
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        boolean logged = loginPage.loginAmazon(configReader.getUserName(), configReader.getPassWord());
        if(logged){
            System.out.println("user is successfully logged in");
        }else{
            System.out.println("user can not logged in");
        }

    }

    //validating search;
    @Test(priority = 2)
    public void searchItems() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);
        String searchItem = "poco f1";
        searchPage.searchElement(searchItem);
        searchPage.verifySearchedProduct(searchItem);
        searchPage.selectSearchItem();
    }

    @Test(priority = 3)
    public void selectQuantityFromDropdown() throws Exception {
        SelectedItemPage selectedItemPage = new SelectedItemPage(driver);
        selectedItemPage.buyTheItem();
    }

    @Test(priority = 4)
    public void validateCheckout() throws InterruptedException {
        Thread.sleep(5000);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.checkTextPage();

    }

    @Test(priority = 5, dependsOnMethods = "testLogin")
    public void logOut() {
        LoginPage loginPage = new LoginPage(driver);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        loginPage.logout();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}