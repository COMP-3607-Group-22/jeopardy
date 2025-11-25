package com.project.Commands;

import com.project.Gameplay.*;
import com.project.Helpers.LogHelper;

public class StartGameCommand implements GameCommand {
    private final Gameplay gameplay;
    private GameInvoker gameInvoker;

    public StartGameCommand(Gameplay gameplay, GameInvoker gameInvoker){
        this.gameplay = gameplay;
        this.gameInvoker = gameInvoker;
    }

    @Override
    public void execute() {
        gameplay.startGame(gameInvoker);
    }

    @Override
    public String toString(){
        return "System,Start Game," + new LogHelper().getTimeStamp() + ",,,,";
    }
}
