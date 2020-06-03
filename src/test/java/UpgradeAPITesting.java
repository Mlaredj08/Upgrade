import Commun.CommunFunctions;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpgradeAPITesting {
    URL UpgradeLoginUrl = new URL("https://credapi.credify.tech/api/brportorch/v2/login");
    final String Body_payload = "{\n" + "\"username\": \"coding.challenge.login@upgrade.com\",\r\n" +
            "    \"password\":\"On$3XcgsW#9q\"" + "\n}";

    public UpgradeAPITesting() throws MalformedURLException {
    }

    @BeforeTest
    public HttpURLConnection Connexion() throws IOException {
        System.out.println(Body_payload);
        CommunFunctions Commun_fctn = new CommunFunctions(null);
        HttpURLConnection postConnection = Commun_fctn.Connexion(UpgradeLoginUrl, Body_payload);
        return postConnection;
    }

    @Test
    public void POSTRequestSucces() throws IOException, ParseException {
        //Send the Post Request
        HttpURLConnection postConnection = Connexion();
        //Store the Response Code
        int responseCode = postConnection.getResponseCode();
        String responseMessage = postConnection.getResponseMessage();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + responseMessage );
        //Assert that the response Code is equal to 200
        Assert.assertEquals(responseCode, 200);
        //assert that response message is OK
        Assert.assertEquals(responseMessage,"OK");
    }

    @Test(priority = 1)
    public void ParsingData () throws IOException, ParseException {
        //Send the Post Request
        HttpURLConnection postConnection = Connexion();
        //Store the Response Code
        int responseCode = postConnection.getResponseCode();
        //Get the response Body in case of success
        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            System.out.println(response.toString());

            //Parse the JSON data present in the string format
            JSONParser parse = new JSONParser();
            //Type caste the parsed json data in json object
            JSONObject jObj = (JSONObject)parse.parse(String.valueOf(response));
            //We can easily get each json value in the response payload individually using jObj.get method
            Object firstName =jObj.get("firstName");
            Object userId = jObj.get("userId");
            Object userUuid = jObj.get("userUuid");
            JSONArray loanApplications = (JSONArray) jObj.get("loanApplications");
            System.out.println("loanApplications:" +loanApplications);
            //Store the JSON object in JSON array as objects (For level 1 array element i.e loansInReview )
            JSONArray loansInReview = (JSONArray) jObj.get("loansInReview");
            for(int i=0;i<loansInReview.size();i++)
            {
                //Store the JSON objects in an array
                //Get the index of the JSON object and print the values as per the index
                JSONObject jsonObj = (JSONObject)loansInReview.get(i);
                Object id =jsonObj.get("id");
                Object uuid =jsonObj.get("uuid");
                Object status =jsonObj.get("status");
                Object ProductType =jsonObj.get("productType");
                Object sourceSystem =jsonObj.get("sourceSystem");
                Object asOpenBackendCounter =jsonObj.get("asOpenBackendCounter");
                Object purpose =jsonObj.get("purpose");
                Object createDate =jsonObj.get("createDate");
                Object postIssuanceStatus =jsonObj.get("postIssuanceStatus");
                System.out.println("productType: " +ProductType);
                // assert that the productType attribute has value PERSONAL_LOAN
                Assert.assertEquals(ProductType,"PERSONAL_LOAN");

            }
        }
    }

    @Test(priority = 2)
    public void BadCredentials() throws IOException {
        //send the PostRequest with the bad credentials
        String BadCrdtl = "{\n" + "\"username\": \"rundom@upgrade.com\",\r\n" +
                "    \"password\":\"AbCd1234\"" + "\n}";
        System.out.println(BadCrdtl);
        CommunFunctions Commun_fctn = new CommunFunctions(null);
        HttpURLConnection postConnection = Commun_fctn.Connexion(UpgradeLoginUrl, BadCrdtl);
        //Store the response code and response message
        int responseCode = postConnection.getResponseCode();
        String responseMessage = postConnection.getResponseMessage();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " +responseMessage);
        //assert that response code is equal to 401
        Assert.assertEquals(responseCode, 401);
        //assert that response message is Unauthorized
        Assert.assertEquals(responseMessage,"Unauthorized");

    }
}