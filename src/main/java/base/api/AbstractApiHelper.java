package base.api;

import java.io.IOException;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractApiHelper {

    public abstract RequestSpecification requestSpecification() throws IOException;

    public abstract String getPropertiesValue(String key) throws IOException;

    public abstract String getJsonPath(Response response, String key);
}
