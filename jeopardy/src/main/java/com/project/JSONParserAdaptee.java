package com.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JSONParserAdaptee implements ParserAdaptee {
    private ArrayList<Question> questions;

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
                            questions.add(init(new Question(), node));
                        } catch (NullPointerException | ClassCastException e) {
                            System.err.println("Not expected format: " + e.getMessage());
                        }
                    }
                }
            }
        }catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return questions;
    }

    public Question init(Question category, JsonNode node){
        if (node.get("Category") != null) {
            category.setCategory(node.get("Category").asText());
        }
        if (node.get("Value") != null) {
            category.setValue(node.get("Value").asInt());
        }
        if (node.get("Question") != null) {
            category.setQuestion(node.get("Question").asText());
        }

        JsonNode options = node.get("Options");
        if (options != null && options.isObject()) {
            String optA = options.get("A") != null ? options.get("A").asText() : "";
            String optB = options.get("B") != null ? options.get("B").asText() : "";
            String optC = options.get("C") != null ? options.get("C").asText() : "";
            String optD = options.get("D") != null ? options.get("D").asText() : "";
            category.setOptions(new ArrayList<>(Arrays.asList(optA, optB, optC, optD)));
        }

        if (node.get("CorrectAnswer") != null) {
            category.setAnswer(node.get("CorrectAnswer").asText());
        }

        return category;
    }
}
