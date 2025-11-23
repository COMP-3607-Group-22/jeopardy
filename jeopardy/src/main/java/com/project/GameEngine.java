package com.project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {

    private String currentCategory = null;
    private Question currentQuestion = null;
    private int pc;
    ArrayList<Player> players = new ArrayList<>();

    public void startGame(){
        //in
    }

    public void selectPlayerCount(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("How many players are playing in the game?");
         int playercount = scanner.nextInt();
         this.pc = playercount;
    }

    public void enterPlayerName(){
        Scanner scanner = new Scanner(System.in);
        
        for(int x=1; x<=pc; x++){
            System.out.println("Enter the name of this player.");
            String name = scanner.nextLine();
            this.players.add(new Player(name));
        } 
    }

    public void selectCategory(String categoryName) {
        ArrayList<String> categories = null;
        for (String cat : categories){
            if (cat.equals(categoryName)){
                this.currentCategory = categoryName;
            }
        }  
    }

    public void selectQuestion(int value,ArrayList<Question> questions){
        for (Question q : questions){
            if(q.getCategory().equals(currentCategory) && q.getValue() == value){
                this.currentQuestion = q;
            }
        }
    }

    public void answerQuestion(){

    }

    public void exitGame(){
        System.exit(0);
    }

    public void generateEventLog(ArrayList<String> history) throws IOException{
        try (FileWriter eventlog = new FileWriter("processmininglog.csv",true)) {
            eventlog.write("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");
            for(String h : history){
                 eventlog.write(h);
            }
        }
    }
    
    public void generateReport(){
        //Input Logic
    }
    public String getCurrentCategory() {
        return this.currentCategory;
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }
}