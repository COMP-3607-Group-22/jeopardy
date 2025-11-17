package com.project;
public class SelectQuestionCommand implements GameEngineCommand {
    private final int questionId;
    private final GameEngine gameEngine;

    public SelectQuestionCommand(GameEngine gameEngine, int questionId) {
        this.gameEngine = gameEngine;
        this.questionId = questionId;
    }

    @Override 
    public void execute() {
        gameEngine.selectQuestion(questionId); 
    }
}
