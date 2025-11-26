package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.lang.reflect.Field;
import java.util.*;
import org.junit.jupiter.api.Test;

import com.project.Gameplay.GameEngine;
import com.project.Gameplay.Player;
import com.project.Helpers.CategoryManager;
import com.project.Parsing.CSVParserAdaptee;
import com.project.Questions.Question;
import com.project.Questions.QuestionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Gameplay Tests")
class GameplayTest {

    private GameEngine gameEngine;
    private Player player1;
    private Player player2;
    private CategoryManager categoryManager;
    private QuestionManager questionManager;

    @BeforeEach
    void setup() throws Exception {

        String path = getClass().getClassLoader().getResource("sample_game_CSV.csv").getPath();
        List<Question> questions = new CSVParserAdaptee().parse(path);
        questionManager = new QuestionManager();
        questionManager.getQuestions().addAll(questions);
        categoryManager = new CategoryManager(questionManager);

        player1 = new Player("Tom");
        player2 = new Player("Jerry");
        gameEngine = new GameEngine(categoryManager,
                new ArrayList<>(Arrays.asList(player1, player2)));
    }

    private void set(String fieldName, Object value) throws Exception {
        Field f = GameEngine.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(gameEngine, value);
    }

    @Test
    @DisplayName("Game Engine initializes correctly")
    void testGameEngineInitialization() {
        assertNotNull(gameEngine);
        assertEquals(2, gameEngine.getPlayers().size());
        assertEquals("Tom", gameEngine.getPlayers().get(0).getName());
        assertEquals("Jerry", gameEngine.getPlayers().get(1).getName());
    }

    @Test
    @DisplayName("Player rotates correctly")
    void playerTurnRotation() {
        assertEquals("Tom", gameEngine.getCurrentPlayer().getName());

        gameEngine.nextPlayerTurn();
        assertEquals("Jerry", gameEngine.getCurrentPlayer().getName());

        gameEngine.nextPlayerTurn();
        assertEquals("Tom", gameEngine.getCurrentPlayer().getName());
    }

    @Test
    @DisplayName("Correct answer adds points and removes question")
    void correctAnswerFlow() throws Exception {
        String category = new ArrayList<>(categoryManager.getCategoryNames()).get(0);
        Question question = categoryManager.getQuestions(category).get(0);
        int beforeScore = player1.getScore();
        int beforeCount = categoryManager.getQuestions(category).size();

        set("currentQuestion", question);
        set("givenAnswer", question.getAnswer());

        if (gameEngine.isAnswerCorrect()) {
            gameEngine.getCurrentPlayer().setScore(question.getValue());
        }

        else {
            gameEngine.getCurrentPlayer().setScore(-question.getValue());
        }

        categoryManager.removeQuestion(category, question);

        if (categoryManager.getQuestions(category).isEmpty()) {
            categoryManager.removeCategory(category);
        }

        assertEquals(beforeScore + question.getValue(), player1.getScore());
        assertEquals(beforeCount - 1, categoryManager.getQuestions(category).size());
    }

    @Test
    @DisplayName("Category is remvoved when empty")
    void categoryRemovedWhenEmpty() throws Exception {
        String category = new ArrayList<>(categoryManager.getCategoryNames()).get(0);
        List<Question> questions = new ArrayList<>(categoryManager.getQuestions(category));

        for (Question q : questions) {
            set("currentQuestion", q);
            set("givenAnswer", q.getAnswer());

            if (gameEngine.isAnswerCorrect()) {
                gameEngine.getCurrentPlayer().setScore(q.getValue());
            }

            else {
                gameEngine.getCurrentPlayer().setScore(-q.getValue());
            }

            categoryManager.removeQuestion(category, q);

            if (categoryManager.getQuestions(category).isEmpty()) {
                categoryManager.removeCategory(category);
            }
        }

        assertFalse(categoryManager.getCategoryNames().contains(category));
    }

    @Test
    @DisplayName("Game ends when all categories are removed")
    void gameEndsWhenAllCategoriesRemoved() throws Exception {
        for (String category : new ArrayList<>(categoryManager.getCategoryNames())) {
            List<Question> questions = new ArrayList<>(categoryManager.getQuestions(category));

            for (Question q : questions) {
                set("currentQuestion", q);
                set("givenAnswer", q.getAnswer());

                if (gameEngine.isAnswerCorrect()) {
                    gameEngine.getCurrentPlayer().setScore(q.getValue());
                }

                else {
                    gameEngine.getCurrentPlayer().setScore(-q.getValue());
                }

                categoryManager.removeQuestion(category, q);
            }

            categoryManager.removeCategory(category);
        }
        assertTrue(categoryManager.getCategoryNames().isEmpty());
}


        
}