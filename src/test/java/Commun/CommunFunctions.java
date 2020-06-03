package Commun;
import Pages.OfferPage;
import com.google.common.collect.ObjectArrays;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CommunFunctions {

    protected WebDriver driver;
    @FindBy(tagName ="h2")
    WebElement heading;
    @FindBy (xpath="/html/body/div[1]/div/main/div/div[1]/div/h2")
    WebElement OfferHeading;
    @FindBy(tagName="h1")
    WebElement LogOutHeading;
    @FindBy (xpath="//div[@class='title--secondary text--weight-light text--color-green section--sm']")
    WebElement LogInHeading;


    public CommunFunctions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean isPageOpened(String Title, String PageName) {
        WebElement header = null;
        if(PageName == "OfferPage"){
        header = OfferHeading;
        }
        else if (PageName == "LogOutPage"){
            header = LogOutHeading;
        }
        else if (PageName == "LogInPage"){
            header = LogInHeading;
        }
        else{
            header = heading;
        }
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOf(header)
            );

            return element.getText().toString().contains(Title);

    }
    public void AssertTitle(String Title, String PageName) {

        Assert.assertTrue(isPageOpened(Title,PageName));
    }

    public String getelementText(WebElement strElement, String strElementText) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(strElement)
        );
        strElementText = element.getText().toString();
        return strElementText;
    }

    public void StoreInFile(String[] Result, String fileName) throws FileNotFoundException {
        String element ="";
        for (int i = 0; i < Result.length; i++) {
            PrintStream fileOut = new PrintStream(fileName);
            System.setOut(fileOut);
            element += Result[i] + "\n";
            System.out.println(element);
        }
    }

    public ArrayList<String> ReadFromFile() {
        ArrayList<String> Result = null;
        try {
            File myObj = new File("./Results.txt");
            Result = new ArrayList<String>();
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                Result.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return Result;
    }

    public HttpURLConnection Connexion(URL url,final String Request_Body) throws IOException {

        HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("x-cf-source-id","coding-challenge");
        postConnection.setRequestProperty("x-cf-corr-id","34c16f53-38c4-461a-bd14-11fa748d2663");
        postConnection.setRequestProperty("Content-Type","application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(Request_Body.getBytes());
        os.flush();
        os.close();
        return postConnection;
    }


}