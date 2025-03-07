package com.example.githubapi.main;

public class Branch {
    private String name;
    private String lastCommitSha;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }
}