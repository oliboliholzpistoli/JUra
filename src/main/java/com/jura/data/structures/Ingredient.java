package com.jura.data.structures;

import com.jura.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class Ingredient implements DBObject{

    private int id;
    private String name;
    private List<IngredientCategory> ingredientCategories;

    public Ingredient(int id, String name, List<IngredientCategory> ingredientCategory) {
        this.id = id;
        this.name = name;
        this.ingredientCategories = ingredientCategory;
    }

    @Override
    public String getCreateString() {
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        fields.add("ID");
        fields.add("NAME");

        values.add(Integer.toString(id));
        values.add("'"+name+"'");

        StringBuilder out = new StringBuilder(DBUtil.generateCreateString("Ingredient",fields,values));

        for (IngredientCategory ingredientCategory:ingredientCategories){
            fields.clear();
            values.clear();

            fields.add("IngredientID");
            fields.add("IngredientCategoryID");

            values.add(Integer.toString(id));
            values.add(Integer.toString(ingredientCategory.getId()));

            out.append(DBUtil.generateCreateString("Ingredient_IngredientCategory",fields,values));
        }

        return out.toString();
    }

    @Override
    public String getUpdateString() {
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> identifierDefinitions = new ArrayList<>();
        ArrayList<String> identifierValues = new ArrayList<>();

        fields.add("ID");
        fields.add("NAME");

        values.add(Integer.toString(id));
        values.add("'"+name+"'");

        identifierDefinitions.add("ID");

        identifierValues.add(Integer.toString(id));

        StringBuilder out = new StringBuilder(DBUtil.generateUpdateString("Ingredient",identifierDefinitions,identifierValues,fields,values));

        //Deleting then recreating all relations so I don't have to check which ones exist

        identifierDefinitions.clear();
        identifierDefinitions.add("IngredientID");
        identifierDefinitions.add("IngredientCategoryID");

        for (IngredientCategory ingredientCategory:ingredientCategories){
            identifierValues.clear();
            identifierValues.add(Integer.toString(id));
            identifierValues.add(Integer.toString(ingredientCategory.getId()));
            out.append(DBUtil.generateDeleteString("Ingredient_IngredientCategory",identifierDefinitions,identifierValues));
        }

        for (IngredientCategory ingredientCategory:ingredientCategories){
            fields.clear();
            values.clear();
            identifierDefinitions.clear();
            identifierValues.clear();

            fields.add("IngredientID");
            fields.add("IngredientCategoryID");

            values.add(Integer.toString(id));
            values.add(Integer.toString(ingredientCategory.getId()));

            out.append(DBUtil.generateCreateString("Ingredient_IngredientCategory",fields,values));
        }

        return out.toString();
    }

    @Override
    public String getDeleteString() {
        ArrayList<String> identifierDefinitions = new ArrayList<>();
        ArrayList<String> identifierValues = new ArrayList<>();

        identifierDefinitions.add("ID");
        identifierValues.add(Integer.toString(id));
        StringBuilder out = new StringBuilder(DBUtil.generateDeleteString("Ingredients",identifierDefinitions,identifierValues));

        identifierDefinitions.clear();
        identifierDefinitions.add("IngredientID");
        identifierDefinitions.add("IngredientCategoryID");

        for (IngredientCategory ingredientCategory:ingredientCategories){
            identifierValues.clear();
            identifierValues.add(Integer.toString(id));
            identifierValues.add(Integer.toString(ingredientCategory.getId()));
            out.append(DBUtil.generateDeleteString("Ingredient_IngredientCategory",identifierDefinitions,identifierValues));
        }
        return out.toString();
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

    public List<IngredientCategory> getIngredientCategory() {
        return ingredientCategories;
    }

    public void setIngredientCategory(ArrayList<IngredientCategory> ingredientCategory) {
        this.ingredientCategories = ingredientCategory;
    }
}
