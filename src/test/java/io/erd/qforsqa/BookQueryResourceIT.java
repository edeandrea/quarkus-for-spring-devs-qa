package io.erd.qforsqa;

import static io.restassured.RestAssured.given;

import java.util.Map;

import jakarta.ws.rs.core.Response.Status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

import io.erd.qforsqa.BookQueryResourceIT.ITProfile;
import io.restassured.http.ContentType;

@QuarkusIntegrationTest
@TestProfile(ITProfile.class)
class BookQueryResourceIT {
	@ParameterizedTest
	@ValueSource(strings = { " ", "    " })
	@NullAndEmptySource
	void invalidQuestion(String invalidQuestion) {
		given()
			.queryParam("question", invalidQuestion)
			.accept(ContentType.TEXT)
			.get("/question")
			.then()
			.statusCode(Status.BAD_REQUEST.getStatusCode());
	}

	public static class ITProfile implements QuarkusTestProfile {
		@Override
  	public Map<String, String> getConfigOverrides() {
  		return Map.of(
//				"quarkus.vertx.max-event-loop-execute-time", "3m",
			  "quarkus.langchain4j.easy-rag.ingestion-strategy", "off"
		  );
  	}
	}
}
