package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    //vreau sa ii configurez diferitele tipuri de requesturi
    public Response performRequest(String requestType, RequestSpecification requestSpecification, String endpoint){
        if (requestType.equals("post")){
            return prepareClient(requestSpecification).post(endpoint);
        }
        if (requestType.equals("get")){
            return prepareClient(requestSpecification).get(endpoint);
        }
        if (requestType.equals("delete")){
            return prepareClient(requestSpecification).delete(endpoint);
        }
        if (requestType.equals("put")){
            return prepareClient(requestSpecification).put(endpoint);
        }
        return null;
    }

    //vreau sa configurez clientul cu proprietatile default
    public RequestSpecification prepareClient(RequestSpecification requestSpecification){
        requestSpecification.baseUri("https://demoqa.com");
        requestSpecification.contentType(ContentType.JSON);
        return requestSpecification;
    }
}
