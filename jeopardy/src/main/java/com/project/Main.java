package com.project;

import java.util.ArrayList;

import com.project.Categories.CategoryManager;
import com.project.Categories.QuestionManager;
import com.project.Commands.AnswerQuestionCommand;
import com.project.Commands.EnterPlayerNameCommand;
import com.project.Commands.GameCommand;
import com.project.Commands.GenerateReportCommand;
import com.project.Commands.LoadFileCommand;
import com.project.Commands.SelectCategoryCommand;
import com.project.Commands.SelectPlayerCountCommand;
import com.project.Commands.SelectQuestionCommand;
import com.project.Commands.StartGameCommand;
import com.project.Gameplay.GameEngine;
import com.project.Gameplay.GameInitialization;
import com.project.Gameplay.GameInvoker;
import com.project.Gameplay.Player;

public class Main {
    public static void main(String[] args) {
        
        QuestionManager manager = new QuestionManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv");
        GameInvoker system = new GameInvoker();
        GameInitialization xd = new GameInitialization();

        GameCommand cmd6 = new StartGameCommand(xd);

        Player SYS = new Player("Sys");
        ArrayList<Player> players = xd.getPlayers();
        players.add(SYS);
        system.runCommand(SYS,cmd6);
    
        CategoryManager category = xd.getCategoryManager();
        
        GameCommand cmd1 = new SelectPlayerCountCommand(xd);
        GameCommand cmd2 = new EnterPlayerNameCommand(xd);

        system.runCommand(SYS,cmd1);
        system.runCommand(SYS,cmd2);
        
        players.remove(SYS);

        GameEngine xp = new GameEngine(category,players);
        
        GameCommand cmd3 = new SelectCategoryCommand(xp);
        GameCommand cmd4 = new SelectQuestionCommand(xp);
        GameCommand cmd5 = new AnswerQuestionCommand(xp);

        system.runCommand(players.get(0), cmd3);
        system.runCommand(players.get(0), cmd4);
        system.runCommand(players.get(0), cmd5);
        system.runCommand(players.get(0), cmd3);
        system.runCommand(players.get(0), cmd4);
        system.runCommand(players.get(0), cmd5);
        system.runCommand(players.get(0), new GenerateReportCommand(xp));
        
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
    }
}   
    
    
    
    
    
    
    
    


