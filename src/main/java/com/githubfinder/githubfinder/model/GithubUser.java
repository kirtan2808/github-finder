package com.githubfinder.githubfinder.model;

public class GithubUser {

    private String name;
    private String login;
    private String bio;
    private String avatar_url;
    private int public_repos;

    // getters setters (IMPORTANT)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getAvatar_url() { return avatar_url; }
    public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url; }

    public int getPublic_repos() { return public_repos; }
    public void setPublic_repos(int public_repos) { this.public_repos = public_repos; }
}