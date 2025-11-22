package com.project;

public class Main {
    public static void main(String[] args) {

        QuestionManager manager = new QuestionManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_CSV.csv");
        GameInvoker system = new GameInvoker();
        GameEngine xp = new GameEngine();
        
        GameEngineCommand cmd1 = new SelectPlayerCountCommand(xp);
        GameEngineCommand cmd2 = new EnterPlayerNameCommand(xp);
        
        system.runCommand(cmd1);
        system.runCommand(cmd2);



        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
        cmd.execute();

        System.out.println("Successfully loaded " + manager.getQuestions().size() + " questions\n");
    }

}

