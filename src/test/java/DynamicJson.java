import Body.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ReusabaleMethods;
import static io.restassured.RestAssured.given;

public class DynamicJson {

   @Test (dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){

        RestAssured.baseURI="http://216.10.245.166";
        String response = given().log().all().header("Content-Type","application/json").
                body(Payload.AddBook(isbn,aisle)).when().
                post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js= ReusabaleMethods.rawToString(response);
        String id=js.getString("ID");
        System.out.println("Book ID is: "+id);

    }
@DataProvider(name="BooksData")

    public Object[][] getData() {

        return new Object[][] {{"aaaaha","118111"},{"bbjbbb","228222"},{"cmcccc","33303"}};
}
}
