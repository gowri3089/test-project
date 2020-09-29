import Body.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import util.ReusabaleMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostOperation {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String (Files.readAllBytes(Paths.get("/Users/gowri/Desktop/AddPlace.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

        System.out.println(response);
        //JsonPath js = new JsonPath(response);
        JsonPath js=ReusabaleMethods.rawToString(response);
        String placeid = js.getString("place_id");
        String ID = js.getString("id");
        System.out.println("Place ID is " + placeid);
        System.out.println(ID);

        String newAddress = "Kalpataru, 2203 SE S St, Bentonville AR 72712";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeid + "\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

    String getPlaceResponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
            .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

    JsonPath js1= ReusabaleMethods.rawToString(getPlaceResponse);
    String actualAddress = js1.getString("address");
    System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);

    }
}
