package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.GithubClient;
import org.example.model.Branch;
import org.example.model.GithubRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GithubService {

    @Inject
    @RestClient
    GithubClient githubClient;

    public List<GithubRepository> getRepositories(String username) {
        List<GithubRepository> allRepos = githubClient.getRepositories(username);

        return allRepos.stream()
                .filter(repo -> !repo.isFork())
                .peek(repo -> repo.setBranches(fetchBranches(username, repo.getName())))
                .collect(Collectors.toList());
    }

    private List<Branch> fetchBranches(String username, String repoName) {
        List<Branch> branches = githubClient.getBranches(username, repoName);
        return branches.stream()
                .map(branch -> new Branch(branch.getName(), branch.getCommit().getSha()))
                .collect(Collectors.toList());
    }
}
