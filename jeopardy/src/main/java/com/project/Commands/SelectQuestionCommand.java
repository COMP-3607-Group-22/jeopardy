package com.project.Commands;

import com.project.Gameplay.GameEngine;
import com.project.Helpers.LogHelper;

/**
 * Command that prompts the engine to select a question for the current category.
 */
public class SelectQuestionCommand implements GameCommand {
   
    private final GameEngine gameEngine;
    
    public SelectQuestionCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    
    }

    @Override
    public void execute() {
        gameEngine.selectQuestion();
    }
    /**
     * @return a CSV-like log entry describing the question selection
     */
    @Override
    public String toString(){
        if(gameEngine.getCurrentQuestion() != null){
                return gameEngine.getCurrentPlayer() + ",Select Question," + new LogHelper().getTimeStamp()
                + gameEngine.getCurrentCategory() + ","
                + gameEngine.getCurrentQuestion().getValue() + ",,,"
                + gameEngine.getCurrentPlayer().getScore();
            }
        return "";
    }
}
