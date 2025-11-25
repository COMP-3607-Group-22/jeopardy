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

@DisplayName("Parser Adapter Tests")
public class ParserAdapterTest {

    private Path testDir;

    @BeforeEach
    void setUp() throws IOException {
        testDir = Files.createTempDirectory("adapter_test_");
    }

    @Nested
    @DisplayName("Format Detection & Delegation")
    class CoreTests {

        @Test
        @DisplayName("Delegates to CSV parser for CSV files")
        void testFormatDetection() throws IOException {
            String csv = "Category,Value,Question,OptionA,OptionB,OptionC,OptionD,CorrectAnswer\n" +
                    "S,100,Q?,a,b,c,d,a\n";
            Path fcsv = createTestFile("test.csv", csv);

            ParserAdapter adapter = new ParserAdapter(fcsv.toString());
            ArrayList<Question> qcsv = adapter.parse(fcsv.toString());
            assertEquals(1, qcsv.size());
        }

        @Test
        @DisplayName("Delegates to JSON parser for JSON files")
        void testDelegation() throws IOException {
            String json = "[{\"Category\":\"S\",\"Value\":100,\"Question\":\"Q?\",\"Options\":{\"A\":\"a\",\"B\":\"b\",\"C\":\"c\",\"D\":\"d\"},\"CorrectAnswer\":\"a\"}]";
            Path fjson = createTestFile("test.json", json);

            ParserAdapter adapter = new ParserAdapter(fjson.toString());
            ArrayList<Question> qjson = adapter.parse(fjson.toString());
            assertEquals(1, qjson.size());
        }
    }

    private Path createTestFile(String name, String content) throws IOException {
        Path p = testDir.resolve(name);
        Files.write(p, content.getBytes());
        return p;
    }
}
