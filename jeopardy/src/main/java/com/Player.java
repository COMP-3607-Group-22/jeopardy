package com;
import java.util.UUID;

public class Player {
    
    private final String id;
    private final String name;
    private int score;

    public Player(String name){

        this.id = UUID.randomUUID().toString();;
        this.name = name;
        this.score = 0;
    }
}
