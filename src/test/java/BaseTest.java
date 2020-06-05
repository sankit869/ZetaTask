import com.utils.APIUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    APIUtils apiUtils = new APIUtils();
    Response response;
    Properties prob;
    @BeforeClass
    public void BaseUrlSetup() throws IOException {
        apiUtils.loadProperties();
        RestAssured.baseURI = apiUtils.baseurl;
    }
}
