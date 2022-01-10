package com.devdroid.easybiz.data_model;

import com.squareup.picasso.Picasso;

public class Employee {

    private String name;
    private int lateMarks;
    private String image_url;
    private boolean dueSalary;
    private int salaryCurDueToLateMarks;

    public Employee(String name, int lateMarks, String image_url, boolean dueSalary, int salaryCurDueToLateMarks) {
        this.name = name;
        this.lateMarks = lateMarks;
        this.image_url = image_url;
        this.dueSalary = dueSalary;
        this.salaryCurDueToLateMarks = salaryCurDueToLateMarks;
    }

    public String getName() {

        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public int getLateMarks() {
        return lateMarks;
    }

    public Employee setLateMarks(int lateMarks) {
        this.lateMarks = lateMarks;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public Employee setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    public boolean isDueSalary() {
        return dueSalary;
    }

    public Employee setDueSalary(boolean dueSalary) {
        this.dueSalary = dueSalary;
        return this;
    }

    public int getSalaryCurDueToLateMarks() {
        return salaryCurDueToLateMarks;
    }

    public Employee setSalaryCurDueToLateMarks(int salaryCurDueToLateMarks) {
        this.salaryCurDueToLateMarks = salaryCurDueToLateMarks;
        return this;
    }
}
