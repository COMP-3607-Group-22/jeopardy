package com.project;
import com.project.Commands.ExitGameCommand;
import com.project.Commands.StartGameCommand;
import com.project.Gameplay.GameInvoker;
import com.project.Gameplay.Gameplay;
import com.project.IO.ConsoleScanner;

public class Main {
    public static void main(String[] args) {
        GameInvoker gameInvoker = new GameInvoker("GAME001");
        Gameplay gameplay = new Gameplay(gameInvoker, new ConsoleScanner());
        gameInvoker.executeCommand(new StartGameCommand(gameplay));
        gameInvoker.executeCommand(new ExitGameCommand(gameplay));
    }
}


