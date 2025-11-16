package com.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONParser implements ParserAdaptee {
    private ArrayList<Category> categories;

    public JSONParser(){
        this.categories = new ArrayList<>();
    }

    public ArrayList<Category> parse(String fileName){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));
            if(rootNode.isArray()){
                ArrayNode arrayNode = (ArrayNode) rootNode;

                for(JsonNode node : arrayNode){
                    if(node.isObject()){
                        categories.add(init(new Category(), node));
                    }
                }
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return categories;
    }

    public Category init(Category category, JsonNode node){
        category.setCategory(node.get("Category").asText());
        category.setValue(node.get("Value").asInt());
        category.setQuestion(node.get("Question").asText());
        JsonNode options = node.get("Options");
        category.setOptions(new ArrayList<>(Arrays.asList(options.get("A").asText(), options.get("B").asText(), options.get("C").asText(), options.get("D").asText())));
        category.setAnswer(node.get("CorrectAnswer").asText());
        return category;
    }
}
