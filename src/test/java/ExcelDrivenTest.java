import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ExcelDrivenTest {

    @Test
    public void addBook() throws IOException {
        DataDriven d = new DataDriven();
        ArrayList data = d.getData("RestAddbook");


        HashMap<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", data.get(1));
        jsonAsMap.put("isbn", data.get(2));
        jsonAsMap.put("aisle", data.get(3));
        jsonAsMap.put("author", data.get(4));

        RestAssured.baseURI = "http://216.10.245.166";
        Response resp = given().log().all()
                .header("Content-Type", "application/json")
                .body(jsonAsMap)
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        JsonPath js = ReusableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);
        //String responseString = resp.asString();
        //JsonPath js = new JsonPath(String.valueOf(resp));
        //String id = js.get("ID");
      //
      // String js = getJsonPath(resp, "ID");
      // String id = js.get("ID");
       // System.out.println(id);


    }

}
