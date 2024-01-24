package pages;

import org.example.exception.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;


public class SelectedItemPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='buy-now-button']")
    WebElement buyButton;

    @FindBy(css = "#quantity")
    WebElement dropdown;


    public SelectedItemPage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(driver, this);
    }


    public void buyTheItem() throws Exception {
        ArrayList<String> al = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(al.get(1));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            if (dropdown.isDisplayed()) {
                Select select = new Select(dropdown);
                select.selectByValue("2");

            }
        } catch (Exception e) {
            System.out.println("quantity dropdown is not present");
        }
        wait.until(ExpectedConditions.visibilityOf(buyButton)).click();


    }

}
