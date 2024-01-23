package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    WebDriver driver;
    public LoginPage(WebDriver d){
        this.driver=d;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="nav-link-accountList-nav-line-1")
     WebElement signInIcon;

    @FindBy(id="continue")
    WebElement continueButton;

    @FindBy(id ="ap_email" )
    private WebElement email;

    @FindBy(id = "ap_password")
     private WebElement password;

    @FindBy(id = "signInSubmit")
   private  WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Sign Out')]")
    private  WebElement signoutButton;

    public void loginAmazon(String userName,String pwd) throws InterruptedException {

        // Wait until the search box is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInIcon)).click();
        email.sendKeys(userName);
        continueButton.click();
        password.sendKeys(pwd);
//        Thread.sleep(3000);
        loginButton.click();

    }
    public void logout(){
        Actions actions = new Actions(driver);
        actions.moveToElement(signInIcon).build().perform();
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(signoutButton)).click();


    }

}
