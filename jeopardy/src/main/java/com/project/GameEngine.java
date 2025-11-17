package com.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameEngine{

    private final Stack<GameEngineCommand> history = new Stack<>();

    public GameEngine(){
    
    }
    public void issueCommand(GameEngineCommand command) {
        command.execute();
        history.push(command);
        System.out.println("Executing: " + command);
    }
    public List<GameEngineCommand> getHistory() { 
        return new ArrayList<>(history);
    }
}