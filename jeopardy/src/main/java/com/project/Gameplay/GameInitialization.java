package com.project.Gameplay;

import java.util.ArrayList;

import com.project.IO.ConsoleIO;

public class GameInitialization {
    private final ConsoleIO consoleIO;
    public GameInitialization(ConsoleIO consoleIO){
        this.consoleIO = consoleIO;
    }

    private int pc;
    ArrayList<Player> players = new ArrayList<>();

    public void selectPlayerCount(){
         consoleIO.print("\nHow many players are playing in the game?\n");
         int playercount = Integer.parseInt(consoleIO.readLine());
         if(playercount <= 0 || playercount > 4){
            consoleIO.print("Pick in the range of 1-4\n");
            selectPlayerCount();
         }
         this.pc = playercount;
    }

    public void enterPlayerName(){
        if(this.players.size() < this.pc){
            consoleIO.print("Enter the name of this player.\n");
            String name = consoleIO.readLine();
            this.players.add(new Player(name));
        }else{
            consoleIO.print("All players have been entered.\n");
        }
    }

    public ArrayList<Player> getPlayers(){return this.players;}
    public int getPlayerCount(){return this.pc;}
}
