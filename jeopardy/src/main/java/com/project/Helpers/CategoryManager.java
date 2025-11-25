package com.project.Helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.Questions.*;

public class CategoryManager {
    private final Map<String, List<Question>> categories = new LinkedHashMap<>();

    public CategoryManager(QuestionManager questionManager){
        questionManager.getQuestions().forEach(this::addQuestion);
    }

    public void addQuestion(Question question) {
        categories.computeIfAbsent(question.getCategory(), k -> new ArrayList<>()).add(question);
    }

    public void removeQuestion(String categoryName, Question questionToRemove) {
        List<Question> list = categories.get(categoryName);
        if (list != null) {
            list.remove(questionToRemove);
        }
    }
    public void removeCategory(String categoryName) {
        categories.remove(categoryName);
    }

    public List<Question> getQuestions(String category) {
        return categories.getOrDefault(category, List.of());
    }

    public Set<String> getCategoryNames() {
        return categories.keySet();
    }

    public Collection<List<Question>> getAllQuestions() {
        return categories.values();
    }

    public Map<String, List<Question>> getAllCatergories() {
        return Map.copyOf(categories);
    }

    public int size() {
        return categories.size();
    }
}