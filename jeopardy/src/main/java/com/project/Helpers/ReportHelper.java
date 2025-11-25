package com.project.Helpers;

import java.util.ArrayList;

import com.project.Gameplay.*;

public class ReportHelper {
    private ArrayList<String> report;
    private GameEngine gameEngine;

    public ReportHelper(GameEngine gameEngine){
        this.report = new ArrayList<>();
        this.gameEngine = gameEngine;
        initReport();
    }

    public void initReport(){
        this.report = new ArrayList<>();

        this.report.add("JEOPARDY PROGRAMMING GAME REPORT\n"
        + "================================"
        + "\nCase ID:"
        + "\nPlayers" + gameEngine.getPlayers().toString()
        + "\n\nGameplay Summary:"
        + "\n-----------------\n");
    }

    public void addTurnSummary(String entry){
        report.add(entry);
    }

    public void finalScores(){
        report.add("Final Scores:\n");
        for(Player p : gameEngine.getPlayers()){
            report.add(p.getName() + ": " + p.getScore() + "\n");
        }
    }

    public ArrayList<String> getReport(){
        finalScores();
        return this.report;
    }
}
