package com.project.Commands;
import java.util.ArrayList;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.LogHelper;

public class GenerateEventLogCommand implements GameCommand {

    private final GameTermination gameTerm;
    private final ArrayList<String> history;

    public GenerateEventLogCommand(GameTermination gameTerm, ArrayList<String> history) {
        this.gameTerm = gameTerm;
        this.history = history;
    }

    @Override
    public void execute() {
        gameTerm.generateEventLog(history);
    }

    @Override
    public String toString(){
        return "Generate Event Log," + new LogHelper().getTimeStamp() + ",,,N/A";
    }
}