import Commun.CommunFunctions;
import Pages.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;


public class UpgradeWebTesting {
    WebDriver driver;
    String strLoan, strAPR, strLoanInterestRate, strTerm, strMonthlyPayment;
    String [] Results;


    @BeforeTest
    public void OpenChromePage()  {
        System.setProperty("webdriver.chrome.driver", "C://Apps//chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C://Apps//chrome-win//chrome.exe");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);

    }

    @Test
    public void UPgradeWebTest() throws FileNotFoundException {
        MainPage FormPage = new MainPage(driver);
        CommunFunctions CommunFctns = new CommunFunctions(driver);
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("Personal Loans up to $35,000", "MainPage");
        //Fill the Loan information
        FormPage.FillLoanAmount("2000");
        FormPage.SetLoanPurpose("Debt Consolidation");
        // Click on the Check Your rate button to send the information
        FormPage.ClickCheckButton();
        //The "Let's get started with some basic information" will be displayed
        InformationPage InfoPage = new InformationPage(driver);
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("Let's get started with some basic information", "InfoPage");
        //Fill the borrower information
        InfoPage.FillFirstName("Frodon");
        InfoPage.FillLastName("Hobbit");
        InfoPage.FillStreet("20 West 34th Street");
        InfoPage.FillCity("New York");
        InfoPage.FillState("NY");
        InfoPage.FillZipCode("10001");
        InfoPage.FillDateOfBirth("01/01/1960");
        //Click on Continue button to send the information
        InfoPage.ClickContinueButton();
        //The Incomes Page will be displayed
        IncomePage IncomesPage = new IncomePage(driver);
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("How much money do you make in a year?", "IncomesPage");
        //Fill the borrower Income
        IncomesPage.setIncome("200000");
        IncomesPage.setAdditionalIncome("10000");
        //Click on Continue button to send the information
        IncomesPage.ClickButtonContinue();
        //The subscription Page will be displayed
        SignInPage SignIn = new SignInPage(driver);
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("Last step before you get your rate", "SignIn");
        //Set the credentials
        SignIn.setUsername("candidate001@upgrade-challenge.com");
        SignIn.setPassword("AbCd1234");
        SignIn.clickCheckBox();
        //Click on Continue button to send the information
        SignIn.cilckCheckButton();
        //The offer Page will be displayed
        OfferPage Offer = new OfferPage(driver);
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("You qualify for a discount when you pick a credit card payoff offer!", "OfferPage");
        //Get The offer information from the page
        String Loan = Offer.getLoanAmount(strLoan);
        String APR = Offer.getAPR(strAPR);
        String LoanInterestRate = Offer.getLoanInterestRate(strLoanInterestRate);
        String Term = Offer.getLoanTerm(strTerm);
        String MonthlyPayment = Offer.getMonthlyPayment(strMonthlyPayment);
        //Store the information in a Array
        Results= new String[]{Loan, APR, LoanInterestRate, Term, MonthlyPayment};
        //Write the stored array in the Result text file
        CommunFctns.StoreInFile(Results, "./Results.txt");
        //LogOut from the offer Page
        Offer.LogOutAction();
        //Assert that the borrower has been successfully logged out
        CommunFctns.AssertTitle("You've been successfully logged out.\n" +
                "See you later.", "LogOutPage");
        //Open the Login Page
        LoginPage Login = new LoginPage(driver);
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("Welcome Back!", "LogInPage");
        //Enter the credentials
        Login.setUsername("candidate001@upgrade-challenge.com");
        Login.setPassword("AbCd1234");
        //Click on Login button
        Login.cilckLoginButton();
        //The offer Page will be displayed
        //Assert that the Page is correctly Loaded and its title is displayed
        CommunFctns.AssertTitle("You qualify for a discount when you pick a credit card payoff offer!", "OfferPage");
        //Read the Result file and store its information in a Array list
        ArrayList<String> OfferResult= CommunFctns.ReadFromFile();
        //Compare the information stored in the Result file with the offer page information
        Assert.assertEquals(Offer.getLoanAmount(strLoan), OfferResult.get(0));
        Assert.assertEquals(Offer.getAPR(strAPR), OfferResult.get(1));
        Assert.assertEquals(Offer.getLoanInterestRate(strLoanInterestRate), OfferResult.get(2));
        Assert.assertEquals(Offer.getLoanTerm(strTerm), OfferResult.get(3));
        Assert.assertEquals(Offer.getMonthlyPayment(strMonthlyPayment), OfferResult.get(4));
           }

}
