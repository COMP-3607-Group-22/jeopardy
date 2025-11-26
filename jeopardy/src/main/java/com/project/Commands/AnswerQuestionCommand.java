package com.project.Commands;

import com.project.Gameplay.GameEngine;
import com.project.Helpers.LogHelper;

/**
 * Command that asks the game engine to accept and process an answer for the
 * current question.
 */
public class AnswerQuestionCommand implements GameCommand {
    private final GameEngine gameEngine;
    public AnswerQuestionCommand(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.answerQuestion();
    }
    /**
     * @return a CSV-like log entry representing the most recent answer
     */
    @Override
    public String toString(){
        if(gameEngine.getGivenAnswer() != null){
            return gameEngine.getLastPlayer() + ",Answer Question," + new LogHelper().getTimeStamp()
            + gameEngine.getCurrentCategory() + ","
            + gameEngine.getCurrentQuestion().getValue() + ","
            + gameEngine.getGivenAnswer() + ","
            + (gameEngine.isAnswerCorrect() ? "Correct" : "Incorrect") + ","
            + gameEngine.getLastPlayer().getScore();
        }
        return "";
    }
}
