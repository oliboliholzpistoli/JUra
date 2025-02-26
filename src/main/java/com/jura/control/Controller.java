package com.jura.control;

import com.jura.data.DBController;
import com.jura.data.structures.Ingredient;
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

    /**
     * @param title Title of recipe
     * @param duration Duration of recipe
     * @param servings Servings of recipe
     * @param steps List of steps
     * @param ingredients Ingredients as List with 3 segment array, with first segment being the ingredient object. Second and third segment are quantity and quantityswitch respectively
     */
    public void createRecipe(String title, int duration, int servings, List<Step> steps, List<Object[]> ingredients){
        //Create recipe object
        Recipe recipe = new Recipe(-1,title,duration,servings,steps);
        for (Object[] ingredient:ingredients){
            recipe.addIngredient((Ingredient) ingredient[0], (Integer) ingredient[1], (Integer) ingredient[2]);
        }
        recipe = (Recipe) dbController.createObject(recipe);

        //Create steps
        for (Step step:recipe.getSteps()){
            step.setRecipeID(recipe.getId());
            dbController.createObject(step);
        }
    }
}
