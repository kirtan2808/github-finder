package com.githubfinder.githubfinder.entity;

import jakarta.persistence.*;

@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String searchedUser;

    public int getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSearchedUser() { return searchedUser; }
    public void setSearchedUser(String searchedUser) { this.searchedUser = searchedUser; }
}