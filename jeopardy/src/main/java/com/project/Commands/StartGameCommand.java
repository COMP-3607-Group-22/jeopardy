package com.project.Commands;

import com.project.Gameplay.Gameplay;
import com.project.Helpers.LogHelper;

/**
 * Command to start the gameplay loop. Delegates to `Gameplay.startGame()`.
 */
public class StartGameCommand implements GameCommand {
    private final Gameplay gameplay;

    /**
     * Create a StartGameCommand that will invoke the gameplay start logic.
     *
     * @param gameplay gameplay instance to start
     */
    public StartGameCommand(Gameplay gameplay){
        this.gameplay = gameplay;
    }

    @Override
    public void execute() {
        gameplay.startGame();
    }
    /**
     * Execute the start game command by delegating to `Gameplay.startGame()`.
     */
    @Override
    public String toString(){
        return "System,Start Game," + new LogHelper().getTimeStamp() + ",,,,";
    }
}
