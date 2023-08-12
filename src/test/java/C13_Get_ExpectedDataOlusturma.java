import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C13_Get_ExpectedDataOlusturma {

    @Test
    public void test01(){

        /*
             https://jsonplaceholder.typicode.com/posts/22 url'ine
             bir GET request yolladigimizda donen response body’sinin
            asagida verilen ile ayni oldugunutest ediniz
        Response body :

        {
        "userId": 3,
        "id": 22,
        "title": "dolor sint quo a velit explicabo quia nam",
        "body": "eos qui et ipsum ipsam suscipit autsed omnis non odioexpedita ear um mollitia molestiae aut atque rem suscipitnam impedit esse"
        }
         */

        // 1- endpoint ve request body hazırla
        String url="https://jsonplaceholder.typicode.com/posts/22";

        // 2- Expected data hazırla

        JSONObject expectedData= new JSONObject();
        expectedData.put("userId", 3);
        expectedData.put("id", 22);
        expectedData.put("title", "dolor sint quo a velit explicabo quia nam");
        expectedData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear \num mollitia molestiae aut atque rem suscipitnam impedit esse");


        // 3- request gonder ve donen response'ı kaydet

        Response response =given().when().get(url);
        response.prettyPrint();

        // 4- Assertion

       /*Bugüne kadar yaptıgımız yontemle de testimizi yapabilirz
        Ancak framework'umuzugelistirmeye devam etmeliyiz

        response
               .then()
               .assertThat()
               .body("userId",equalTo(3),
               "id",equalTo(22),
               "title",equalTo("dolor sint quo a velit explicabo quia nam"));

        */

        JsonPath responseJsonPath= response.jsonPath();

        Assert.assertEquals(expectedData.get("userId"),responseJsonPath.get("userId"));
        Assert.assertEquals(expectedData.get("id"),responseJsonPath.get("id"));
        Assert.assertEquals(expectedData.get("title"),responseJsonPath.get("title"));
        Assert.assertEquals(expectedData.get("userId"),responseJsonPath.get("userId"));

    }
}
