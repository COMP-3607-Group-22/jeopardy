package com.project.Commands;
import java.util.ArrayList;

import com.project.Gameplay.GameTermination;
import com.project.Helpers.*;

public class GenerateReportCommand implements GameCommand {

    private final GameTermination gameTerm;
    private ReportHelper reportHelper;

    public GenerateReportCommand(GameTermination gameTerm, ReportHelper reportHelper){
        this.gameTerm = gameTerm;
        this.reportHelper = reportHelper;
    }

    @Override
    public void execute(){
        gameTerm.generateReport(reportHelper);
    }

    @Override
    public String toString(){
        return "System,Generate Report," + new LogHelper().getTimeStamp() + ",,,N/A,";
    }
}