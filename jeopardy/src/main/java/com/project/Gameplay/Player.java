package com.project.Gameplay;
import java.util.UUID;

/**
 * Represents a player in the Jeopardy game with id, name and score.
 */
public class Player {

    private final String id;
    private final String name;
    int score;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = 0;
    }
    /**
     * Return the immutable identifier assigned to this player.
     *
     * @return player id string
     */
    public String getId() {
        return id;
    }

    /**
     * Return the player's display name.
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the player's current score.
     *
     * @return integer score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adjust the player's score by the given amount.
     *
     * @param value delta to add to the player's score (may be negative)
     */
    public void setScore(int value){
        this.score += value;
    }

    /**
     * Return a human-readable representation of the player (name).
     *
     * @return player name
     */
    @Override
    public String toString(){
        return this.name;
    }
}