package com.project;
import java.util.ArrayList;

public class GameInvoker{
    private ArrayList<String> history = new ArrayList<>();
    private Player Player;

    public ArrayList<String> getHistory(){return this.history;}

    public void runCommand(Player Player, GameEngineCommand command){
        command.execute();
        addToHistory(Player, command);
    }

    public void addToHistory(Player Player, GameEngineCommand command){
        String log = Player.getName() + "," + command.toString();
        history.add(log);
    }
}
