package com.project.Helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.Questions.Question;
import com.project.Questions.QuestionManager;

/**
 * Manages categories and the mapping of category names to question lists.
 */
public class CategoryManager {
    private final Map<String, List<Question>> categories = new LinkedHashMap<>();

    /**
     * Create a CategoryManager and populate it from the provided
     * QuestionManager.
     *
     * @param questionManager source of questions used to seed categories
     */
    public CategoryManager(QuestionManager questionManager){
        questionManager.getQuestions().forEach(this::addQuestion);
    }

    /**
     * Add a question into the manager under its category key.
     *
     * @param question the question to add; its category determines the bucket
     */
    public void addQuestion(Question question) {
        categories.computeIfAbsent(question.getCategory(), k -> new ArrayList<>()).add(question);
    }

    /**
     * Remove a specific question from a given category list.
     *
     * @param categoryName the category name from which to remove
     * @param questionToRemove the question instance to remove
     */
    public void removeQuestion(String categoryName, Question questionToRemove) {
        List<Question> list = categories.get(categoryName);
        if (list != null) {
            list.remove(questionToRemove);
        }
    }
    /**
     * Remove an entire category and its associated questions.
     *
     * @param categoryName name of the category to remove
     */
    public void removeCategory(String categoryName) {
        categories.remove(categoryName);
    }
    /**
     * Get the list of questions for a specific category.
     *
     * @param category category name
     * @return a list of questions for the category (empty list when none)
     */
    public List<Question> getQuestions(String category) {
        return categories.getOrDefault(category, List.of());
    }
    /**
     * Return the set of category names currently managed.
     *
     * @return set of category name strings
     */
    public Set<String> getCategoryNames() {
        return categories.keySet();
    }
    /**
     * Return a collection of question lists (one per category).
     *
     * @return collection containing each category's question list
     */
    public Collection<List<Question>> getAllQuestions() {
        return categories.values();
    }
    /**
     * Return a snapshot copy of the internal categories map.
     *
     * @return an unmodifiable map of category name to question list
     */
    public Map<String, List<Question>> getAllCategories() {
        return Map.copyOf(categories);
    }

    /**
     * Return the number of categories managed.
     *
     * @return number of categories
     */
    public int size() {
        return categories.size();
    }
}