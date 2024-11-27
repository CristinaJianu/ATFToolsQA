package tests.e2e;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import modelRequest.RequestAccount;
import modelResponse.ResponseAccountSuccess;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import services.AccountService;
import sharedData.Hooks;

public class CreateAccountE2ETest extends Hooks {

    @Test
    public void testMethod(){
        RequestAccount requestBody=new RequestAccount("CristinaAutomation", "CristinaAutomation123!");

        AccountService accountService = new AccountService();
        ResponseAccountSuccess response = accountService.createNewAccount(requestBody, "/Account/v1/User");

        //deschidem un browser si ne logam in contul creat
        LoginPage loginPage=new LoginPage(getDriver());
        loginPage.loginProcess(requestBody);

        ProfilePage profilePage=new ProfilePage(getDriver());
        profilePage.validateLoginProcess(requestBody);
    }
}
