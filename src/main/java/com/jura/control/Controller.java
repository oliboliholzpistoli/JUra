package com.jura.control;

import com.jura.data.DBController;
import com.jura.data.structures.Recipe;
import com.jura.data.structures.Step;

import java.util.List;

public class Controller {
    DBController dbController;

    public Controller() {
        dbController = new DBController();
        dbController.initialize();
    }

    public List<Recipe> loadRecipes(){
        return dbController.loadRecipes();
    }

    public void createRecipe(String title, int duration, int servings, List<Step> steps){

    }
}
