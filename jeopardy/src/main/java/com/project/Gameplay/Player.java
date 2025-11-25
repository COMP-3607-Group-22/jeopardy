package com.project.Gameplay;
import java.util.UUID;

public class Player {

    private final String id;
    private final String name;
    int score;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int value){
        this.score += value;
    }

    @Override
    public String toString(){
        return this.name;
    }
}