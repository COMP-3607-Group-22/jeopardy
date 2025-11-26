package com.project.Gameplay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.project.Helpers.CategoryManager;
import com.project.Helpers.ReportHelper;
import com.project.IO.ConsoleIO;
import com.project.Questions.Question;


/**
 * Core game engine that tracks players, categories, questions and scores.
 * Exposes behavior used by commands to select categories/questions and answer them.
 */
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
    
    /**
     * Construct a GameEngine with the provided category manager, players,
     * console IO and report helper.
     *
     * @param category category manager containing categories and questions
     * @param players the list of players participating
     * @param consoleIO console abstraction for input/output
     * @param reportHelper helper used to accumulate report entries
     */
    public GameEngine (CategoryManager category, ArrayList<Player> players, ConsoleIO consoleIO, ReportHelper reportHelper){
        this.category = category;
        this.players = players;
        this.currentPlayer = this.players.get(0);
        this.consoleIO = consoleIO;
        if (reportHelper == null) {
            this.reportHelper = new ReportHelper(this.players, "AUTO-GENERATED-CASE");
        } else {
            this.reportHelper = reportHelper;
        }
    }

    /**
     * Prompt the user to select a category from the available categories.
     * Invalid selections re-prompt until a valid category is chosen.
     */
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
            /**
             * If the chosen category is invalid present a message and retry.
             */
            consoleIO.println("\nInvalid Category.");
            selectCategory();
        }
        else{
            consoleIO.println("\n*****************************************");
            consoleIO.println("You have choosen " + this.currentCategory);
            consoleIO.println("*****************************************");
        }
    }

    /**
     * Prompt the user to select a question by dollar value from the
     * currently selected category. Invalid selections re-prompt.
     */
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
            /**
             * If the question value is invalid prompt again.
             */
            consoleIO.println("\nInvalid Question Choice.\n");
            selectQuestion();
        }

        consoleIO.println("\nFor $" + currentQuestion.getValue() + "\n\n" + currentQuestion.getQuestion() + "\n\n" + currentQuestion.getOptionsAsString());
    }

    /**
     * Read the player's answer, evaluate correctness, update scores and
     * category state, then advance to the next player's turn.
     */
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
            return;
        }

        addTurnSummary();
        this.totalTurns++;
        this.nextPlayerTurn();
    }

    /**
     * Rotate the active player to the next player in round-robin order
     * and announce the active player.
     */
    public void nextPlayerTurn() {
        this.lastPlayer = this.currentPlayer;
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        consoleIO.println("\n---------------------------------");
        consoleIO.println("It is now " + this.currentPlayer.getName() + "'s turn!");
        consoleIO.println("---------------------------------");
    }

    /**
     * Return the currently selected category name.
     *
     * @return the currently selected category name, or {@code null} when none is selected
     */
    public String getCurrentCategory() {return this.currentCategory;}

    /**
     * Check whether there are no categories remaining in the game.
     *
     * @return {@code true} when there are no categories remaining
     */
    public boolean getCategoryEmpty() {return this.category.getAllCategories().isEmpty();}

    /**
     * Return the currently selected question.
     *
     * @return the selected {@code Question}, or {@code null} when none is selected
     */
    public Question getCurrentQuestion() {return this.currentQuestion;}

    /**
     * Return the player whose turn it currently is.
     *
     * @return the active {@code Player}
     */
    public Player getCurrentPlayer(){return this.currentPlayer;}

    /**
     * Return the list of players participating in the game.
     *
     * @return an ArrayList of {@code Player} objects
     */
    public ArrayList<Player> getPlayers(){return this.players;}

    /**
     * Return the player who most recently took a turn.
     *
     * @return the last {@code Player} who acted, or {@code null} if none
     */
    public Player getLastPlayer(){return this.lastPlayer;}

    /**
     * Return the report helper used to collect turn summaries and final scores.
     *
     * @return the {@code ReportHelper} instance
     */
    public ReportHelper getReportHelper(){return this.reportHelper;}

    /**
     * Return the last answer provided by the current player.
     *
     * @return the answer string most recently given
     */
    public String getGivenAnswer(){return this.givenAnswer;}

    /**
     * Determine whether the most recently provided answer matches the
     * current question's correct answer.
     *
     * @return true if the last given answer is correct
     */
    public boolean isAnswerCorrect(){return this.givenAnswer.equals(this.currentQuestion.getAnswer());}

    /**
     * Construct a multi-line string representing current player scores.
     *
     * @return score summary for display
     */
    /**
     * Construct a multi-line string representing current player scores.
     *
     * @return score summary for display
     */
    public String getScores(){
        StringBuilder scores = new StringBuilder("Current Scores:\n");
        for(Player p : this.players){
            scores.append(p.getName()).append(": ").append(p.getScore()).append("\n");
        }
        return scores.toString();
    }

    /**
     * Append a turn summary line to the report helper describing the
     * current player's action and the outcome.
     */
    public void addTurnSummary(){
        reportHelper.addTurnSummary(
            "Turn " + this.totalTurns + ": " + this.currentPlayer + " selected " + this.currentCategory + " for " + this.currentQuestion.getValue()
            + "\nQuestion: " + this.currentQuestion.getQuestion()
            + "\nAnswer: " + this.givenAnswer + " - " + (this.givenAnswer.equals(this.currentQuestion.getAnswer()) ? "Correct (+" + this.currentQuestion.getValue() : "Incorrect (+0") + " pts)"
            + "\nScore after turn: " + this.currentPlayer + " = " + this.currentPlayer.getScore() + "\n\n"
        );
    }
}