package Pages;
import Commun.CommunFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.xml.dom.Tag;

public class MainPage {

    public WebDriver driver;
    //Page URL
    private static String Upgrade_URL="https://www.credify.tech/phone/nonDMFunnel";

    @FindBy(name = "desiredAmount")
    WebElement desiredAmount;

    @FindBy(xpath="//select[@aria-label='Loan Purpose']")
    WebElement LoanPurpose;

    @FindBy(xpath ="//button[@data-auto='CheckYourRate']")
    WebElement CheckButton;

    public MainPage(WebDriver driver) {
        this.driver=driver;
        driver.get(Upgrade_URL);
        PageFactory.initElements(driver, this);
    }


    public void FillLoanAmount(String amount){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(desiredAmount)
        );
        element.sendKeys(amount);

    }
    public void SetLoanPurpose(String Purpose){

        Select Loan = new Select(LoanPurpose);
        Loan.selectByVisibleText(Purpose);

    }

    public void ClickCheckButton(){

        CheckButton.click();
    }

}