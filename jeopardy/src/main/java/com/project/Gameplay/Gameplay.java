package com.project.Gameplay;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.Questions.QuestionManager;
import com.project.Commands.*;
import com.project.Helpers.*;

public class Gameplay {

    public void startGame(GameInvoker gameInvoker){
        Scanner scanner = new Scanner(System.in);
        QuestionManager manager = new QuestionManager();

        System.out.print("Game " + gameInvoker.getCaseId() + "created.\n");
        System.out.print("Enter filename for Game data to load (must be in directory jeopardy/src/main/resources and of type .csv|.json|.xml):\n");
        gameInvoker.executeCommand(new LoadFileCommand(manager, "jeopardy/src/main/resources/" + scanner.nextLine()));

        GameInitialization gameInit = new GameInitialization();
        gameInvoker.executeCommand(new SelectPlayerCountCommand(gameInit));

        for(int x = 0; x < gameInit.getPlayerCount();x++){
            gameInvoker.executeCommand(new EnterPlayerNameCommand(gameInit));
        }

        GameEngine gameEngine = new GameEngine(new CategoryManager(manager), gameInit.getPlayers());
        for(int x = 0; x < gameInit.getPlayerCount();x++){
            gameInvoker.executeCommand(new SelectCategoryCommand(gameEngine));
            gameInvoker.executeCommand(new SelectQuestionCommand(gameEngine));
            gameInvoker.executeCommand(new AnswerQuestionCommand(gameEngine));
        }

        GameTermination gameTermination = new GameTermination();
        gameEngine.finalScores();
        gameInvoker.executeCommand(new GenerateReportCommand(gameTermination, gameEngine.getReport()));
        gameInvoker.executeCommand(new GenerateEventLogCommand(gameTermination, gameInvoker.getHistory()));
        gameInvoker.executeCommand(new ExitGameCommand(gameTermination));
    }
}
