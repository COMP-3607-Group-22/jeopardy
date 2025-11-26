package com.project.Gameplay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.project.Helpers.CategoryManager;
import com.project.Helpers.ReportHelper;
import com.project.IO.ConsoleIO;
import com.project.Questions.Question;


public final class GameEngine{
    private String currentCategory = null;
    private Question currentQuestion = null;
    private String givenAnswer;
    private Player currentPlayer = null;
    private Player lastPlayer;
    private int currentPlayerIndex = 0;
    private int totalTurns = 1;

    private final CategoryManager category;
    private final ArrayList<Player> players;
    private final ReportHelper reportHelper;
    private final ConsoleIO consoleIO;
    
    public GameEngine (CategoryManager category, ArrayList<Player> players, ConsoleIO consoleIO, ReportHelper reportHelper){
        this.category = category;
        this.players = players;
        this.currentPlayer = this.players.get(0);
        this.consoleIO = consoleIO;
        this.reportHelper = reportHelper;
    }

    public void selectCategory() {
        boolean found = false;
        this.currentQuestion = null;
        this.currentCategory = null;
        consoleIO.println("\nSelect a category from the ones below\n");
        consoleIO.println("-------------------------------------------------");

        for (String cat : category.getCategoryNames()) {
            consoleIO.println(cat);
        }
        consoleIO.println("-------------------------------------------------");
        consoleIO.print("\nYour requested category: ");
        String chosenCategory = consoleIO.readLine();

        for (String cat : category.getCategoryNames()) {
            if(chosenCategory.equals(cat)){
                this.currentCategory = chosenCategory;
                found = true;
            }
        }

        if (found == false){
            consoleIO.println("\nInvalid Category.");
            selectCategory();
        }
        else{
            consoleIO.println("\n*****************************************");
            consoleIO.println("You have choosen " + this.currentCategory);
            consoleIO.println("*****************************************");
        }
    }

    public void selectQuestion(){
        boolean found = false;
        consoleIO.println("\nSelect a question by inputting the dollar value.\n");
        List<Question> list = category.getQuestions(this.currentCategory);
        Collections.sort(list, (a,b) -> a.getValue() - b.getValue());
        for (Question q : list) {
            consoleIO.println("$" + q.getValue());
        }
        consoleIO.print("\nYour requested question: $");
        int chosenQuestion = Integer.parseInt(consoleIO.readLine());
        for (Question q : list) {
            if(chosenQuestion == q.getValue()){
                this.currentQuestion = q;
                consoleIO.println("\n*****************************************");
                consoleIO.println("\nYou have choosen the question for $" + chosenQuestion);
                consoleIO.println("\n*****************************************");
                found=true;
            }
        }
        if(found == false){
            consoleIO.println("\nInvalid Question Choice.\n");
            selectQuestion();
        }

        consoleIO.println("\nFor $" + currentQuestion.getValue() + "\n\n" + currentQuestion.getQuestion() + "\n\n" + currentQuestion.getOptionsAsString());
    }

    public void answerQuestion(){
        consoleIO.print("\nYour Choice: ");
        this.givenAnswer = consoleIO.readLine();

        if(isAnswerCorrect()){
            consoleIO.println("\nYou got the answer correct!");
            currentPlayer.setScore(currentQuestion.getValue());
            consoleIO.println(getScores());
        }
        else{
            consoleIO.println("\nThe correct answer was: " + currentQuestion.getAnswer());
            consoleIO.println(getScores());
        }

        category.removeQuestion(this.currentCategory, this.currentQuestion); //removes questions from list

        List<Question> remainingQuestions = category.getQuestions(this.currentCategory); //remove empty categories from list
        if (remainingQuestions.isEmpty()) {
            category.removeCategory(this.currentCategory);
        }

        if(getCategoryEmpty()){
            consoleIO.println("\n*****************************************");
            consoleIO.println("No more categories exist. The game has concluded.");
            consoleIO.println("\n*****************************************");
        }

        addTurnSummary();
        this.totalTurns++;
        this.nextPlayerTurn();
    }

    public void nextPlayerTurn() {
        this.lastPlayer = this.currentPlayer;
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        consoleIO.println("\n---------------------------------");
        consoleIO.println("It is now " + this.currentPlayer.getName() + "'s turn!");
        consoleIO.println("---------------------------------");
    }

    public String getCurrentCategory() {return this.currentCategory;}
    public boolean getCategoryEmpty() {return this.category.getAllCatergories().isEmpty();}
    public Question getCurrentQuestion() {return this.currentQuestion;}
    public Player getCurrentPlayer(){return this.currentPlayer;}
    public ArrayList<Player> getPlayers(){return this.players;}
    public Player getLastPlayer(){return this.lastPlayer;}
    public ReportHelper getReportHelper(){return this.reportHelper;}
    public String getGivenAnswer(){return this.givenAnswer;}
    public boolean isAnswerCorrect(){return this.givenAnswer.equals(this.currentQuestion.getAnswer());}
    public String getScores(){
        StringBuilder scores = new StringBuilder("Current Scores:\n");
        for(Player p : this.players){
            scores.append(p.getName()).append(": ").append(p.getScore()).append("\n");
        }
        return scores.toString();
    }

    public void addTurnSummary(){
        reportHelper.addTurnSummary(
            "Turn " + this.totalTurns + ": " + this.currentPlayer + " selected " + this.currentCategory + " for " + this.currentQuestion.getValue()
            + "\nQuestion: " + this.currentQuestion.getQuestion()
            + "\nAnswer: " + this.givenAnswer + " - " + (this.givenAnswer.equals(this.currentQuestion.getAnswer()) ? "Correct (+" + this.currentQuestion.getValue() : "Incorrect (+0") + " pts)"
            + "\nScore after turn: " + this.currentPlayer + " = " + this.currentPlayer.getScore() + "\n\n"
        );
    }
}