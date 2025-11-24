package com.project;
import java.util.ArrayList;

public class GameInvoker{
    private ArrayList<String> history = new ArrayList<>();
    private User user;

    public ArrayList<String> getHistory(){return this.history;}

    public void runCommand(User user, GameEngineCommand command){
        command.execute();
        addToHistory(user, command);
    }

    public void addToHistory(User user, GameEngineCommand command){
        String log = user.getName() + "," + command.toString();
        history.add(log);
    }
}
