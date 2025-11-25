package com.project.Gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.Helpers.CategoryManager;
import com.project.Parsing.CSVParserAdaptee;
import com.project.Questions.Question;

public class GameInitialization {

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

        if(this.players.size() < this.pc){
            System.out.println("Enter the name of this player.");
            String name = scanner.nextLine();
            this.players.add(new Player(name));
        }else{
            System.out.println("All players have been entered.");
            return;
        }
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public CategoryManager getCategoryManager() {
        return this.category;
    }
}
