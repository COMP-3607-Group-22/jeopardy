package com.project.Commands;

import com.project.Gameplay.GameEngine;
import com.project.Helpers.LogHelper;

/**
 * Command that asks the engine to present categories and choose one.
 */
public class SelectCategoryCommand implements GameCommand {

    private final GameEngine gameEngine;

    /**
     * Create the command bound to a GameEngine instance.
     *
     * @param gameEngine engine used to select categories
     */
    public SelectCategoryCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute(){
        gameEngine.selectCategory();
    }
    /**
     * @return a CSV-like log entry describing the category selection
     */
    @Override
    public String toString(){
        return gameEngine.getCurrentPlayer() + ",Select Category," + new LogHelper().getTimeStamp()
        + gameEngine.getCurrentCategory() + ",,,,"
        + gameEngine.getCurrentPlayer().getScore();
    }
}
