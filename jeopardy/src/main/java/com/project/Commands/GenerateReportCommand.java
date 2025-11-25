package com.project.Commands;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.LogHelper;

public class GenerateReportCommand implements GameCommand {

    private final GameTermination gameTerm;

    public GenerateReportCommand(GameTermination gameTerm) {
        this.gameTerm = gameTerm;
    }

    @Override
    public String toString(){
        return "Select Category," + new LogHelper().getTimeStamp() + ",,,N/A";
    }
}