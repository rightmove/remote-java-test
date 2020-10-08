package com.rightmove;

import com.rightmove.datasetup.PropertyEntityBuilder;
import com.rightmove.property.data.PropertyDao;
import com.rightmove.property.data.PropertyEntity;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	private PropertyDao propertyDao;

	@LocalServerPort
	private int serverPort;

	@BeforeEach
	void setup() {
		RestAssured.port = serverPort;
		RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig());
	}

	@AfterEach
	void tearDown() {
		propertyDao.clear();
	}

	@Disabled
	@Test
	void shouldFindPropertyByPostcode() {
		PropertyEntity property = PropertyEntityBuilder.aDefaultPropertyEntity(1).postcode("W1D 3QU").build();
		PropertyEntity property2 = PropertyEntityBuilder.aDefaultPropertyEntity(2).postcode("W1F 3FT").build();
		propertyDao.save(property);
		propertyDao.save(property2);

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("postcode", "W1D 3QU")
				.get("/property")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertThat(jsonPath.getList("properties")).hasSize(1);
		assertThat(jsonPath.getLong("properties[0].id")).isEqualTo(1L);
	}

	@Test
	void shouldNotFindPropertyWherePostcodeNotFound() {
		PropertyEntity property = PropertyEntityBuilder.aDefaultPropertyEntity(1).postcode("W1D 3QU").build();
		PropertyEntity property2 = PropertyEntityBuilder.aDefaultPropertyEntity(2).postcode("W1F 3FT").build();
		propertyDao.save(property);
		propertyDao.save(property2);

		JsonPath jsonPath = given()
				.when()
				.accept(ContentType.JSON)
				.queryParam("postcode", "WA11 9RW")
				.get("/property")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.extract()
				.jsonPath();

		assertThat(jsonPath.getList("properties")).hasSize(0);
	}
}
