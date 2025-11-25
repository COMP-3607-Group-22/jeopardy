package com.project.Commands;
import java.util.ArrayList;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.LogHelper;

public class GenerateReportCommand implements GameCommand {

    private final GameTermination gameTerm;
    private ArrayList<String> report;

    public GenerateReportCommand(GameTermination gameTerm, ArrayList<String> report) {
        this.gameTerm = gameTerm;
        this.report = report;
    }

    @Override
    public void execute(){
        gameTerm.generateReport(report);
    }

    @Override
    public String toString(){
        return "System,Generate Report," + new LogHelper().getTimeStamp() + ",,,N/A,";
    }
}