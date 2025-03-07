package org.example.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import java.util.List;

import org.example.model.GithubRepository;
import org.example.service.GithubService;

@Path("/api/github")
public class GithubResource {

    @Inject
    GithubService githubService;

    @GET
    @Path("/repos/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRepositories(@PathParam("username") String username) {
        List<GithubRepository> repositories = githubService.getRepositories(username);
        return Response.ok(repositories).build();
    }
}

