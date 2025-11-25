package com.project.Gameplay;
import java.util.ArrayList;

import com.project.Commands.GameCommand;

public class GameInvoker{
    private final ArrayList<String> history = new ArrayList<>();
    private final String caseId;

    public GameInvoker(String caseId){
        this.caseId = caseId;
    }

    public void executeCommand(GameCommand command){
        command.execute();
        addToHistory(command);
    }

    public void addToHistory(GameCommand command){
        String log = this.caseId + "," + command.toString();
        history.add(log);
    }

    public ArrayList<String> getHistory(){return this.history;}
    public String getCaseId(){return this.caseId;}
}
