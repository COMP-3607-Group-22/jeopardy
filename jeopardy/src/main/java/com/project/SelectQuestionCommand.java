package com.project;

import java.util.ArrayList;

public class SelectQuestionCommand implements GameEngineCommand {
    private final int value;
    private final GameEngine gameEngine;
    private ArrayList<Question> questions = null;

    public SelectQuestionCommand(GameEngine gameEngine, int value , ArrayList<Question> questions) {
        this.gameEngine = gameEngine;
        this.value = value;
        this.questions = questions;
    }

    @Override 
    public void execute() {
        gameEngine.selectQuestion(value,questions); 
    }
}
