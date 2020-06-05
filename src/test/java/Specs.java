import com.com.endpoint.Endpoint;
import io.restassured.http.Header;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

public class Specs extends BaseTest{
    SoftAssert softAssert=new SoftAssert();
    @Test
    public void verifyCategories(){
        Header header=new Header("user-key","89ea4ede8e543bad88fa07ccbc3ceec6");
        response = apiUtils.getRequest(Endpoint.CATEGORIES,header);
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void verifyWithInvalidKeyCategories(){
        Header header=new Header("user-key","89ea4ede8e543bawqfd88fa07ccbc3ceec6");
        response = apiUtils.getRequest(Endpoint.CATEGORIES,header);
        response.then().assertThat().statusCode(403)
                .body("message",equalTo("Invalid API Key"))
                .body("status", equalTo("Forbidden"));
    }
    @Test
    public void verifyCategoryWithInvalidURI(){
        Header header=new Header("user-key","89ea4ede8e543bawqfd88fa07ccbc3ceec6");
        response = apiUtils.getRequest(Endpoint.CATEGORIES+"esddd",header);
        response.then().assertThat().statusCode(404);
    }
    @Test
    public void verifyCities(){
        Header header=new Header("user-key","89ea4ede8e543bad88fa07ccbc3ceec6");
        response=apiUtils.getRequest(Endpoint.CIIIES,header);
        response.then().assertThat().statusCode(200)
        .body("user_has_addresses",equalTo(true));
    }
    @Test
    public void verifySpecificCity(){
        Header header=new Header("user-key","89ea4ede8e543bad88fa07ccbc3ceec6");
        HashMap param=new HashMap();
        param.put("q","bhopal");
        response=apiUtils.getRequest(Endpoint.CIIIES,header,param);
        response.then().assertThat().statusCode(200)
                .body("location_suggestions.name.toString()",containsString("Bhopal"))
                .body("user_has_addresses",equalTo(true));
    }
    @Test
    public void verifyCityByCityIds(){
        Header header=new Header("user-key","89ea4ede8e543bad88fa07ccbc3ceec6");
        HashMap param=new HashMap();
        param.put("city_ids","25,26,27");
        response=apiUtils.getRequest(Endpoint.CIIIES,header,param);
        response.then().assertThat().statusCode(200);
        String city_ids =response.getBody().jsonPath().getString("location_suggestions.id");
        softAssert.assertEquals(city_ids,"[25, 26, 27]");
        softAssert.assertAll();
    }
}
