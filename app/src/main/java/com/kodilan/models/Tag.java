package com.kodilan.models;

public class Tag {

    public String name;
    public String slug;

    public Tag(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
