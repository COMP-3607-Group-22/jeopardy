package com.project.Commands;

import com.project.Gameplay.GameInitialization;
import com.project.Helpers.LogHelper;

/**
 * Command to select how many players will participate in the current game.
 */
public class SelectPlayerCountCommand implements GameCommand {
    private final GameInitialization gameInit;

    public SelectPlayerCountCommand(GameInitialization gameInit) {
        this.gameInit = gameInit;
    }

    @Override 
    public void execute() {
        gameInit.selectPlayerCount();
    }
    /**
     * @return compact textual representation for event logs
     */
    @Override
    public String toString(){
        return "System,Select Player Count," + new LogHelper().getTimeStamp() + ",," + gameInit.getPlayerCount() + ",N/A,";
    }
}

