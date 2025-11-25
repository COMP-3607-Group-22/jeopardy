package com.project.Gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.Categories.CategoryManager;
import com.project.Categories.Question;
import com.project.Parsing.CSVParserAdaptee;

public class GameInitialization {

    private Player currentPlayer = null;
    
    private int pc;

    ArrayList<Player> players = new ArrayList<>();
    CSVParserAdaptee parser = new CSVParserAdaptee();
    List<Question> questions = parser.parse("jeopardy/src/main/resources/sample_game_CSV.csv");
    CategoryManager category = new CategoryManager();
    
    public void startGame(){
        this.questions.forEach(this.category::addQuestion);
    }

    public void selectPlayerCount(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("How many players are playing in the game?");
         int playercount = scanner.nextInt();
         if(playercount <= 0 || playercount > 4){
            System.out.println("Pick in the range of 1-4");
            selectPlayerCount();
         }
         this.pc = playercount;
    }

    public void enterPlayerName(){
        Scanner scanner = new Scanner(System.in);
        
        for(int x=1; x<=pc; x++){
            System.out.println("Enter the name of this player.");
            String name = scanner.nextLine();
            this.players.add(new Player(name));
    }
        if (!this.players.isEmpty()) {
        this.currentPlayer = this.players.get(0);
    }
    }

    public void exitGame(){
        System.exit(0);
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public CategoryManager getCategoryManager() {
    return this.category;
}

}
