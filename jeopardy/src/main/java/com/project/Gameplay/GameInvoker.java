package com.project.Gameplay;
import java.util.ArrayList;

import com.project.Commands.GameCommand;

public class GameInvoker{
    private final ArrayList<String> history = new ArrayList<>();
    private final String caseId;

    public GameInvoker(String caseId){
        this.caseId = caseId;
    }

    public void executeCommand(String invokerUser, GameCommand command){
        command.execute();
        addToHistory(invokerUser, command);
    }

    public void addToHistory(String invokerUser, GameCommand command){
        String log = this.caseId + "," + invokerUser + "," + command.toString();
        history.add(log);
    }

    public ArrayList<String> getHistory(){return this.history;}
    public String getCaseId(){return this.caseId;}
}
