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

        return new Object[][] {{"aaadadha","00001"},{"bbjfdbbb","00002"},{"cmgfcccc","00003"}};
}


//@Test(dataProvider = "DeleteBooksData")
//public void deletebook(String id){
//       RestAssured.baseURI="http://216.10.245.166";
//       String delresponse= given().log().all().header("Content-Type","application/json").
//               body(Payload.DeleteBook(id)).when().
//               post("/Library/DeleteBook.php").
//               then().log().all().assertThat().statusCode(200).extract().response().asString();
//
//       JsonPath js1= ReusabaleMethods.rawToString(delresponse);
////    String id1=js1.getString("ID");
////    System.out.println("Deleted Book ID: "+id);
//
//}
//@DataProvider(name="DeleteBooksData")
//    public Object[] getID(){
//
//       return new Object[] {"00001","00002", "00003"};
//}
}
