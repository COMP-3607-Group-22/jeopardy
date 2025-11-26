package com.project.Helpers;

import java.util.ArrayList;

import com.project.Gameplay.Player;

/**
 * Helper responsible for building a textual report of gameplay.
 * Contains convenience methods to add turn summaries and produce final output.
 */
public final class ReportHelper {
    private final ArrayList<String> report;
    private final ArrayList<Player> players;
    private String caseId;

    /**
     * Create a ReportHelper for the supplied players and optional case id.
     *
     * @param players list of players participating in the game
     * @param caseId optional case identifier (may be null and set later)
     */
    public ReportHelper(ArrayList<Player> players, String caseId){
        this.players = players;
        this.caseId = caseId;

        this.report = new ArrayList<>();
        initReport();
    }
    /**
     * Initialize the report content with the header, case id and player list.
     * This is invoked during construction to prepare the report buffer.
     */
    public void initReport(){
        this.report.add(buildHeader());
    }

    /** 
     * @return String
     */
    private String buildHeader(){
        return "JEOPARDY PROGRAMMING GAME REPORT\n"
            + "================================\n"
            + "Case ID: " + (this.caseId == null ? "" : this.caseId) + "\n"
            + "Players: " + players
            + "\n\nGameplay Summary:\n"
            + "-----------------\n";
    }

    /**
     * Set or update the case identifier used in the report header. If the
     * report header has already been written to the buffer, update the
     * existing header line so subsequent output uses the new case id.
     *
     * @param caseId the case identifier to embed in the report
     */
    public void setCaseId(String caseId){
        this.caseId = caseId;
        if (!this.report.isEmpty()){
            this.report.set(0, buildHeader());
        }
    }

    /**
     * Append a single turn summary line to the in-memory report buffer.
     *
     * @param entry a textual summary describing a single turn
     */
    public void addTurnSummary(String entry){
        report.add(entry);
    }

    /**
     * Add a final scores section to the report (player name and score
     * per line).
     */
    public void finalScores(){
        report.add("Final Scores:");
        for(Player p : this.players){
            report.add(p.getName() + ": " + p.getScore());
        }
    }

    /**
     * Produce the final report as a list of lines. This will append the
     * final scores section before returning the buffer.
     *
     * @return an ArrayList of report lines
     */
    public ArrayList<String> getReport(){
        finalScores();
        return this.report;
    }
}
