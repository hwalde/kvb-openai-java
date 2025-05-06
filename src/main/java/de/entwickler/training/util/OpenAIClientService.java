package de.entwickler.training.util;

import io.github.sashirestela.openai.SimpleOpenAI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OpenAIClientService {
    private static final Logger logger = LoggerFactory.getLogger(OpenAIClientService.class);

    private SimpleOpenAI simpleOpenAI;

    private static OpenAIClientService instance;

    private record OpenAiProperties(String baseUrl, String apiKey) {}

    private OpenAIClientService() {
    }

    public static synchronized OpenAIClientService getInstance() {
        if (instance == null) {
            instance = new OpenAIClientService();
        }
        return instance;
    }

    public SimpleOpenAI getOpenAIClient() {
        if(simpleOpenAI == null) {
            OpenAiProperties openAiProperties = getOpenAiProperties();
            simpleOpenAI = SimpleOpenAI.builder()
                    // .baseUrl(openAiProperties.baseUrl())
                    .apiKey(openAiProperties.apiKey())
                    .build();
        }
        return simpleOpenAI;
    }

    private OpenAiProperties getOpenAiProperties() {
        Properties prop = new Properties();
        String fileName = "config.properties"; // Name of the properties file in the classpath

        // Use try-with-resources to ensure the InputStream is closed automatically
        try (InputStream input = OpenAIClientService.class.getClassLoader().getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + fileName);
            }

            // Load the properties file from the InputStream
            prop.load(input);

            // Get property values
            if(!prop.containsKey("base_url")) {
                throw new RuntimeException("Sorry, unable to find base_url in " + fileName);
            }
            if(!prop.containsKey("api_key")) {
                throw new RuntimeException("Sorry, unable to find api_key in " + fileName);
            }
            String baseUrl = prop.getProperty("base_url");
            String apiKey = prop.getProperty("api_key");
            if(baseUrl.isEmpty() || apiKey.isEmpty()) {
                throw new RuntimeException("Sorry, base_url or api_key is empty in " + fileName);
            }

            // You can also iterate through all properties
            //System.out.println("\nAll Properties:");
            //prop.forEach((key, value) -> System.out.println(key + "=" + value));

            return new OpenAiProperties(baseUrl, apiKey);
        } catch (IOException ex) {
            throw new RuntimeException("Sorry, unable to read " + fileName, ex);
        }
    }
}