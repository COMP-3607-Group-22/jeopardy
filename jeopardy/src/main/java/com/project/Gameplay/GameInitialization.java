package com.project.Gameplay;

import java.util.ArrayList;

import com.project.IO.ConsoleIO;

/**
 * Holds temporary state during game setup (player count and names).
 */
public class GameInitialization {
    private final ConsoleIO consoleIO;
    /**
     * Create a GameInitialization helper used during setup.
     *
     * @param consoleIO console abstraction for prompting the user
     */
    public GameInitialization(ConsoleIO consoleIO){
        this.consoleIO = consoleIO;
    }

    private int pc;
    ArrayList<Player> players = new ArrayList<>();

    /**
     * Prompt and set the number of players for this game. Validates the
     * chosen number is between 1 and 4 and re-prompts on invalid input.
     */
    public void selectPlayerCount(){
         consoleIO.print("\nHow many players are playing in the game?\n");
         int playercount = Integer.parseInt(consoleIO.readLine());
         if(playercount <= 0 || playercount > 4){
            consoleIO.print("Pick in the range of 1-4\n");
            selectPlayerCount();
         }
         this.pc = playercount;
    }

    /**
     * Prompt for and append a player name during setup until the configured
     * player count has been reached.
     */
    public void enterPlayerName(){
        if(this.players.size() < this.pc){
            consoleIO.print("Enter the name of this player.\n");
            String name = consoleIO.readLine();
            this.players.add(new Player(name));
        }else{
            consoleIO.print("All players have been entered.\n");
        }
    }

    /** @return the list of players entered during setup */
    public ArrayList<Player> getPlayers(){return this.players;}

    /** @return the configured player count */
    public int getPlayerCount(){return this.pc;}
}
