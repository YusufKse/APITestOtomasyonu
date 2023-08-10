import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C09_Get_BodyTekrarlardanKurtulma {

    @Test
    public void test01(){

        /* https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde
           donen Response’un,

                status code’unun 200,
                ve content type’inin application-json,
                ve response body’sindeki
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice" ın 1000'den kucuk oldugunu,
                "depositpaid": false,
                 "additionalneeds" 'in degerinin boş bırakılmamış

                oldugunu test edin

         */

        // 1- end point ve request body hazırla
        String url="https://restful-booker.herokuapp.com/booking/10";

        // 2 Expected data hazırla

        // 3-request gönder donen response'ı kaydet
        Response response= given().when().get(url);
        response.prettyPrint();

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Jim")
                        ,"lastname",equalTo("Brown")
                        ,"totalprice",lessThan(1000)
                        ,"depositpaid",equalTo(true)
                        ,"additionalneeds",notNullValue());


    }
}
