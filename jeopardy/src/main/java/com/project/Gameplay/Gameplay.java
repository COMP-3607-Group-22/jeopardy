package com.project.Gameplay;

import java.util.Scanner;

import com.project.Commands.AnswerQuestionCommand;
import com.project.Commands.EnterPlayerNameCommand;
import com.project.Commands.GenerateEventLogCommand;
import com.project.Commands.GenerateReportCommand;
import com.project.Commands.LoadFileCommand;
import com.project.Commands.SelectCategoryCommand;
import com.project.Commands.SelectPlayerCountCommand;
import com.project.Commands.SelectQuestionCommand;
import com.project.Helpers.CategoryManager;
import com.project.Questions.QuestionManager;

public class Gameplay {
    private final GameInvoker gameInvoker;
    private GameEngine gameEngine;

    public Gameplay(GameInvoker gameInvoker){
        this.gameInvoker = gameInvoker;
    }

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        QuestionManager manager = new QuestionManager();

        System.out.print("Game " + gameInvoker.getCaseId() + " created.\n");
        System.out.print("Enter filename for Game data to load (must be in directory jeopardy/src/main/resources and of type .csv|.json|.xml):\n");
        gameInvoker.executeCommand(new LoadFileCommand(manager, "jeopardy/src/main/resources/" + scanner.nextLine()));

        GameInitialization gameInit = new GameInitialization();
        gameInvoker.executeCommand(new SelectPlayerCountCommand(gameInit));

        for(int x = 0; x < gameInit.getPlayerCount();x++){
            gameInvoker.executeCommand(new EnterPlayerNameCommand(gameInit));
        }

        this.gameEngine = new GameEngine(new CategoryManager(manager), gameInit.getPlayers());
        for(int x = 0; x < gameInit.getPlayerCount()*2;x++){
            gameInvoker.executeCommand(new SelectCategoryCommand(gameEngine));
            gameInvoker.executeCommand(new SelectQuestionCommand(gameEngine));
            gameInvoker.executeCommand(new AnswerQuestionCommand(gameEngine));
        }
    }

    public void exitGame(){
        GameTermination gameTermination = new GameTermination();
        gameEngine.finalScores();
        gameInvoker.executeCommand(new GenerateReportCommand(gameTermination, gameEngine.getReport()));
        gameInvoker.executeCommand(new GenerateEventLogCommand(gameTermination, gameInvoker.getEventLogHelper()));
    }
}
