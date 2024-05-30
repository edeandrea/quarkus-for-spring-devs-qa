package io.erd.qforsqa;

import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import io.smallrye.common.annotation.RunOnVirtualThread;

@Path("/question")
public class BookQueryResource {
	private final BookQueryService queryService;

	public BookQueryResource(BookQueryService queryService) {
		this.queryService = queryService;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@RunOnVirtualThread
	public String askQuestion(@QueryParam("question") @NotEmpty String question) {
		return this.queryService.askQuestionAboutBook(question);
	}
}
