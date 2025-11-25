package com.project.Commands;

import com.project.Gameplay.*;
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
        Player player = gameInit.getPlayers().get(gameInit.getPlayers().size() - 1);
        return player +  ",Enter Player Name," + new LogHelper().getTimeStamp() 
        + ",," + player + ",N/A,";
    }
}

