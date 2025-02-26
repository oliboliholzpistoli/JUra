package com.jura.data.structures;

import com.jura.util.DBUtil;

import java.util.ArrayList;

public class Step implements DBObject{

    private int id;
    private String description;
    //Duration in minutes (for now)
    private int duration;
    private int recipeID = -1;

    public Step(int id, String description, int duration) {
        this.id = id;
        this.description = description;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //TODO: Implement DB string creation
    @Override
    public String getCreateString() {
        if (recipeID != -1) {
            ArrayList<String> fields = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            fields.add("DESCRIPTION");
            fields.add("DURATION");
            fields.add("RECIPEID");

            values.add("'" + description + "'");
            values.add(Integer.toString(duration));
            values.add(Integer.toString(recipeID));

            return DBUtil.generateCreateString("Step", fields, values);
        }
        return "";
    }

    @Override
    public String getUpdateString() {
        return "";
    }

    @Override
    public String getDeleteString() {
        return "";
    }

    @Override
    public String getSelectString() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRecipeID() { return recipeID;}

    public void setRecipeID(int recipeID) { this.recipeID = recipeID; }
}
