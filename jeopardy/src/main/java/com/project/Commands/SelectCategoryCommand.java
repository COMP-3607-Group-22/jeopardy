package com.project.Commands;

import com.project.Gameplay.GameEngine;
import com.project.Helpers.LogHelper;

public class SelectCategoryCommand implements GameCommand {

    private final GameEngine gameEngine;

    public SelectCategoryCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute(){
        gameEngine.selectCategory();
    }

    @Override
    public String toString(){
        return gameEngine.getCurrentPlayer() + ",Select Category," + new LogHelper().getTimeStamp()
        + gameEngine.getCurrentCategory() + ",,,,"
        + gameEngine.getCurrentPlayer().getScore();
    }
}
