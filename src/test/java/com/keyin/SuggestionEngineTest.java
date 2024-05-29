package com.keyin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine;
    private static final Logger logger = Logger.getLogger(SuggestionEngineTest.class.getName());

    @Test
    public void testGenerateSuggestions() {
        suggestionEngine = new SuggestionEngine();
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Failed to load dictionary data", e);
            fail("Failed to load dictionary data: " + e.getMessage());
        }

        String word = "crroect";
        String expectedSuggestions = "correct\narrect";
        String actualSuggestions = suggestionEngine.generateSuggestions(word);
        assertEquals(expectedSuggestions, actualSuggestions);
    }

    @Test
    public void testGenerateSuggestions_ReturnsEmptyList() {
        suggestionEngine = new SuggestionEngine();
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Failed to load dictionary data", e);
            fail("Failed to load dictionary data: " + e.getMessage());
        }

        String word = "correct";
        String expectedSuggestions = "";
        String actualSuggestions = suggestionEngine.generateSuggestions(word);
        assertEquals(expectedSuggestions, actualSuggestions);
    }

    @Test
    public void testGenerateSuggestions_ForEmptyWord() {
        suggestionEngine = new SuggestionEngine();
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Failed to load dictionary data", e);
            fail("Failed to load dictionary data: " + e.getMessage());
        }

        String word = "";
        String expectedSuggestions = "a\nb\nc\nd\ne\nf\ng\nh\ni\nj";
        String actualSuggestions = suggestionEngine.generateSuggestions(word);
        assertEquals(expectedSuggestions, actualSuggestions);
    }

    @Test
    public void testGenerateSuggestions_WhiteSpaceTest() {
        suggestionEngine = new SuggestionEngine();
        try {
            suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Failed to load dictionary data", e);
            fail("Failed to load dictionary data: " + e.getMessage());
        }

        String word = " ";
        String expectedSuggestions = "a\nb\nc\nd\ne\nf\ng\nh\ni\nj";
        String actualSuggestions = suggestionEngine.generateSuggestions(word);
        assertEquals(expectedSuggestions, actualSuggestions);
    }
}
