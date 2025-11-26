package com.project.Gameplay;

import com.project.Commands.AnswerQuestionCommand;
import com.project.Commands.EnterPlayerNameCommand;
import com.project.Commands.GenerateEventLogCommand;
import com.project.Commands.GenerateReportCommand;
import com.project.Commands.LoadFileCommand;
import com.project.Commands.SelectCategoryCommand;
import com.project.Commands.SelectPlayerCountCommand;
import com.project.Commands.SelectQuestionCommand;
import com.project.Helpers.CategoryManager;
import com.project.Helpers.ReportHelper;
import com.project.IO.ConsoleIO;
import com.project.Questions.QuestionManager;

/**
 * Orchestrates the gameplay loop: loading questions, player setup,
 * turn execution and graceful shutdown (report + event log generation).
 */
public class Gameplay {
    private final GameInvoker gameInvoker;
    private final GameInitialization gameInit;
    private GameEngine gameEngine;
    private final GameTermination gameTermination;
    private final QuestionManager manager;
    private final ConsoleIO consoleIO;

    /**
     * Create a new Gameplay coordinator.
     *
     * @param gameInvoker the invoker used to execute commands and record events
     * @param consoleIO the console abstraction for user interaction
     */
    public Gameplay(GameInvoker gameInvoker, ConsoleIO consoleIO){
        this.gameInvoker = gameInvoker;
        this.consoleIO = consoleIO;
        this.manager = new QuestionManager();
        this.gameInit = new GameInitialization(consoleIO);
        this.gameTermination = new GameTermination(consoleIO);
    }

    /**
     * Start the interactive game flow: prompt for a question file, load
     * questions, configure players and begin the turn loop.
     */
    public void startGame(){
        consoleIO.print("Game " + gameInvoker.getCaseId() + " created.\n");
        consoleIO.print("Enter filename for Game data to load (must be in directory jeopardy/src/main/resources and of type .csv|.json|.xml):\n");

        gameInvoker.executeCommand(new LoadFileCommand(manager, "jeopardy/src/main/resources/" + consoleIO.readLine()));
        gameInvoker.executeCommand(new SelectPlayerCountCommand(gameInit));

        for(int x = 0; x < gameInit.getPlayerCount();x++){
            gameInvoker.executeCommand(new EnterPlayerNameCommand(gameInit));
        }

        beginTurns();
    }

    /**
     * Initialize the `GameEngine` using the selected players and case id,
     * then drive the main turn loop until no categories remain.
     */
    public void beginTurns(){
        ReportHelper reportHelper = gameInvoker.createReportHelper(gameInit.getPlayers());
        this.gameEngine = new GameEngine(new CategoryManager(manager), gameInit.getPlayers(), consoleIO, reportHelper);
        while(!gameEngine.getCategoryEmpty()){
            gameInvoker.executeCommand(new SelectCategoryCommand(gameEngine));
            gameInvoker.executeCommand(new SelectQuestionCommand(gameEngine));
            gameInvoker.executeCommand(new AnswerQuestionCommand(gameEngine));
        }
    }

    /**
     * Terminate gameplay and persist the generated report and event log
     * using the configured `GameTermination` helper.
     */
    public void exitGame(){
        gameInvoker.executeCommand(new GenerateReportCommand(gameTermination, gameEngine.getReportHelper()));
        gameInvoker.executeCommand(new GenerateEventLogCommand(gameTermination, gameInvoker.getEventLogHelper()));
        consoleIO.print("Thank you for playing!\n");
        consoleIO.close();
    }
}
