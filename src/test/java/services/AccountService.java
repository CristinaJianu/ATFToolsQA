package services;

import client.ApiClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import modelRequest.RequestAccount;
import modelResponse.ResponseAccountGetSuccess;
import modelResponse.ResponseAccountSuccess;
import modelResponse.ResponseTokenSuccess;
import org.testng.Assert;

public class AccountService {

    public ResponseAccountSuccess createNewAccount(RequestAccount requestBody, String endpoint){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(requestBody);

        Response response = performRequest("post", requestSpecification, endpoint);
        response.getBody().prettyPrint();
        return response.body().as(ResponseAccountSuccess.class);
    }

    public ResponseTokenSuccess generateToken(RequestAccount requestBody, String endpoint){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(requestBody);

        Response response = performRequest("post", requestSpecification, endpoint);
        response.getBody().prettyPrint();
        return response.body().as(ResponseTokenSuccess.class);
    }

    public void validateNewAccount(String endpoint, String userId, String token){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);

        String finalEndpoint = endpoint + userId;
        Response response = performRequest("get", requestSpecification, finalEndpoint);
        response.getBody().prettyPrint();
        System.out.println(response.getStatusLine());

        if (response.getStatusCode() == 200){
            Assert.assertEquals(response.getStatusCode(), 200);
        } else {
            Assert.assertEquals(response.getStatusCode(), 401);
        }
    }

    public void deleteNewAccount(String endpoint, String userId, String token){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);

        String finalEndpoint = endpoint + userId;
        Response response = performRequest("delete", requestSpecification, finalEndpoint);
        response.getBody().prettyPrint();
        System.out.println(response.getStatusLine());
    }

    private Response performRequest(String requestType, RequestSpecification requestSpecification, String endpoint){
        return new ApiClient().performRequest(requestType, requestSpecification, endpoint);
    }
}
