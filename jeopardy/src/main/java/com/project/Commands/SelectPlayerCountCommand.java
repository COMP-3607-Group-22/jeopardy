package com.project.Commands;

import com.project.Gameplay.GameInitialization;
import com.project.Helpers.LogHelper;

public class SelectPlayerCountCommand implements GameCommand {
    private final GameInitialization gameInit;

    public SelectPlayerCountCommand(GameInitialization gameInit) {
        this.gameInit = gameInit;
    }

    @Override 
    public void execute() {
        gameInit.selectPlayerCount();
    }

    @Override
    public String toString(){
        return "Select Player Count," + new LogHelper().getTimeStamp() + ",," + gameInit.getPlayerCount() + ",N/A,";
    }
}

