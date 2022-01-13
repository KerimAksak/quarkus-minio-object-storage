package org.kerim.minio.resource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Path("/getAll")
    public Response getAllBuckets(){
        return Response.status(200).entity(minioService.getAllBuckets()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteImage(@PathParam("id") String id){
        minioService.deleteImage(id);
        return Response.ok().build();
    }
}
