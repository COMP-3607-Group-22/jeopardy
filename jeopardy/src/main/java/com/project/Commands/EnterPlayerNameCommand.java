package com.project.Commands;

import com.project.Gameplay.GameInitialization;
import com.project.Gameplay.Player;
import com.project.Helpers.LogHelper;

public class EnterPlayerNameCommand implements GameCommand{
    /**
     * Command used during setup to enter a player's name into GameInitialization.
     */
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
    /**
     * @return a CSV-like log entry representing the most recently entered player
     */
        if(!gameInit.getPlayers().isEmpty()){
            Player player = gameInit.getPlayers().get(gameInit.getPlayers().size() - 1);
            return player + ",Enter Player Name," + new LogHelper().getTimeStamp()
            + ",," + player + ",N/A,";
        }
        return "";
    }
}

