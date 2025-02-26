package com.jura.data.structures;

import com.jura.util.DBUtil;

import java.util.ArrayList;

public class IngredientCategory implements DBObject{

    private int id;
    private String name;

    public IngredientCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getCreateString() {
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        fields.add("ID");
        fields.add("NAME");

        values.add(Integer.toString(id));
        values.add("'"+name+"'");
        return DBUtil.generateCreateString("IngredientCategory",fields,values);
    }

    @Override
    public String getUpdateString() {
        ArrayList<String> identifierDefinitions = new ArrayList<>();
        ArrayList<String> identifierValues = new ArrayList<>();
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        identifierDefinitions.add("ID");
        identifierValues.add(Integer.toString(id));

        fields.add("NAME");
        values.add("'"+name+"'");

        return DBUtil.generateUpdateString("IngredientCategory",identifierDefinitions,identifierValues,fields,values);
    }

    @Override
    public String getDeleteString() {
        ArrayList<String> identifierDefinitions = new ArrayList<>();
        ArrayList<String> identifierValues = new ArrayList<>();

        identifierDefinitions.add("ID");
        identifierValues.add(Integer.toString(id));

        return DBUtil.generateDeleteString("IngredientCategory",identifierDefinitions,identifierValues);
    }

    @Override
    public String getSelectString() {
        return null;
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
}