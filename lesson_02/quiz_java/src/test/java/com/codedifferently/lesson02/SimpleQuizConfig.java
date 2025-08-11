package com.codedifferently.lesson02;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Simple QuizConfig for testing that loads from YAML file
 */
public class SimpleQuizConfig {

    private Map<String, List<String>> answersByProvider;
    private final BCrypt.Verifyer verifyer = BCrypt.verifyer();

    public SimpleQuizConfig(String yamlFilePath) {
        loadFromYaml(yamlFilePath);
    }

    @SuppressWarnings("unchecked")
    private void loadFromYaml(String yamlFilePath) {
        Yaml yaml = new Yaml();
        try {
            // Try to load from classpath first
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(yamlFilePath);
            if (inputStream == null) {
                // If not found in classpath, try as file path
                Path path = Paths.get(yamlFilePath);
                inputStream = Files.newInputStream(path);
            }

            Map<String, Object> data = yaml.load(inputStream);
            Map<String, Object> quiz = (Map<String, Object>) data.get("quiz");
            this.answersByProvider = (Map<String, List<String>>) quiz.get("answers");

        } catch (IOException e) {
            throw new RuntimeException("Failed to load quiz configuration from: " + yamlFilePath, e);
        }
    }

    public int size(String provider) {
        if (!answersByProvider.containsKey(provider)) {
            return 0;
        }
        return answersByProvider.get(provider).size();
    }

    public boolean checkAnswer(String provider, int questionNumber, String actualAnswer) {
        List<String> answers = answersByProvider.get(provider);
        if (answers == null || questionNumber >= answers.size()) {
            return false;
        }
        String expectedHash = answers.get(questionNumber);
        return verifyer.verify(actualAnswer.toCharArray(), expectedHash).verified;
    }
}
