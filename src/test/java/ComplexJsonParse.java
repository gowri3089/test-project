import io.restassured.path.json.JsonPath;
import Body.Payload;
import org.testng.Assert;

import java.awt.*;

public class ComplexJsonParse {

    public static void main(String[] args){
        JsonPath js= new JsonPath(Payload.CoursePrice());

       int count= js.getInt("courses.size()");
        System.out.println("Courses Count is "+count);

        int purchaseAmount= js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount is "+purchaseAmount);

        String firstCourse = js.getString("courses[0].title");
        System.out.println("First Course is : "+firstCourse);

        for (int i=0;i<count;i++){
            System.out.println(js.getString("courses["+i+"].title").toString());
            System.out.println(js.get("courses["+i+"].price").toString());

        }

        for (int j=1;j<count;j++){

            String courseTitle=js.get("courses["+j+"].title");
            if (courseTitle.equalsIgnoreCase("RPA")) {

                System.out.println("Number of copies for RPA is: "+js.get("courses["+j+"].copies").toString());
                break;
            }
        }
int amount=0;
        for (int i=0;i<count;i++) {

            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            amount = amount +( price * copies);
        }
       /* if (amount==purchaseAmount)
            System.out.println("Sum of all Course prices matches with Purchase Amount");
        else
            System.out.println("Sum of all Course prices does not match with Purchase Amount");*/

        org.testng.Assert.assertEquals(amount,purchaseAmount);

    }

}
