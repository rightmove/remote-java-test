package com.jesse.dockercomposecruddemo;

import com.jesse.dockercomposecruddemo.datasetup.PropertyEntityBuilder;
import com.jesse.dockercomposecruddemo.entity.PropertyEntity;
import com.jesse.dockercomposecruddemo.entity.PropertyType;
import com.jesse.dockercomposecruddemo.repository.PropertyRepository;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DockerComposeCrudDemoApplicationTests {

	@Autowired
	private PropertyRepository repository;

	@LocalServerPort
	private int serverPort;

	@BeforeEach
	void setup() {
		RestAssured.port = serverPort;
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig());
	}

	@AfterEach
	void tearDown() {
		repository.deleteAll();
	}

	@Test
	void shouldFindPropertyByPostcode() {
		PropertyEntity property = PropertyEntityBuilder.aDefaultPropertyEntity(1).postcode("W1D 3QU").build();
		PropertyEntity property2 = PropertyEntityBuilder.aDefaultPropertyEntity(2).postcode("W1F 3FT").build();
		repository.save(property);
		repository.save(property2);

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("postcode", "W1D 3QU")
				.get("/property")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertEquals(1, jsonPath.getList("properties").size());
	}

	@Test
	void shouldNotFindPropertyWherePostcodeNotFound() {
		PropertyEntity property = PropertyEntityBuilder.aDefaultPropertyEntity(1).postcode("W1D 3QU").build();
		PropertyEntity property2 = PropertyEntityBuilder.aDefaultPropertyEntity(2).postcode("W1F 3FT").build();
		repository.save(property);
		repository.save(property2);

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("postcode", "WA11 9RW")
				.get("/property")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertTrue(jsonPath.getList("properties").isEmpty());
	}

	@Test
	void shouldAddSingleProperty() {

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("price", "500000")
				.queryParam("bedrooms", "3")
				.queryParam("bathrooms", "5")
				.queryParam("number", "22")
				.queryParam("address", "Smith Road")
				.queryParam("region", "Lancashire")
				.queryParam("postcode", "IG4 RE3")
				.queryParam("propertytype", "Detached")
				.post("/property/add")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertEquals(1, jsonPath.getList("properties").size());
		assertEquals(500000L, jsonPath.getLong("properties[0].price"));
		assertEquals(3, jsonPath.getLong("properties[0].bedrooms"));
		assertEquals("22", jsonPath.getString("properties[0].number"));
		assertEquals("Smith Road", jsonPath.getString("properties[0].address"));
		assertEquals("Lancashire", jsonPath.getString("properties[0].region"));
		assertEquals("IG4 RE3", jsonPath.getString("properties[0].postcode"));
		assertEquals(PropertyType.DETACHED.toString(), jsonPath.getString("properties[0].type"));
	}

}
