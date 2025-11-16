package com.project;

public class Main {
    public static void main(String[] args) {
        CategoryManager manager = new CategoryManager();
        LoadFileCommand cmd = new LoadFileCommand(manager, "sample_game_CSV.csv");
        cmd.execute();
        System.out.println("Loaded " + manager.getCategories().size() + " categories via LoadFileCommand");
    }
}

