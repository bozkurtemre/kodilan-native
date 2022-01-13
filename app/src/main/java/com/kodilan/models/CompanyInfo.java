package com.kodilan.models;

public class CompanyInfo {

    public String name;
    public String location;
    public String updatedAt;

    public CompanyInfo(String name, String location, String updatedAt) {
        this.name = name;
        this.location = location;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
