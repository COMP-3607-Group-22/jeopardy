package com.project.Gameplay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.project.Helpers.*;
import com.project.Questions.Question;


public final class GameEngine {
    private String currentCategory = null;
    private Question currentQuestion = null;
    private String givenAnswer;
    private Player currentPlayer = null;
    private Player lastPlayer;
    private int currentPlayerIndex = 0;
    private int totalTurns = 1;
    private boolean categoryEmpty;

    private CategoryManager category;
    private ArrayList<Player> players;
    private ReportHelper reportHelper;
    
    public GameEngine (CategoryManager category,ArrayList<Player> players){
        this.category = category;
        this.players = players;
        this.currentPlayer = this.players.get(0);
        this.reportHelper = new ReportHelper(this);
    }

    public void selectCategory() {
        boolean found = false;
        this.currentQuestion = null;
        this.currentCategory = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSelect a category from the ones below\n");
        System.out.println("-------------------------------------------------");

        for (String cat : category.getCategoryNames()) {
            System.out.println(cat);
        }
        System.out.println("-------------------------------------------------");
        System.out.print("\nYour requested category : ");
        String chosenCategory = scanner.nextLine();

        for (String cat : category.getCategoryNames()) {
            if(chosenCategory.equals(cat)){
                this.currentCategory = chosenCategory;
                found = true;
            }
        }
        if (found == false){
            System.out.println("\nInvalid Category.");
            selectCategory();
        }
        else{
            System.out.println("\n*****************************************");
            System.out.println("You have choosen " + this.currentCategory);
            System.out.println("*****************************************");
        }
    }

    public void selectQuestion(){
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect a question by inputting the dollar value.\n");
        List<Question> list = category.getQuestions(this.currentCategory);
        Collections.sort(list, (a,b) -> a.getValue() - b.getValue());
        for (Question q : list) {
            System.out.println("$" + q.getValue());
            }
        System.out.print("\nYour requested question : $");    
        int chosenQuestion = scanner.nextInt();
        for (Question q : list) {
            if(chosenQuestion == q.getValue()){
                this.currentQuestion = q;
                System.out.println("\n*****************************************");
                System.out.println("\nYou have choosen the question for $" + chosenQuestion);
                System.out.println("\n*****************************************");
                found=true;
            }
        }
        if(found == false){
            System.out.println("\nInvalid Question Choice.\n");
            selectQuestion();
        }

        System.out.println("\nFor $" + currentQuestion.getValue() + "\n\n" + currentQuestion.getQuestion() + "\n\n" + currentQuestion.getOptions());
    }

    public void answerQuestion(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nYour Choice : ");
        this.givenAnswer = scanner.nextLine();

        if(isAnswerCorrect()){
            System.out.println("\nYou got the answer correct!");
            currentPlayer.setScore(currentQuestion.getValue());
            System.out.println("\nYour new score is " + currentPlayer.getScore());
        }
        else{
            System.out.println("\nThe correct answer was: " + currentQuestion.getAnswer());
            System.out.println("\nYour new score is " + currentPlayer.getScore());
        }

        category.removeQuestion(this.currentCategory, this.currentQuestion); //removes questions from list

        List<Question> remainingQuestions = category.getQuestions(this.currentCategory); //remove empty categories from list
        if (remainingQuestions.isEmpty()) {
            category.removeCategory(this.currentCategory);
        }

        if(category.getAllCatergories().isEmpty()){
            System.out.println("\n*****************************************");
            System.out.println("No more categories exist. The game has included");
            System.out.println("\n*****************************************");
            this.categoryEmpty = true;
        } else{
             this.categoryEmpty = false;
        }

        addTurnSummary();
        this.totalTurns++;
        this.nextPlayerTurn();
    }

    public void nextPlayerTurn() {
        this.lastPlayer = this.currentPlayer;
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        System.out.println("\n---------------------------------");   
        System.out.println("It is now " + this.currentPlayer.getName() + "'s turn!");
        System.out.println("---------------------------------");
    }

    public String getCurrentCategory() {
        return this.currentCategory;
    }

     public boolean getCategoryEmpty() {
        return this.categoryEmpty;
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public Player getLastPlayer(){
        return this.lastPlayer;
    }

    public ReportHelper getReportHelper(){
        return this.reportHelper;
    }

    public String getGivenAnswer(){
        return this.givenAnswer;
    }

    public boolean isAnswerCorrect(){
        return this.givenAnswer.equals(this.currentQuestion.getAnswer());
    }

    public void addTurnSummary(){
        reportHelper.addTurnSummary(
            "Turn " + this.totalTurns + ": " + this.currentPlayer + " selected " + this.currentCategory + " for " + this.currentQuestion.getValue()
            + "\nQuestion: " + this.currentQuestion.getQuestion()
            + "\nAnswer: " + this.givenAnswer + " - " + (this.givenAnswer.equals(this.currentQuestion.getAnswer()) ? "Correct (+" : "Incorrect (-") + this.currentQuestion.getValue() + " pts)"
            + "\nScore after turn: " + this.currentPlayer + " = " + this.currentPlayer.getScore() + "\n\n"
        );
    }
}