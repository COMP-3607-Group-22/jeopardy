package com.project.Helpers;

import java.util.ArrayList;

import com.project.Gameplay.Player;

public final class ReportHelper {
    private final ArrayList<String> report;
    private final ArrayList<Player> players;
    private final String caseId;

    public ReportHelper(ArrayList<Player> players, String caseId){
        this.players = players;
        this.caseId = caseId;

        this.report = new ArrayList<>();
        initReport();
    }

    public void initReport(){
        this.report.add(
        "JEOPARDY PROGRAMMING GAME REPORT\n"
        + "================================\n"
        + "Case ID: " + this.caseId + "\n"
        + "Players: " + players
        + "\n\nGameplay Summary:\n"
        + "-----------------\n"
    );
    }

    public void addTurnSummary(String entry){
        report.add(entry);
    }

    public void finalScores(){
        report.add("Final Scores:");
        for(Player p : this.players){
            report.add(p.getName() + ": " + p.getScore());
        }
    }

    public ArrayList<String> getReport(){
        finalScores();
        return this.report;
    }
}
