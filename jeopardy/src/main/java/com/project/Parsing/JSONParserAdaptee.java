package com.project.Parsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.project.Questions.Question;
import com.project.Questions.QuestionBuilder;

public class JSONParserAdaptee implements ParserAdaptee {
    private final ArrayList<Question> questions;

    public JSONParserAdaptee(){
        this.questions = new ArrayList<>();
    }

    @Override
    public ArrayList<Question> parse(String fileName){
        questions.clear();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));
            if(rootNode.isArray()){
                ArrayNode arrayNode = (ArrayNode) rootNode;

                for(JsonNode node : arrayNode){
                    if(node.isObject()){
                        try {
                            QuestionBuilder builder = QuestionBuilder.create();

                            if(node.get("Category") != null) {
                                builder.setCategory(node.get("Category").asText());
                            }
                            if(node.get("Value") != null) {
                                builder.setValue(node.get("Value").asInt());
                            }
                            if(node.get("Question") != null) {
                                builder.setQuestion(node.get("Question").asText());
                            }
                            
                            JsonNode options = node.get("Options");
                            if(options != null && options.isObject()) {
                                String optA = options.get("A") != null ? options.get("A").asText() : "";
                                String optB = options.get("B") != null ? options.get("B").asText() : "";
                                String optC = options.get("C") != null ? options.get("C").asText() : "";
                                String optD = options.get("D") != null ? options.get("D").asText() : "";
                                builder.setOptions(new ArrayList<>(Arrays.asList(optA, optB, optC, optD)));
                            }
                            if(node.get("CorrectAnswer") != null) {
                                builder.setAnswer(node.get("CorrectAnswer").asText());
                            }
                            questions.add(builder.build());

                        } catch (NullPointerException | ClassCastException e) {
                            System.err.println("Not expected format: " + e.getMessage());
                        } catch (IllegalStateException e) {
                            System.err.println("Builder validation failed: " + e.getMessage());
                        }
                    }
                }
            }
        }catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return questions;
    }

}
