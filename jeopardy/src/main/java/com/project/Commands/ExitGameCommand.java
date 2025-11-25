package com.project.Commands;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.LogHelper;

public class ExitGameCommand implements GameCommand {

    private final GameTermination gameTerm;

    public ExitGameCommand(GameTermination gameTerm) {
        this.gameTerm = gameTerm;
    }

    @Override 
    public void execute() {
        gameTerm.exitGame();
    }

    @Override
    public String toString(){
        return "Exit Game," + new LogHelper().getTimeStamp() + ",,,,";
    }
}