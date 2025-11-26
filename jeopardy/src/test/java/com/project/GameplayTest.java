package com.project;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import com.project.Gameplay.GameEngine;
import com.project.Gameplay.Player;
import com.project.Helpers.CategoryManager;
import com.project.Parsing.CSVParserAdaptee;
import com.project.Questions.QuestionManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Gameplay Tests")
class GameplayTest {

    private GameEngine gameEngine;
    private Player player1;
    private Player player2;
    private CategoryManager categoryManager;

    @BeforeEach
    void setup() throws Exception {

    String path = getClass().getClassLoader().getResource("sample_game_CSV.csv").getPath();
    QuestionManager questionManager = new QuestionManager();
    questionManager.getQuestions().forEach(q -> categoryManager.addQuestion(q));
    categoryManager = new CategoryManager(questionManager);
    questionManager.getQuestions().forEach(categoryManager::addQuestion);

        player1 = new Player("Tom");
        player2 = new Player("Jerry");
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        gameEngine = new GameEngine(categoryManager, players);
    }

}
