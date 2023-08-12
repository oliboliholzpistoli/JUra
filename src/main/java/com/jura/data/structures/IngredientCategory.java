package com.jura.data.structures;

public class IngredientCategory implements DBObject{

    private int id;
    private String name;

    public IngredientCategory(int id, String name) {
        this.id = id;
        this.name = name;
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
}