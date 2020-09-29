package util;

import io.restassured.path.json.JsonPath;

public class ReusabaleMethods {

    public static JsonPath rawToString (String resp){

        JsonPath js1=new JsonPath(resp);
        return js1;
    }
}
