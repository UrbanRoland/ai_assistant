package com.ai_assistant.commands;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;
import org.springframework.shell.command.annotation.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command
public class SpringAssistantCommand {
    
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    @Value("classpath:/prompts/rag-prompt-template.st")
    private Resource promptTemplate;
    
    public SpringAssistantCommand(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }
    
    @Command(command = "q", description = "Processes a user question and generates a response using the AI chat client")
    public String question(@DefaultValue(value = "What is Spring Boot") String message) {
        PromptTemplate promptTemplate = new PromptTemplate(this.promptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));
        
        return chatClient.call(promptTemplate.create(promptParameters))
            .getResult()
            .getOutput()
            .getContent();
    }
    
    @Command(command = "weather", description = "Get the current weather conditions for the given city.")
    public String cities(String message) {
        SystemMessage systemMessage = new SystemMessage("You are a helpful AI Assistant answering questions about cities around the world.");
        UserMessage userMessage = new UserMessage(message);
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
            .withFunction("currentWeatherFunction")
            .build();
        ChatResponse response = chatClient.call(new Prompt(List.of(systemMessage,userMessage), chatOptions));
        return response.getResult().getOutput().getContent();
    }
    
    
    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        return similarDocuments.stream().map(Document::getContent).toList();
    }

}