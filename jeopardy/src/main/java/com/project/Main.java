package com.project;

public class Main {
    public static void main(String[] args) {
        QuestionManager manager = new QuestionManager();
        //LoadFileCommand cmd = new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");

        GameInvoker system = new GameInvoker();
        GameEngine xp = new GameEngine();
        User user = new User("System");

        system.runCommand(user, new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv"));
        system.runCommand(user, new SelectPlayerCountCommand(xp));
        system.runCommand(user, new EnterPlayerNameCommand(xp));
        system.runCommand(user, new SelectCategoryCommand(xp));
        system.runCommand(user, new OutputEventLogCommand(new OutputLogs(), system.getHistory()));
    }
}