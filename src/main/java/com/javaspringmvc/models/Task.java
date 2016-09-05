package com.javaspringmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tshiamotaukobong on 2016/09/05.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private int id;
    private String title;
    private String due_date;
    private String estimated_hours;
    private int project;
    private ProjectData project_data;

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

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getEstimated_hours() {
        return estimated_hours;
    }

    public void setEstimated_hours(String estimated_hours) {
        this.estimated_hours = estimated_hours;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public ProjectData getProject_data() {
        return project_data;
    }

    public void setProject_data(ProjectData project_data) {
        this.project_data = project_data;
    }
}
