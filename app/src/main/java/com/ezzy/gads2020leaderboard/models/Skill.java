package com.ezzy.gads2020leaderboard.models;

public class Skill {
    private String name;
    private String skill;
    private String country;
    private String badgeUrl;

    public Skill(String name, String skill, String country, String badgeUrl) {
        this.name = name;
        this.skill = skill;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public Skill() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
