package com.project;

import org.junit.jupiter.api.Test;

import com.project.Gameplay.Player;
import com.project.Questions.Question;
import com.project.Questions.QuestionBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("Gameplay and Scoring Tests")
class ScoringTest {

    private Player player;
    private Question question200;
    private Question question400;

    @BeforeEach
    void setup() {
        player = new Player("TestPlayer");
        question200 = createQuestion("Summer", 200);
        question400 = createQuestion("Fall", 400);
    }

    private Question createQuestion(String answer, int value) {
        QuestionBuilder builder = QuestionBuilder.create();
        builder.setCategory("Seasons");
        builder.setValue(value);
        builder.setQuestion("What season comes after spring?");
        builder.setOptions(new ArrayList<>(List.of("Summer", "Fall", "Winter", "Spring")));
        builder.setAnswer(answer);
        return builder.build();
    }

    @Nested
    @DisplayName("Correct Answer Tests")
    class CorrectAnswerTests {

        @Test
        @DisplayName("Correct Answer adds points")
        void correctAnswerAddsPoints() {
            player.setScore(question200.getValue());
            assertEquals(200, player.getScore());
        }

        @Test
        @DisplayName("Multiple Correct Answers adds points correctly")
        void multipleCorrectAnswers() {
            player.setScore(question200.getValue());
            player.setScore(question400.getValue());
            assertEquals(600, player.getScore());
        }

    }

    @Nested
    @DisplayName("Incorrect Answer Tests")
    class IncorrectAnswerTests {

        @Test
        @DisplayName("Incorrect Answer deducts points")
        void incorrectAnswerDeductsPoints() {
            player.setScore(-question200.getValue());
            assertEquals(-200, player.getScore());
        }

        @Test
        @DisplayName("Scores can go negative")
        void scoresCanGoNegative() {
            player.setScore(-question400.getValue());
            player.setScore(-question200.getValue());
            assertEquals(-600, player.getScore());
        }
    }

    @Nested
    @DisplayName("Edge Case Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Large Score Handling")
        void largeScoreHandling() {
            player.setScore(1_000_000);
            assertEquals(1_000_000, player.getScore());
        }
    }

    @Nested
    @DisplayName("Real Gameflow Simulation Tests")
    class RealGameFlow {
        @Test
        @DisplayName("An average game, some correct and some incorrect answers")
        void averageGameFlow() {
            player.setScore(question200.getValue()); 
            player.setScore(-question400.getValue()); 
            player.setScore(question400.getValue()); 
            player.setScore(question200.getValue()); 
            assertEquals(400, player.getScore());
        }
    }
}