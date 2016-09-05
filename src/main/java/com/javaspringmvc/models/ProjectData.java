package com.javaspringmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tshiamotaukobong on 2016/09/05.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectData {

    private int pk;
    private String title;
    private String description;
    private String start_date;
    private Object end_date;
    private boolean is_billable;
    private boolean is_active;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public Object getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Object end_date) {
        this.end_date = end_date;
    }

    public boolean is_billable() {
        return is_billable;
    }

    public void setIs_billable(boolean is_billable) {
        this.is_billable = is_billable;
    }

    public boolean is_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
