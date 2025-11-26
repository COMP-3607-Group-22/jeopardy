package com.project;

import org.junit.jupiter.api.Test;

import com.project.Gameplay.GameEngine;
import com.project.Gameplay.GameTermination;
import com.project.Gameplay.Player;
import com.project.Helpers.CategoryManager;
import com.project.Helpers.ReportHelper;
import com.project.Questions.Question;
import com.project.Questions.QuestionBuilder;
import com.project.Questions.QuestionManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Report Generation Tests")
class ReportGenerationTest {

    private GameEngine gameEngine;
    private ReportHelper reportHelper;
    private GameTermination terminator;

    @BeforeEach
    void setup() {
        var questionManager = new QuestionManager();
        var categoryManager = new CategoryManager(questionManager);
        var players = new ArrayList<Player>();
        players.add(new Player("Batman"));
        players.add(new Player("Robin"));

        gameEngine = new GameEngine(categoryManager, players);
        reportHelper = gameEngine.getReportHelper();
        terminator = new GameTermination();

        addFakeTurn("Batman", "Functions", 500, "Python", true);
    }

    private void addFakeTurn(String playerName, String category, int value, String answer, boolean correct) {
        QuestionBuilder builder = QuestionBuilder.create();
        builder.setCategory(category);
        builder.setValue(value);
        builder.setQuestion("Test?");
        builder.setOptions(new ArrayList<>(List.of("Wrong", answer)));
        builder.setAnswer(answer);
        Question q = builder.build();

        for (Player p : gameEngine.getPlayers()) {
            if (p.getName().equals(playerName)) {
                try {
                    java.lang.reflect.Field field = GameEngine.class.getDeclaredField("currentPlayer");
                    field.setAccessible(true);
                    field.set(gameEngine, p);
                } catch (Exception ignored) {
                }
            }
        }

        try {
            setField(gameEngine, "currentCategory", category);
            setField(gameEngine, "currentQuestion", q);
            setField(gameEngine, "givenAnswer", correct ? answer : "Wrong");
        } catch (Exception ignored) {
        }
        gameEngine.addTurnSummary();
    }

    private void setField(Object obj, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    @Test
    @DisplayName("Report contains headers, players, turns and scores")
    void fullReportGeneration() {
        terminator.generateReport(reportHelper);

        List<String> report = reportHelper.getReport();
        String fullText = String.join("\n", report);

        assertTrue(fullText.contains("JEOPARDY PROGRAMMING GAME REPORT"));
        assertTrue(fullText.contains("Batman"));
        assertTrue(fullText.contains("Robin"));
        assertTrue(fullText.contains("Functions"));
        assertTrue(fullText.contains("Correct"));
        assertTrue(fullText.contains("Final Scores"));
        assertTrue(fullText.contains("Batman: "));
        assertTrue(fullText.contains("Robin: 0"));

    }

    @Test
    @DisplayName("Wrong answer is recorded in report")
    void wrongAnswerReportGeneration() {
        addFakeTurn("Robin", "Loops", 300, "Java", false);
        terminator.generateReport(reportHelper);

        List<String> report = reportHelper.getReport();
        String fullText = String.join("\n", report);

        assertTrue(fullText.contains("Loops"));
        assertTrue(fullText.contains("Incorrect"));
    }

}
