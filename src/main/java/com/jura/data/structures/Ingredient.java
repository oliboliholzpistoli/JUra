package com.jura.data.structures;

import com.jura.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingredient implements DBObject{

    private int id;
    private String name;
    private List<IngredientCategory> ingredientCategories;

    public Ingredient(int id, String name, List<IngredientCategory> ingredientCategories) {
        this.id = id;
        this.name = name;
        this.ingredientCategories = ingredientCategories;
    }

    @Override
    public String getCreateString() {
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        fields.add("ID");
        fields.add("NAME");

        values.add(Integer.toString(id));
        values.add("'"+name+"'");

        return DBUtil.generateCreateString("Ingredient",fields,values);
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

        fields.clear();
        fields.add("IngredientID");
        fields.add("IngredientCategoryID");

        for (IngredientCategory ingredientCategory:ingredientCategories){
            identifierValues.clear();
            identifierValues.add(Integer.toString(id));
            identifierValues.add(Integer.toString(ingredientCategory.getId()));
            out.append(DBUtil.generateDeleteString("Ingredient_IngredientCategory",identifierDefinitions,identifierValues));

            values.clear();
            identifierDefinitions.clear();
            identifierValues.clear();

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

    @Override
    public String getSelectString() {
        return null;
    }

    public String getCreateIngredientCategoryString(IngredientCategory ingredientCategory) {
        if(ingredientCategories.contains(ingredientCategory)) {
            ArrayList<String> fields = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            fields.add("INGREDIENTID");
            fields.add("INGREDIENTCATEGORYID");

            values.add(Integer.toString(id));
            values.add(Integer.toString(ingredientCategory.getId()));

            return DBUtil.generateCreateString("Ingredient", fields, values);
        }
        return "";
    }

    public int getId() {
        return id;
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id;
    }
}
