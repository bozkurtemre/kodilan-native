package com.kodilan.models;

import java.util.ArrayList;

public class Data {

    public String slug;
    public String position;
    public String description;
    public String apply_url;
    public String apply_email;
    public String location;
    public int type;
    public int status;
    public int is_featured;
    public String created_at;
    public String updated_at;
    public String post_url;
    public Company company;
    public ArrayList<Tag> tags;

    public Data(String slug, String position, String description, String apply_url, String apply_email, String location, int type, int status, int is_featured, String created_at, String updated_at, String post_url, Company company, ArrayList<Tag> tags) {
        this.slug = slug;
        this.position = position;
        this.description = description;
        this.apply_url = apply_url;
        this.apply_email = apply_email;
        this.location = location;
        this.type = type;
        this.status = status;
        this.is_featured = is_featured;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.post_url = post_url;
        this.company = company;
        this.tags = tags;
    }
}
