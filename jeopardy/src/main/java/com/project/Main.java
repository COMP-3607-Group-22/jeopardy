package com.project;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        QuestionManager manager = new QuestionManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "jeopardy/src/main/resources/sample_game_CSV.csv");
        GameInvoker system = new GameInvoker();
        GameEngine xp = new GameEngine();
        
        GameEngineCommand cmd1 = new SelectPlayerCountCommand(xp);
        
 



        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_JSON.json");
        //LoadFileCommand cmd = new LoadFileCommand(manager, "src/main/resources/sample_game_XML.xml");
        cmd.execute();

        System.out.println("Successfully loaded " + manager.getQuestions().size() + " questions\n");

        
        //TEST FOR CATERGORY MANAGER
        var parser = new CSVParserAdaptee();  
        //DIFFERENT PARSER OPTIONS
        //var JsonParser = new JSONParserAdaptee();
        //var XMLParser = new XMLParserAdaptee();               
        List<Question> questions = parser.parse("jeopardy/src/main/resources/sample_game_CSV.csv");
        //List<Question> questions = parser.parse("jeopardy/src/main/resources/sample_game_JSON.json");
        //List<Question> questions = parser.parse("jeopardy/src/main/resources/sample_game_XML.xml");
        var category = new CategoryManager();
        questions.forEach(category::addQuestion);

        System.out.println("Categories: " + category.size());
        for (String cat : category.getCategoryNames()) {
            System.out.println(" ");
            System.out.println(cat);
            List<Question> list = category.getQuestions(cat);
            Collections.sort(list, (a,b) -> a.getValue() - b.getValue());
            for (Question q : list) {
            System.out.println("$" + q.getValue() + " " + q.getQuestion());
            }
        }
        
    }
}   
    
    
    
    
    
    
    
    


