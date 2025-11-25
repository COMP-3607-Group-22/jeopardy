package com.project.Gameplay;

import java.util.ArrayList;
import java.util.Scanner;

public class GameInitialization {

    private int pc;
    ArrayList<Player> players = new ArrayList<>();

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
        }
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public int getPlayerCount(){return this.pc;}
}
