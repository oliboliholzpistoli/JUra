package com.jura.data.structures;

public class Step implements DBObject{

    private int id;
    private String description;
    private int duration;

    public Step(int id, String description, int duration) {
        this.id = id;
        this.description = description;
        this.duration = duration;
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
}
