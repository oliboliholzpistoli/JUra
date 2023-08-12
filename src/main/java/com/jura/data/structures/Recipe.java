package com.jura.data.structures;

public class Recipe implements DBObject {
    private int id;
    private String title;
    private int duration;
    private int servings;

    public Recipe(int id, String title, int duration, int servings) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.servings = servings;
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
}
