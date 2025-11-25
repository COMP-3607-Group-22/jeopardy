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

import com.project.Parsing.CSVParserAdaptee;
import com.project.Questions.Question;

public class CSVParserAdapteeTest {

    private CSVParserAdaptee parser;
    private Path testDir;

    @BeforeEach
    void setUp() throws IOException {
        parser = new CSVParserAdaptee();
        testDir = Files.createTempDirectory("csv_test_");
    }

    @Nested
    @DisplayName("Core CSV Tests")
    class CoreTests {

        @Test
        @DisplayName("Parse single valid CSV row")
        void testSingleValidParse() throws IOException {
            String csv = "Category,Value,Question,OptionA,OptionB,OptionC,OptionD,CorrectAnswer\n" +
                    "Science,100,What is H2O?,Water,Oxygen,Hydrogen,Salt,Water\n";
            Path f = createTestFile("single.csv", csv);

            ArrayList<Question> qs = parser.parse(f.toString());

            assertEquals(1, qs.size());
            assertEquals("Science", qs.get(0).getCategory());
        }

        @Test
        @DisplayName("Skip invalid row and parse others")
        void testSkipInvalidRow() throws IOException {
            String csv = "Category,Value,Question,OptionA,OptionB,OptionC,OptionD,CorrectAnswer\n" +
                    "Science,100,What is H2O?,Water,Oxygen,Hydrogen,Salt,\n" +
                    "History,200,Who was first?,Lincoln,Washington,Jefferson,Kennedy,Washington\n";
            Path f = createTestFile("skip.csv", csv);

            ArrayList<Question> qs = parser.parse(f.toString());

            assertEquals(1, qs.size());
            assertEquals("History", qs.get(0).getCategory());
        }

        @Test
        @DisplayName("Non-existent file returns empty list")
        void testNonExistentFile() {
            ArrayList<Question> qs = parser.parse("nonexistent/file.csv");
            assertEquals(0, qs.size());
        }
    }

    private Path createTestFile(String name, String content) throws IOException {
        Path p = testDir.resolve(name);
        Files.write(p, content.getBytes());
        return p;
    }
}
