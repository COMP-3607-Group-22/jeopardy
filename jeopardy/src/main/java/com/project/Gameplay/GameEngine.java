package com.project.Gameplay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.project.Helpers.CategoryManager;
import com.project.Questions.Question;


public class GameEngine {

    private String currentCategory = null;
    private Question currentQuestion = null;
    private String givenAnswer;
    private Player currentPlayer = null;
    private Player lastPlayer;
    private int currentPlayerIndex = 0;
    private int totalTurns = 0;

    CategoryManager category;
    ArrayList<Player> players;
    
    
    public GameEngine (CategoryManager category,ArrayList<Player> players){
        this.category = category;
        this.players = players;
        this.currentPlayer = this.players.get(0);
    }

    public void selectCategory() {
        boolean found = false;
        this.currentQuestion = null;
        this.currentCategory = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a category from the ones below");

        for (String cat : category.getCategoryNames()) {
            System.out.println(" ");
            System.out.println(cat);
        }
        System.out.println("\n");
        String chosenCategory = scanner.nextLine();

        for (String cat : category.getCategoryNames()) {
            if(chosenCategory.equals(cat)){
                this.currentCategory = chosenCategory;
                found = true;
            }
        }
        if (found == false){
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
        if(found == false){
            System.out.println("Invalid Question Choice.\n");
            selectQuestion();
        }

        System.out.println("For $" + currentQuestion.getValue() + "\n" + currentQuestion.getQuestion() + "\n" + currentQuestion.getOptions());
    }

    public void answerQuestion(){
        Scanner scanner = new Scanner(System.in);
        this.givenAnswer = scanner.nextLine();

        if(givenAnswer.equals(currentQuestion.getAnswer())){
            System.out.println("You got the answer correct! yay");
            currentPlayer.setScore(currentQuestion.getValue());
            System.out.println("Your new score is " + currentPlayer.getScore());
        }
        else{
            System.out.println("The correct answer was: " + currentQuestion.getAnswer());
            System.out.println("Your new score is " + currentPlayer.getScore());
        }

        category.removeQuestion(this.currentCategory, this.currentQuestion); //removes questions from list

        List<Question> remainingQuestions = category.getQuestions(this.currentCategory); //remove empty categories from list
        if (remainingQuestions.isEmpty()) {
            category.removeCategory(this.currentCategory);
        }

        this.totalTurns++;
        this.nextPlayerTurn();
    }

    public void nextPlayerTurn() {
        this.lastPlayer = this.currentPlayer;
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        System.out.println("\nIt is now " + this.currentPlayer.getName() + "'s turn!\n");
    }

    public String getCurrentCategory() {
        return this.currentCategory;
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public int getTotalTurnsPlayed() {
        return this.totalTurns;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public Player getLastPlayer(){
        return this.lastPlayer;
    }
}