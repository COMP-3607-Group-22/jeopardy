package com.project;
import java.util.ArrayList;

public class OutputEventLogCommand implements GameEngineCommand {

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