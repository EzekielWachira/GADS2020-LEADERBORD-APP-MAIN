package com.ezzy.gads2020leaderboard.models;

public class Project {
    private String firstName;
    private String lastName;
    private String email;
    private String githubLink;

    public Project(String firstName, String lastName, String email, String githubLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.githubLink = githubLink;
    }

    public Project(){ }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }
}
