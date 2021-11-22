package com.example.mad_hdridevelopers;

public class PlanModel {

    private String task, description, time, date, id;

    public PlanModel() {
    }

    public PlanModel(String task, String description, String time, String date, String id) {
        this.task = task;
        this.description = description;
        this.time = time;
        this.date = date;
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
