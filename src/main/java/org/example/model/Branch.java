package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    private String name;
    private Commit commit;

    public Branch() {}

    public Branch(String name, String commitSha) {
        this.name = name;
        this.commit = new Commit(commitSha);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        private String sha;

        public Commit() {}

        public Commit(String sha) {
            this.sha = sha;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }
    }
}