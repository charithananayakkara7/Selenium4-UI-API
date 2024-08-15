package serviceObjects;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import payloads.*;
import base.api.BaseApi;
import endpoints.apiendpoints;
import io.restassured.response.Response;

public class ChargePointService extends BaseApi {
    private requestpayloads data = new requestpayloads();
    public static String serial_id;

    public void addSerialNumPayload(String serialnumber) throws IOException {
        req = given().spec(requestSpecification())
                .body(data.addSerialNumberPayload(serialnumber));
    }

    public Response makeApiRequest(String method) throws Exception {
        switch (method.toUpperCase()) {
            case "POST":
                return req.when().post(apiendpoints.addSerialNumberAPI.getResource());
            case "DELETE":
                return given().spec(requestSpecification()).delete(apiendpoints.DeleteSerialNumberAPI.getResource()+ serial_id);
            case "GET":
                return req.when().get(apiendpoints.getSerialNumberAPI.getResource());
            default:
                throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }

    }

}
