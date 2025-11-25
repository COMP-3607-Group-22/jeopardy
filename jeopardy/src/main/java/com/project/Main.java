package com.project;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        QuestionManager manager = new QuestionManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv");
        GameInvoker system = new GameInvoker();
        GameInitialization xd = new GameInitialization();

        GameEngineCommand cmd6 = new StartGameCommand(xd);

        Player SYS = new Player("Sys");
        ArrayList<Player> players = xd.getPlayers();
        players.add(SYS);
        system.runCommand(SYS, cmd);
        system.runCommand(SYS,cmd6);
    
        CategoryManager category = xd.getCategoryManager();
        
        GameEngineCommand cmd1 = new SelectPlayerCountCommand(xd);
        GameEngineCommand cmd2 = new EnterPlayerNameCommand(xd);

        system.runCommand(SYS,cmd1);
        system.runCommand(SYS,cmd2);
        
        players.remove(SYS);

        GameEngine xp = new GameEngine(category,players);
        
        GameEngineCommand cmd3 = new SelectCategoryCommand(xp);
        GameEngineCommand cmd4 = new SelectQuestionCommand(xp);
        GameEngineCommand cmd5 = new AnswerQuestionCommand(xp);

        system.runCommand(players.get(0), cmd3);
        system.runCommand(players.get(0), cmd4);
        system.runCommand(players.get(0), cmd5);
        system.runCommand(players.get(0), cmd3);
        system.runCommand(players.get(0), cmd4);
        system.runCommand(players.get(0), cmd5);
        
        
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
    }
}   
    
    
    
    
    
    
    
    


