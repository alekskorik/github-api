package org.example.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.HashMap;
import java.util.Map;

@Provider
public class ExceptionHandler implements ExceptionMapper<ClientWebApplicationException> {

    @Override
    public Response toResponse(ClientWebApplicationException exception) {
        int status = exception.getResponse().getStatus();

        if (status == 404) {
            Map<String, Object> responseJson = new HashMap<>();
            responseJson.put("status", status);
            responseJson.put("message", "User not found");

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(responseJson)
                    .build();
        }

        // Handle other errors (like rate limiting, etc.)
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("status", status);
        responseJson.put("message", "An error occurred: " + exception.getMessage());

        return Response.status(status)
                .entity(responseJson)
                .build();
    }
}
