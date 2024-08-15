package base.api;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseApi extends AbstractApiHelper {
	private static final Logger logger = LogManager.getLogger(BaseApi.class);
	RequestSpecification res;
	ResponseSpecification resspec;
	public static RequestSpecification req;
	Response response;

	@Override
	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("log4jlogs/apilogs.log"));
			req = new RequestSpecBuilder().setBaseUri(getPropertiesValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
		}
		return req;

	}

	@Override
	public String getPropertiesValue(String key) throws IOException {
		Properties properties = new Properties();
		String filePath = "src/main/resources/apitest.local.properties";
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
			logger.debug("Properties file loaded successfully");
		} catch (IOException e) {
			logger.error("Error loading properties file: {}", filePath, e);
			throw new IOException("Error loading properties file: " + filePath, e);
		}

		String value = properties.getProperty(key);
		return value;
	}

	@Override
	public String getJsonPath(Response response, String key) {
		try {
			String responseBody = response.asString();
			logger.debug("Response body: {}", responseBody);

			JsonPath jsonPath = new JsonPath(responseBody);
			String value = jsonPath.get(key).toString();
			return value;
		} catch (Exception e) {
			logger.error("Error extracting value from JSON response", e);
			return null;
		}
	}
	
}
