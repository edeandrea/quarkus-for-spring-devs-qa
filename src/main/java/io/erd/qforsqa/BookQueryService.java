package io.erd.qforsqa;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
@SystemMessage("""
		You are an AI that knows everything about Spring and Quarkus from the book "Quarkus for Spring Developers".
		
		You will answer user questions using only references from the book.
		
		When you don't know the answer, respond that you don't know the answer.
		""")
public interface BookQueryService {
	String askQuestionAboutBook(@UserMessage String question);
}
