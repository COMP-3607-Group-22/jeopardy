package com.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.project.Categories.Question;
import com.project.Parsing.JSONParserAdaptee;

public class JSONParserAdapteeTest {

    private JSONParserAdaptee parser;
    private Path testDir;

    @BeforeEach
    void setUp() throws IOException {
        parser = new JSONParserAdaptee();
        testDir = Files.createTempDirectory("json_test_");
    }

    @Nested
    @DisplayName("Core JSON Tests")
    class CoreTests {

        @Test
        @DisplayName("Parse single valid JSON object in array")
        void testSingleValidParse() throws IOException {
            String json = "[{\"Category\":\"Science\",\"Value\":100,\"Question\":\"Q?\",\"Options\":{\"A\":\"A\",\"B\":\"B\",\"C\":\"C\",\"D\":\"D\"},\"CorrectAnswer\":\"A\"}]";
            Path f = createTestFile("single.json", json);

            ArrayList<Question> qs = parser.parse(f.toString());

            assertEquals(1, qs.size());
            assertEquals("Science", qs.get(0).getCategory());
        }

        @Test
        @DisplayName("Skip object missing required fields and parse others")
        void testSkipInvalidObject() throws IOException {
            String json = "[" +
                    "{\"Category\":\"Science\",\"Value\":100,\"Question\":\"Q1?\",\"Options\":{\"A\":\"A\",\"B\":\"B\",\"C\":\"C\",\"D\":\"D\"},\"CorrectAnswer\":\"A\"}," +
                    "{\"Category\":\"History\",\"Value\":200,\"Question\":\"Q2?\",\"Options\":{\"A\":\"A\",\"B\":\"B\",\"C\":\"C\",\"D\":\"D\"}}," +
                    "{\"Category\":\"Math\",\"Value\":300,\"Question\":\"Q3?\",\"Options\":{\"A\":\"A\",\"B\":\"B\",\"C\":\"C\",\"D\":\"D\"},\"CorrectAnswer\":\"C\"}" +
                    "]";
            Path f = createTestFile("skip.json", json);

            ArrayList<Question> qs = parser.parse(f.toString());

            assertEquals(2, qs.size());
            assertEquals("Science", qs.get(0).getCategory());
            assertEquals("Math", qs.get(1).getCategory());
        }

        @Test
        @DisplayName("Non-existent file returns empty list")
        void testNonExistentFile() {
            ArrayList<Question> qs = parser.parse("nonexistent/file.json");
            assertEquals(0, qs.size());
        }
    }

    private Path createTestFile(String name, String content) throws IOException {
        Path p = testDir.resolve(name);
        Files.write(p, content.getBytes());
        return p;
    }
}
