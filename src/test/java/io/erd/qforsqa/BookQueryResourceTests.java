package io.erd.qforsqa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;

@QuarkusTest
class BookQueryResourceTests {
	@InjectMock
	BookQueryService queryService;

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

		verifyNoInteractions(this.queryService);
	}

	@Test
	void validQuestion() {
		var question = "What's the best Quarkus book?";
		var answer = "Quarkus for Spring Developers";

		when(this.queryService.askQuestionAboutBook(question))
			.thenReturn(answer);

		given()
			.accept(ContentType.TEXT)
			.queryParam("question", question)
			.get("/question")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.contentType(ContentType.TEXT)
			.body(is(answer));

		verify(this.queryService).askQuestionAboutBook(question);
	}
}