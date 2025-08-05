package TestClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TestAmazon {



    @Test
    public  void testAmazon() throws InterruptedException {
        WebDriver driver = new ChromeDriver(); 
        driver.get("https://www.amazon.in/");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        String dynamicId="nav-link-accountList";
        WebElement signInButton = driver.findElement(By.xpath("//div[@id='"+dynamicId+"']"));
        signInButton.click();
        driver.findElement(By.xpath("//input[@id='ap_email_login']")).sendKeys("9110164834");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Shashi@123");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='a-button-input' and @type='submit'][@id='signInSubmit']")).click();
//        Thread.sleep(10000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement loggedUserWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")));
        String text = loggedUserWebElement.getText();
        Actions action = new Actions(driver);

        action.moveToElement(loggedUserWebElement).build().perform();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@id='nav-item-signout']")))).click();

        Thread.sleep(30000);

        driver.quit();
    }

    @Test(dataProvider = "getData", dataProviderClass = Data.class)
    public void dataDrivenTest(String name, String pass){
        System.out.println(name);
        System.out.println(pass);

    }
    @DataProvider(name="login")
    public Object[][] loginData(){
        Object [][] aa= new Object[2][2];
        aa[0][0]="shashi";
        aa[0][1]="Shashi@123";
        aa[1][0]="Shikha";
        aa[1][1]="Shashi@123";
        return aa;

    }
}
