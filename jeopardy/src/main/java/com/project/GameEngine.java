package com.project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class GameEngine {

    private String currentCategory = null;
    private Question currentQuestion = null;
    private int pc;
    ArrayList<User> players = new ArrayList<>();
    CSVParserAdaptee parser = new CSVParserAdaptee();
    List<Question> questions = parser.parse("jeopardy/src/main/resources/sample_game_CSV.csv");
    CategoryManager category = new CategoryManager();
    

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
            this.players.add(new User(name));
    }
    }

    public void selectCategory() {
          boolean found = false;
          this.questions.forEach(this.category::addQuestion);
          Scanner scanner = new Scanner(System.in);

          System.out.println("Select a category from the ones below");

          for (String cat : category.getCategoryNames()) {
            System.out.println(" ");
            System.out.println(cat);
            }

        String chosenCategory = scanner.nextLine();

        for (String cat : category.getCategoryNames()) {
            if(chosenCategory.equals(cat)){
                this.currentCategory = chosenCategory;
                found = true;
            }
        }
        if (found ==  false){
            System.out.println("Invalid Category.");
            selectCategory();
        }
        else{
            System.out.println("You have choosen " + this.currentCategory);
        }
        
}

    public void selectQuestion(){
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a question by inputting the dollar value (The $ is there already)");
        List<Question> list = category.getQuestions(this.currentCategory);
        Collections.sort(list, (a,b) -> a.getValue() - b.getValue());
        for (Question q : list) {
            System.out.println("$" + q.getValue());
            }
        int chosenQuestion = scanner.nextInt();
        for (Question q : list) {
            if(chosenQuestion == q.getValue()){
                this.currentQuestion = q;
                System.out.println("You have choosen the question for $" + chosenQuestion);
                found=true;
            }
            }
        System.out.println("For $" + currentQuestion.getValue() + "\n" + currentQuestion.getQuestion() + "\n" + currentQuestion.getOptions());

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