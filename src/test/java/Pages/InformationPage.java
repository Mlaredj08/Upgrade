package Pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InformationPage {
    private WebDriver driver;

    @FindBy(name = "borrowerFirstName")
    WebElement FirstName;

    @FindBy(name="borrowerLastName")
    WebElement LastName;

    @FindBy(name="borrowerStreet")
    WebElement Street;

    @FindBy(name="borrowerCity")
    WebElement City;

    @FindBy(name="borrowerState")
    WebElement State;

    @FindBy(name="borrowerZipCode")
    WebElement ZipCode;

    @FindBy(name="borrowerDateOfBirth")
    WebElement DoB;

    @FindBy(xpath ="//button[@data-auto='continuePersonalInfo']")
    WebElement ContinueButton;

    public InformationPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void FillFirstName(String First){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(FirstName)
        );
        element.sendKeys(First);
    }
    public void FillLastName(String Last){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(LastName)
        );
        element.sendKeys(Last);
    }
    public void FillStreet(String Adress){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement AdressInput = wait.until(
                ExpectedConditions.visibilityOf(Street)
        );
        AdressInput.sendKeys(Adress);
        AdressInput.sendKeys(Keys.RETURN);

    }
    public void FillCity(String city){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(City)
        );
        element.sendKeys(city);
    }
    public void FillState(String state){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(State)
        );
        element.sendKeys(state);
    }
    public void FillZipCode(String Zip){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(ZipCode)
        );
        element.sendKeys(Zip);
    }
    public void FillDateOfBirth(String DateOfBirth){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(DoB)
        );
        element.sendKeys(DateOfBirth);
    }

    public void ClickContinueButton(){
        ContinueButton.click();
    }
}