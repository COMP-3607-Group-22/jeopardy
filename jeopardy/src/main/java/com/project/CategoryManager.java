package com.project;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;

public class CategoryManager {
    private Map<String, List<Question>> categories = new LinkedHashMap<>();

public void addQuestion(Question question) {
    categories.computeIfAbsent(question.getCategory(), k -> new ArrayList<>()).add(question);
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