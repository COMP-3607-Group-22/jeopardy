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

import com.project.Parsing.XMLParserAdaptee;
import com.project.Questions.Question;

public class XMLParserAdapteeTest {

    private XMLParserAdaptee parser;
    private Path testDir;

    @BeforeEach
    void setUp() throws IOException {
        parser = new XMLParserAdaptee();
        testDir = Files.createTempDirectory("xml_test_");
    }

    @Nested
    @DisplayName("Core XML Tests")
    class CoreTests {

        @Test
        @DisplayName("Parse single valid QuestionItem")
        void testSingleValidParse() throws IOException {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Questions>\n" +
                    "  <QuestionItem>\n" +
                    "    <Category>Science</Category>\n" +
                    "    <Value>100</Value>\n" +
                    "    <QuestionText>Q?</QuestionText>\n" +
                    "    <Options><OptionA>A</OptionA><OptionB>B</OptionB><OptionC>C</OptionC><OptionD>D</OptionD></Options>\n" +
                    "    <CorrectAnswer>A</CorrectAnswer>\n" +
                    "  </QuestionItem>\n" +
                    "</Questions>";
            Path f = createTestFile("single.xml", xml);

            ArrayList<Question> qs = parser.parse(f.toString());
            assertEquals(1, qs.size());
            assertEquals("Science", qs.get(0).getCategory());
        }

        @Test
        @DisplayName("Skip item missing required fields and parse others")
        void testSkipInvalidItem() throws IOException {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Questions>\n" +
                    "  <QuestionItem>\n" +
                    "    <Category>X</Category>\n" +
                    "    <Value>100</Value>\n" +
                    "    <QuestionText>Q1</QuestionText>\n" +
                    "    <Options><OptionA>A</OptionA></Options>\n" +
                    "  </QuestionItem>\n" +
                    "  <QuestionItem>\n" +
                    "    <Category>Y</Category>\n" +
                    "    <Value>200</Value>\n" +
                    "    <QuestionText>Q2</QuestionText>\n" +
                    "    <Options><OptionA>A</OptionA><OptionB>B</OptionB><OptionC>C</OptionC><OptionD>D</OptionD></Options>\n" +
                    "    <CorrectAnswer>A</CorrectAnswer>\n" +
                    "  </QuestionItem>\n" +
                    "</Questions>";
            Path f = createTestFile("skip.xml", xml);

            ArrayList<Question> qs = parser.parse(f.toString());
            assertEquals(1, qs.size());
            assertEquals("Y", qs.get(0).getCategory());
        }

        @Test
        @DisplayName("Non-existent file returns empty list")
        void testNonExistentFile() {
            ArrayList<Question> qs = parser.parse("nonexistent/file.xml");
            assertEquals(0, qs.size());
        }
    }

    private Path createTestFile(String name, String content) throws IOException {
        Path p = testDir.resolve(name);
        Files.write(p, content.getBytes());
        return p;
    }
}
