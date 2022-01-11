package org.kerim.minio.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.kerim.minio.service.MinioService;

@Path("/minio")
public class MinioResource {

    @Inject
    MinioService minioService;
    
    @GET
    @Path("{id}")
    @Produces("image/jpeg")
    public Response getImage(@PathParam("id") String id){
        return Response.status(200).entity(minioService.getImage(id)).build();
    }
}
