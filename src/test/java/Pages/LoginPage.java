package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public WebDriver driver;
    //Page URL
    private static String Login_URL="https://www.credify.tech/portal/login";

    @FindBy(name = "username")
    WebElement Username;

    @FindBy(name="password")
    WebElement Password;

    @FindBy(xpath ="//button[@data-auto='login']")
    WebElement LoginButton;


    public LoginPage(WebDriver driver){
        this.driver=driver;
        driver.get(Login_URL);
        PageFactory.initElements(driver, this);

    }

    public void setUsername(String User) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(Username)
        );
        element.sendKeys(User);
    }

    public void setPassword(String pwd) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(Password)
        );
        element.sendKeys(pwd);
    }
    public void cilckLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(LoginButton)
        );
        element.click();
    }

}
