package com.jura.data.structures;

import com.jura.util.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe implements DBObject {
    private int id;
    private String title;
    private int duration;
    private int servings;
    private List<Step> steps;
    //Map with quantity and quantity flag as value 0 is weight in g, 1 is volume in ml
    private Map<Ingredient,int[]> ingredients;

    public Recipe(int id, String title, int duration, int servings, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.servings = servings;
        this.steps = steps;
        this.ingredients = new HashMap<>();
    }

    @Override
    public String getCreateString() {
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        fields.add("TITLE");
        fields.add("DURATION");
        fields.add("SERVINGS");

        values.add("'"+title+"'");
        values.add(Integer.toString(duration));
        values.add(Integer.toString(servings));

        return DBUtil.generateCreateString("Recipe",fields,values);
    }

    @Override
    public String getUpdateString() {
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> identifierDefinitions = new ArrayList<>();
        ArrayList<String> identifierValues = new ArrayList<>();

        fields.add("TITLE");
        fields.add("DURATION");
        fields.add("SERVINGS");

        values.add("'"+title+"'");
        values.add(Integer.toString(duration));
        values.add(Integer.toString(servings));

        identifierDefinitions.add("ID");

        identifierValues.add(Integer.toString(id));

        StringBuilder out = new StringBuilder(DBUtil.generateUpdateString("Recipe",identifierDefinitions,identifierValues,fields,values));

        identifierDefinitions.clear();

        identifierDefinitions.add("id");

        fields.clear();

        fields.add("ID");
        fields.add("DESCRIPTION");
        fields.add("DURATION");
        fields.add("RECIPEID");

        //Deleting and recreating all steps so I don't have to bother with knowing which ones exist
        for (Step step:steps){
            //adding delete strings
            identifierValues.clear();

            identifierValues.add(Integer.toString(step.getId()));

            out.append(DBUtil.generateDeleteString("Step",identifierDefinitions,identifierValues));

            //adding create strings
            values.clear();

            values.add(Integer.toString(step.getId()));
            values.add("'"+step.getDescription()+"'");
            values.add(Integer.toString(step.getDuration()));
            values.add(Integer.toString(id));

            out.append(DBUtil.generateCreateString("Step",fields,values));
        }

        identifierDefinitions.clear();

        identifierDefinitions.add("RECIPEID");
        identifierDefinitions.add("INGREDIENTID");

        fields.clear();

        fields.add("RECIPEID");
        fields.add("INGREDIENTID");
        fields.add("QUANTITY");
        fields.add("QUANTITYFLAG");

        //Deleting and recreating all ingredient relationships to not bother with checking which exist and which dont
        for(Map.Entry<Ingredient,int[]> ingredient:ingredients.entrySet()){
            //deleting ingredients
            identifierValues.clear();

            identifierValues.add(Integer.toString(id));
            identifierValues.add(Integer.toString(ingredient.getKey().getId()));

            out.append(DBUtil.generateDeleteString("Ingredient_Recipe",identifierDefinitions,identifierValues));

            //creating ingredients
            values.clear();

            values.add(Integer.toString(id));
            values.add(Integer.toString(ingredient.getKey().getId()));
            values.add(Integer.toString(ingredient.getValue()[0]));
            values.add(Integer.toString(ingredient.getValue()[1]));

            out.append(DBUtil.generateCreateString("Ingredient_Recipe",fields,values));
        }


        return out.toString();
    }

    @Override
    public String getDeleteString() {
        ArrayList<String> identifierDefinitions = new ArrayList<>();
        ArrayList<String> identifierValues = new ArrayList<>();

        identifierDefinitions.add("ID");
        identifierValues.add(Integer.toString(id));

        StringBuilder out = new StringBuilder(DBUtil.generateDeleteString("Recipe",identifierDefinitions,identifierValues));

        identifierDefinitions.clear();
        identifierValues.add("id");

        for (Step step:steps){
            identifierValues.clear();
            identifierValues.add(Integer.toString(step.getId()));

            out.append(DBUtil.generateDeleteString("Step",identifierDefinitions,identifierValues));
        }

        identifierDefinitions.clear();
        identifierDefinitions.add("RecipeId");
        identifierDefinitions.add("IngredientId");

        for (Map.Entry<Ingredient,int[]> ingredient:ingredients.entrySet()) {
            identifierValues.clear();
            identifierValues.add(Integer.toString(id));
            identifierValues.add(Integer.toString(ingredient.getKey().getId()));
            out.append(DBUtil.generateDeleteString("Ingredient_Recipe", identifierDefinitions, identifierValues));
        }
        
        return out.toString();
    }

    @Override
    public String getSelectString() {
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
