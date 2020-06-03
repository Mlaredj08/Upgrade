package Pages;

import Commun.CommunFunctions;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferPage {
    private WebDriver driver;

    @FindBy(xpath="//span[@data-auto='userLoanAmount']")
    WebElement LoanAmount;

    @FindBy(xpath="//div[@data-auto='defaultMoreInfoAPR']")
    WebElement APR;

    @FindBy(xpath="//div[@data-auto='defaultLoanInterestRate']")
    WebElement LoanInterestRate;

    @FindBy(xpath="//div[@data-auto='defaultLoanTerm']")
    WebElement LoanTerm;

    @FindBy(xpath="//span[@data-auto='defaultMonthlyPayment']")
    WebElement MonthlyPayment;

    @FindBy(xpath="//label[@class='header-nav__toggle']")
    WebElement Menu;

    @FindBy(xpath="//a[text()='Sign Out']")
    WebElement LogOut;


    public OfferPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getLoanAmount(String userLoanAmount) {
        CommunFunctions Commun_Fct = new CommunFunctions(driver);
        userLoanAmount = Commun_Fct.getelementText(LoanAmount, userLoanAmount);
        return userLoanAmount;

    }
    public String getAPR(String defaultMoreInfoAPR) {
        CommunFunctions Commun_Fct = new CommunFunctions(driver);
        defaultMoreInfoAPR = Commun_Fct.getelementText(APR, defaultMoreInfoAPR);
        String[] InfoAPR = defaultMoreInfoAPR.split(" ");
        return InfoAPR[0];

    }
    public String getLoanInterestRate(String defaultLoanInterestRate) {
        CommunFunctions Commun_Fct = new CommunFunctions(driver);
        defaultLoanInterestRate = Commun_Fct.getelementText(LoanInterestRate, defaultLoanInterestRate);
        return defaultLoanInterestRate;

    }
    public String getLoanTerm(String defaultLoanTerm) {
        CommunFunctions Commun_Fct = new CommunFunctions(driver);
        defaultLoanTerm = Commun_Fct.getelementText(LoanTerm, defaultLoanTerm);
        return defaultLoanTerm;
    }
    public String getMonthlyPayment(String defaultMonthlyPayment) {
        CommunFunctions Commun_Fct = new CommunFunctions(driver);
        defaultMonthlyPayment = Commun_Fct.getelementText(MonthlyPayment, defaultMonthlyPayment);
        return defaultMonthlyPayment;
    }
    public void LogOutAction (){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement MenuButton = wait.until(
                ExpectedConditions.visibilityOf(Menu)
        );
        MenuButton.click();

        WebElement SignOut = wait.until(
                ExpectedConditions.visibilityOf(LogOut)
        );
        SignOut.click();
    }

}
