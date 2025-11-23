package com.project;

public class Main {
    public static void main(String[] args) {
        
        QuestionManager manager = new QuestionManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv");
        GameInvoker system = new GameInvoker();
        GameEngine xp = new GameEngine();
        
        GameEngineCommand cmd1 = new SelectPlayerCountCommand(xp);
        GameEngineCommand cmd2 = new EnterPlayerNameCommand(xp);
        GameEngineCommand cmd3 = new SelectCategoryCommand(xp);
        GameEngineCommand cmd4 = new SelectQuestionCommand(xp);
        GameEngineCommand cmd5 = new AnswerQuestionCommand(xp);
        GameEngineCommand cmd6 = new StartGameCommand(xp);

        system.runCommand(cmd6);
        system.runCommand(cmd1);
        system.runCommand(cmd2);
        system.runCommand(cmd3);
        system.runCommand(cmd4);
        system.runCommand(cmd5);
        system.runCommand(cmd3);
        system.runCommand(cmd4);
        system.runCommand(cmd5);

        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
        cmd.execute();

        System.out.println("Successfully loaded " + manager.getQuestions().size() + " questions\n");
    }
}   
    
    
    
    
    
    
    
    


