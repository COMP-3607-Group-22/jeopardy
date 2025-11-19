package com.project;
public class Main {
    public static void main(String[] args) {
        QuestionManager manager = new QuestionManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_CSV.csv");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
        cmd.execute();

        System.out.println("Successfully loaded " + manager.getQuestions().size() + " questions\n");
    }

}

