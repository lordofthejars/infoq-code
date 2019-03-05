package com.lordofthejars.diferenciaexample.rest;

import java.util.Random;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class HelloWorldEndpoint {

    private static final Random random = new Random();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInformation() {
        final JsonObject doc = Json.createObjectBuilder()
            .add("name", "Alex")
            .build();
        return Response.ok(doc.toString())
            .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    // Producing something to get some failures as well
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUserInformation(String data) {

        System.out.println(data);
        final JsonObject doc = Json.createObjectBuilder()
            .add("name", "Alex")
            .add("favouriteNumber", random.nextInt())
            .build();
        return Response.ok(doc.toString())
            .build();
    }
}
