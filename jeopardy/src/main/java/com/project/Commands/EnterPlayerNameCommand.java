package com.project.Commands;

import com.project.Gameplay.GameInitialization;
import com.project.Helpers.LogHelper;

public class EnterPlayerNameCommand implements GameCommand{
    private final GameInitialization gameInit;

    public EnterPlayerNameCommand(GameInitialization gameInit) {
        this.gameInit = gameInit;
    }

    @Override
    public void execute() {
        gameInit.enterPlayerName();
    }

    @Override
    public String toString(){
        return "Enter Player Name," + new LogHelper().getTimeStamp() 
        + ",," + gameInit.getPlayers().get(gameInit.getPlayers().size() - 1) + ",N/A";
    }
}

