package com.project.Gameplay;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameTermination{
    public void generateEventLog(ArrayList<String> history){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy/src/main/resources/event_log.csv"))) {
            writer.write(String.join(",", new String[]{"Case_ID", "Player_ID", "Activity", "Timestamp", "Category", "Question_Value", "Answer_Given", "Result", "Score_After_Play"}));
            writer.newLine();

            for (String[] row : parseHistoryToData(history)) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public void generateReport() {

    }

    public void exitGame(){
        System.exit(0);
    }

    public String[][] parseHistoryToData(ArrayList<String> history) {
        String[][] data = new String[history.size()][9];
        for (int i = 0; i < history.size(); i++) {
            String logEntry = history.get(i);
            String[] fields = logEntry.split(",");
            System.arraycopy(fields, 0, data[i], 0, Math.min(fields.length, 9));
        }
        return data;
    }
}
