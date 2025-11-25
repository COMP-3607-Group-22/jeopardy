package com.project.Gameplay;
import java.util.ArrayList;

import com.project.Commands.GameCommand;

public class GameInvoker{
    private final ArrayList<String> history = new ArrayList<>();
    private final int caseID;

    public GameInvoker(int caseID){
        this.caseID = caseID;
    }

    public void executeCommand(Player player, GameCommand command){
        command.execute();
        addToHistory(player, command);
    }

    public void addToHistory(Player player, GameCommand command){
        String log = this.caseID + "," + player.getName() + "," + command.toString();
        history.add(log);
    }

    public ArrayList<String> getHistory(){return this.history;}
}
