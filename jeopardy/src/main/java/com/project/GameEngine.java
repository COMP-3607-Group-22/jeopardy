package com.project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {

    private String currentCategory = null;
    private Question currentQuestion = null;

    public void startGame(){
        //in
    }

    public void selectPlayerCount(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("How many players are playing in the game?");
         int playercount = scanner.nextInt();
    }

    public void enterPlayerName(){

    }

    public void selectCategory(String categoryName) {
        ArrayList<String> categories = null;
        for (String cat : categories){
            if (cat.equals(categoryName)){
                currentCategory = categoryName;
            }
        }  
    }

    public void selectQuestion(int value,ArrayList<Question> questions){
        for (Question q : questions){
            if(q.getCategory().equals(currentCategory) && q.getValue() == value){
                currentQuestion = q;
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
        return currentCategory;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}