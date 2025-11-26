package com.project;
import com.project.Commands.ExitGameCommand;
import com.project.Commands.StartGameCommand;
import com.project.Gameplay.GameInvoker;
import com.project.Gameplay.Gameplay;
import com.project.IO.ConsoleScanner;

/**
 * Application entry point for the Jeopardy game.
 * Sets up minimal composition (invoker and gameplay) and starts the game.
 */
public class Main {
    /**
     * Application entrypoint. Creates core components and triggers the
     * start/exit command sequence.
     *
     * @param args command line args (ignored)
     */
    public static void main(String[] args) {
        GameInvoker gameInvoker = new GameInvoker("GAME001");
        Gameplay gameplay = new Gameplay(gameInvoker, new ConsoleScanner());
        gameInvoker.executeCommand(new StartGameCommand(gameplay));
        gameInvoker.executeCommand(new ExitGameCommand(gameplay));
    }
}


