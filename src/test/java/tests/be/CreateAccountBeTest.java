package tests.be;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import modelRequest.RequestAccount;
import modelResponse.ResponseAccountGetSuccess;
import modelResponse.ResponseAccountSuccess;
import modelResponse.ResponseTokenSuccess;
import org.testng.annotations.Test;
import services.AccountService;

public class CreateAccountBeTest {

    @Test
    public void testMethod(){
        System.out.println("STEP 1: CREATE NEW ACCOUNT");
        RequestAccount requestBody=new RequestAccount("CristinaAutomation", "CristinaAutomation123!");

        AccountService accountService = new AccountService();
        ResponseAccountSuccess responseAccount = accountService.createNewAccount(requestBody, "/Account/v1/User");
        String userId = responseAccount.getUserID();
        System.out.println();

        System.out.println("STEP 2: GENERATE TOKEN FOR NEW ACCOUNT");
        ResponseTokenSuccess responseToken = accountService.generateToken(requestBody, "/Account/v1/GenerateToken");
        String token = responseToken.getToken();
        System.out.println();

        System.out.println("STEP 3: VALIDATE NEW ACCOUNT");
        accountService.validateNewAccount("/Account/v1/User/", userId, token);
        System.out.println();

        System.out.println("STEP 4: DELETE NEW ACCOUNT");
        accountService.deleteNewAccount("/Account/v1/User/", userId, token);
        System.out.println();

        System.out.println("STEP 5: VALIDATE AGAIN NEW ACCOUNT");
        accountService.validateNewAccount("/Account/v1/User/", userId, token);
        System.out.println();



    }
}
