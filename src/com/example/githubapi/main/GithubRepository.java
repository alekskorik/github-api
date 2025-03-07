package com.example.githubapi.main;

import java.util.List;

public class GithubRepository {
    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branches;

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
