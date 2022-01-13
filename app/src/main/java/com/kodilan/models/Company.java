package com.kodilan.models;

public class Company {

    public String name;
    public String slug;
    public String logo;
    public String www;
    public String twitter;
    public String linkedin;

    public Company(String name, String slug, String logo, String www, String twitter, String linkedin) {
        this.name = name;
        this.slug = slug;
        this.logo = logo;
        this.www = www;
        this.twitter = twitter;
        this.linkedin = linkedin;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getLogo() {
        return logo;
    }

    public String getWww() {
        return www;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }
}
