package Pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;

    @FindBy(name="username")
    WebElement Username;

    @FindBy(name="password")
    WebElement Password;

    @FindBy(xpath="/html/body/div[1]/div/main/div/div[1]/div[2]/div[1]/div/div/form/div[2]/div/label/div[1]")
    WebElement AgreementCheckBox;

    @FindBy(xpath="//label[@data-checked='false']")
    WebElement CheckBoxState;

    @FindBy(xpath="//button[text()='Check Your Rate']")
    WebElement CheckButton;


    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
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

    public void clickCheckBox() {
        String ischecked = CheckBoxState.getAttribute("data-checked");
        Assert.assertEquals(ischecked, "false");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(AgreementCheckBox)
        );
            element.click();

    }

    public void cilckCheckButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(CheckButton)
        );
        element.click();
    }
}
