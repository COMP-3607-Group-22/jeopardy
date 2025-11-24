package com.project;
import java.util.UUID;

public class User {

    private final String id;
    private final String name;
    private final int score;

    public User(String name) {
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
}