package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver d){
        this.driver=d;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(xpath = "//input[@value='Go']")
    WebElement searchButton;

   public void SearchElement(String items){
       searchBox.click();
       searchBox.sendKeys(items);
       searchButton.click();
   }
   public void verifySearchedProduct(String searchItem){
       String title = driver.getTitle();
       Assert.assertTrue(title.contains(searchItem));
       System.out.println("searched item is correct");
   }
   public void selectSearchItem() throws InterruptedException {
       JavascriptExecutor js=  (JavascriptExecutor) driver;
       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[1]/div[1]/div[8]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]/a[1]/div[1]/img[1]")));

       js.executeScript("arguments[0].scrollIntoView()", element);
       Thread.sleep(2000);
       element.click();
       String title = driver.getTitle();

       System.out.println(title);
   }
}
