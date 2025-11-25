package com.project.Commands;
import java.util.ArrayList;

import com.project.Gameplay.OutputLogs;

public class OutputEventLogCommand implements GameCommand {

    private final OutputLogs outputLogs;
    private final ArrayList<String> history;

    public OutputEventLogCommand(OutputLogs outputLogs, ArrayList<String> history) {
        this.outputLogs = outputLogs;
        this.history = history;
    }

    @Override
    public void execute() {
        outputLogs.generateEventLog(history);
}
}