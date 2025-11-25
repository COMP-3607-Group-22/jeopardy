package com.project.Commands;

import com.project.Gameplay.GameEngine;
import com.project.Helpers.LogHelper;

public class SelectQuestionCommand implements GameCommand {
   
    private final GameEngine gameEngine;
    
    public SelectQuestionCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    
    }

    @Override
    public void execute() {
        gameEngine.selectQuestion();
    }

    @Override
    public String toString(){
        return gameEngine.getCurrentPlayer() + ",Select Question," + new LogHelper().getTimeStamp() + gameEngine.getCurrentCategory() + "," + gameEngine.getCurrentQuestion().getValue() + ",,," + gameEngine.getCurrentPlayer().getScore();
    }
}
