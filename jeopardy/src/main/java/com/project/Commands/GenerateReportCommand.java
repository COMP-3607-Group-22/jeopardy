package com.project.Commands;
import com.project.Gameplay.GameTermination;
import com.project.Helpers.LogHelper;
import com.project.Helpers.ReportHelper;

/**
 * Command to generate the final textual gameplay report using GameTermination.
 */
public class GenerateReportCommand implements GameCommand {

    private final GameTermination gameTerm;
    private final ReportHelper reportHelper;

    /**
     * Create a command that will request generation of the textual gameplay
     * report using the provided {@link ReportHelper}.
     *
     * @param gameTerm GameTermination helper responsible for file outputs
     * @param reportHelper prepared report helper containing report data
     */
    public GenerateReportCommand(GameTermination gameTerm, ReportHelper reportHelper){
        this.gameTerm = gameTerm;
        this.reportHelper = reportHelper;
    }

    @Override
    public void execute(){
        gameTerm.generateReport(reportHelper);
    }

    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "System,Generate Report," + new LogHelper().getTimeStamp() + ",,,N/A,";
    }
}