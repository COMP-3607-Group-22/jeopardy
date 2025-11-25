package com.project.Commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.Categories.QuestionManager;

public class LoadFileCommand implements GameCommand {
    private final String fileName;
    private final QuestionManager questionManager;
    private boolean status;

    public LoadFileCommand(QuestionManager questionManager, String fileName){
        this.fileName = fileName;
        this.questionManager = questionManager;
    }
    @Override
    public void execute(){
        status = this.questionManager.buildQuestions(this.fileName);
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        String formatted = timestamp.format(formatter);
        String state;
        return "Load File," + formatted + ",,,," + (state = this.status ? "Success" : "Failure") + ",";
    }
}
