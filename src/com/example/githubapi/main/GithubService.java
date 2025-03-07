package com.example.githubapi.main;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public List<GitHubRepository> getRepositories(String username) {
        try {
            String url = GITHUB_API_URL + username + "/repos";
            List<Map<String, Object>> repos = restTemplate.getForObject(url, List.class);

            if (repos == null) {
                return List.of();
            }

            List<GitHubRepository> result = new ArrayList<>();

            for (Map<String, Object> repo : repos) {
                if (!(Boolean) repo.get("fork")) { // Filter out forked repos
                    GitHubRepository githubRepo = new GitHubRepository();
                    githubRepo.setRepositoryName((String) repo.get("name"));
                    githubRepo.setOwnerLogin((String) ((Map<?, ?>) repo.get("owner")).get("login"));
                    githubRepo.setBranches(getBranches(username, githubRepo.getRepositoryName()));
                    result.add(githubRepo);
                }
            }
            return result;

        } catch (HttpClientErrorException.NotFound e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    private List<Branch> getBranches(String owner, String repoName) {
        String url = String.format("https://api.github.com/repos/%s/%s/branches", owner, repoName);
        List<Map<String, Object>> branches = restTemplate.getForObject(url, List.class);

        if (branches == null) return List.of();

        List<Branch> result = new ArrayList<>();
        for (Map<String, Object> branch : branches) {
            Branch b = new Branch();
            b.setName((String) branch.get("name"));
            b.setLastCommitSha((String) ((Map<?, ?>) branch.get("commit")).get("sha"));
            result.add(b);
        }
        return result;
    }
}