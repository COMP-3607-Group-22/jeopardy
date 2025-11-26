package com.project.Commands;

import com.project.Gameplay.GameInitialization;
import com.project.Gameplay.Player;
import com.project.Helpers.LogHelper;

/**
 * Command used during setup to capture a player's name and add it to the
 * {@link GameInitialization} instance.
 */
public class EnterPlayerNameCommand implements GameCommand{
    private final GameInitialization gameInit;

    /**
     * Create the command bound to a GameInitialization helper.
     *
     * @param gameInit the setup helper used to store entered player names
     */
    public EnterPlayerNameCommand(GameInitialization gameInit) {
        this.gameInit = gameInit;
    }

    @Override
    public void execute() {
        gameInit.enterPlayerName();
    }

    /** 
     * @return String
     */
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

