package com.jura.data.structures;

import java.util.ArrayList;

public class Ingredient implements DBObject{

    private int id;
    private String name;
    private ArrayList<IngredientCategory> ingredientCategory;

    public Ingredient(int id, String name, ArrayList<IngredientCategory> ingredientCategory) {
        this.id = id;
        this.name = name;
        this.ingredientCategory = ingredientCategory;
    }

    @Override
    public String getCreateString() {
        return null;
    }

    @Override
    public String getUpdateString() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<IngredientCategory> getIngredientCategory() {
        return ingredientCategory;
    }

    public void setIngredientCategory(ArrayList<IngredientCategory> ingredientCategory) {
        this.ingredientCategory = ingredientCategory;
    }
}
