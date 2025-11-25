package com.project.Gameplay;
import java.util.ArrayList;

import com.project.Commands.GameCommand;

public class GameInvoker{
    private ArrayList<String> history = new ArrayList<>();
    private Player player;

    public ArrayList<String> getHistory(){return this.history;}

    public void runCommand(Player player, GameCommand command){
        command.execute();
        addToHistory(player, command);
    }

    public void addToHistory(Player player, GameCommand command){
        String log = player.getName() + "," + command.toString();
        history.add(log);
    }
}
