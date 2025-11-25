package com.project;
import com.project.Commands.StartGameCommand;
import com.project.Gameplay.*;

public class Main {
    public static void main(String[] args) {
        GameInvoker gameInvoker = new GameInvoker("GAME001");
        gameInvoker.executeCommand("System", new StartGameCommand(new Gameplay(), gameInvoker));
    }
}


