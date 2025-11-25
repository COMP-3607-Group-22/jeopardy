package com.project.Commands;

import com.project.Gameplay.Gameplay;
import com.project.Helpers.LogHelper;

public class StartGameCommand implements GameCommand {
    private final Gameplay gameplay;

    public StartGameCommand(Gameplay gameplay){
        this.gameplay = gameplay;
    }

    @Override
    public void execute() {
        gameplay.startGame();
    }

    @Override
    public String toString(){
        return "System,Start Game," + new LogHelper().getTimeStamp() + ",,,,";
    }
}
