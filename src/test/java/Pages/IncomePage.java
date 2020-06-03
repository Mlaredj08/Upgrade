package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IncomePage {
    private WebDriver driver;

    @FindBy(name="borrowerIncome")
    WebElement Income;

    @FindBy(name="borrowerAdditionalIncome")
    WebElement AdditionalIncome;

    @FindBy(xpath="//button[text()='Continue']")
    WebElement ButtonContinue;

    public IncomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void setIncome(String IncomeAmount) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(Income)
        );
        element.sendKeys(IncomeAmount);
    }

    public void setAdditionalIncome(String AdditionalAmount) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(AdditionalIncome)
        );
        element.sendKeys(AdditionalAmount);
    }

    public void ClickButtonContinue() {
        WebDriverWait wait = new WebDriverWait(driver, 60);

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(ButtonContinue)

        );
        for (int i=0; i<2; i++) {
            element.click();
        }


    }
}
