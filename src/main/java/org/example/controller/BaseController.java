package org.example.controller;

import jakarta.ws.rs.*;
import lombok.NoArgsConstructor;
import org.example.DAO.BaseDAO;
import org.example.entity.BaseEntity;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@NoArgsConstructor
public class BaseController<T extends BaseEntity>{
    BaseDAO<T> baseDAO;
    public void setBaseDAO(BaseDAO<T> baseDAO){
        this.baseDAO=baseDAO;
    }
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(T t){
        baseDAO.update(t);
        return Response.ok().build();
    }
    @Path("/delete")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(T t){
        baseDAO.delete(t);
        return Response.ok().build();
    }
    @Path("/create")
    @POST
    public Response create(T t){
        baseDAO.create(t);
        return Response.ok().build();
    }
}
