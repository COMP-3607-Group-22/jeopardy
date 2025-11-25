package com.project.Commands;

import com.project.Gameplay.GameEngine;

public class GenerateReportCommand implements GameCommand {

    private final GameEngine gameEngine;

    public GenerateReportCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.generateReport();
    }
}