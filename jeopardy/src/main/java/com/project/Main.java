package com.project;

public class Main {
    public static void main(String[] args) {
        
        QuestionManager manager = new QuestionManager();
        CategoryManager category = new CategoryManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv");
        GameInvoker system = new GameInvoker();
        GameInitialization xd = new GameInitialization();
        GameEngine xp = new GameEngine(category,xd.getPlayers());
        
        
        GameEngineCommand cmd1 = new SelectPlayerCountCommand(xd);
        GameEngineCommand cmd2 = new EnterPlayerNameCommand(xd);
        GameEngineCommand cmd3 = new SelectCategoryCommand(xp);
        GameEngineCommand cmd4 = new SelectQuestionCommand(xp);
        GameEngineCommand cmd5 = new AnswerQuestionCommand(xp);
        GameEngineCommand cmd6 = new StartGameCommand(xd);


        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
    }
}   
    
    
    
    
    
    
    
    


