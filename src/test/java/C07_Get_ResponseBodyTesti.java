import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.given;

public class C07_Get_ResponseBodyTesti {

    @Test
    public void test01(){

        /* https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
         donen Response’in
            status code'unun 200,
            ve content type'inin Aplication.JSON,
            ve response body'sinde bulunan userId'nin 5,
            ve response body'sinde bulunan title'in "optio dolor molestias sit"

         */
        // 1-End point ve request body hazirla
        String url="https://jsonplaceholder.typicode.com/posts/44";

        // 2- Expected data hazırla

        // 3- request yollayıp dönen response'sı kaydet

        Response response = given().when().get(url);

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", Matchers.equalTo("optio dolor molestias sit"))
                .body("userId",Matchers.equalTo(5));
    }
}
