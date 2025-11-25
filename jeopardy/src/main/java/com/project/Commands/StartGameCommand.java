package com.project.Commands;

import com.project.Gameplay.GameInitialization;
import com.project.Helpers.LogHelper;

public class StartGameCommand implements GameCommand {
    private final GameInitialization gameInit;

    public StartGameCommand(GameInitialization gameInit){
        this.gameInit = gameInit;
    }

    @Override
    public void execute() {
        gameInit.startGame();
    }

    @Override
    public String toString(){
        return "Start Game," + new LogHelper().getTimeStamp() + ",,,,";
    }
}
