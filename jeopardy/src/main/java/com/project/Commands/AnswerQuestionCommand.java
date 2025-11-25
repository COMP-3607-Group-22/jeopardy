package com.project.Commands;

import com.project.Gameplay.GameEngine;

public class AnswerQuestionCommand implements GameCommand {
    private final GameEngine gameEngine;
    public AnswerQuestionCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.answerQuestion();
    }
}
