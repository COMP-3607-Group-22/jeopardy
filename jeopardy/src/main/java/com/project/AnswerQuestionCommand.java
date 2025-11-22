package com.project;

public class AnswerQuestionCommand implements GameEngineCommand {
    private final GameEngine gameEngine;
    public AnswerQuestionCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override 
    public void execute() {
        gameEngine.answerQuestion();
    }
}
