package org.example.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.model.Branch;
import org.example.model.GithubRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(baseUri = "https://api.github.com")
public interface GithubClient {

    @GET
    @Path("/users/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    List<GithubRepository> getRepositories(@PathParam("username") String username);

    @GET
    @Path("/repos/{username}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    List<Branch> getBranches(@PathParam("username") String username, @PathParam("repo") String repo);
}
