package com.jura.data.structures;

import java.util.List;
import java.util.Map;

public class Recipe implements DBObject {
    private int id;
    private String title;
    private int duration;
    private int servings;
    private List<Step> steps;
    private Map<Ingredient,int[]> ingredients;

    public Recipe(int id, String title, int duration, int servings, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.servings = servings;
        this.steps = steps;
    }

    @Override
    public String getCreateString() {
        return null;
    }

    @Override
    public String getUpdateString() {
        return null;
    }

    @Override
    public String getDeleteString() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    /**
     * Will add or overwrite ingredient in recipe
     * @param ingredient The ingredients to be added
     * @param quantity The quantity of the ingredient to be added for the selected serving size
     * @param quantitySwitch The indicator for the type of quantity provided, 0 being volume (measured in ml) 1 being mass (measured in g)
     */
    public void addIngredient(Ingredient ingredient,int quantity, int quantitySwitch){
        ingredients.put(ingredient, new int[]{quantity, quantitySwitch});
    }

    public int removeIngredient(Ingredient ingredient){
        if (ingredients.remove(ingredient) != null){
            return 1;
        } else {
            return 0;
        }
    }
}
