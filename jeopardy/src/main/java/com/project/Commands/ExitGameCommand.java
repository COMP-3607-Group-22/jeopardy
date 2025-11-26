package com.project.Commands;

import com.project.Gameplay.Gameplay;
import com.project.Helpers.LogHelper;

/**
 * Command that exits the current game by delegating to `Gameplay.exitGame()`.
 */
public class ExitGameCommand implements GameCommand {

    private final Gameplay gameplay;

    public ExitGameCommand(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    @Override 
    public void execute() {
        gameplay.exitGame();
    }

    @Override
    public String toString(){
        return "System,Exit Game," + new LogHelper().getTimeStamp() + ",,,,";
    }
}