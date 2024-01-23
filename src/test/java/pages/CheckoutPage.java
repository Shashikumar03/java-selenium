package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPage {
     WebDriver driver;
    public  CheckoutPage(WebDriver d){
        this.driver=d;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h1[contains(text(),\'Checkout\')]")
    WebElement checkoutText;

    @FindBy(xpath = "//span[contains(text(),'Credit or debit card')]")
    WebElement cardElement;

    public void checkTextPage(){

        String chekcoutText = checkoutText.getText().toLowerCase();
        Assert.assertTrue(chekcoutText.equals("checkout"));
        System.out.println("checkot page this is");
    }
    public void checkCardOption() throws InterruptedException {
        String text = cardElement.getText().toLowerCase();
        Assert.assertTrue(text.contains("card"));
        System.out.println("card option is present");
        Thread.sleep(5000);

    }
}
