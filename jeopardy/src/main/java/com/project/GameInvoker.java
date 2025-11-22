package com.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameInvoker {
    private Stack<GameEngineCommand> history = new Stack<>();

    public GameInvoker() {
        
    }

    public List<GameEngineCommand> gethistory(){return new ArrayList<>(history);}

    public void runCommand(GameEngineCommand command){
        command.execute();
        history.push(command);
    }
}
