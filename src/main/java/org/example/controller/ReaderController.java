package org.example.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import org.example.DAO.ReaderDAO;
import org.example.entity.Book;
import org.example.entity.Reader;

import jakarta.ws.rs.Path;
import org.example.util.HibernateUtil;

import java.util.List;


@Path("/api/v1/reader")
public class ReaderController extends BaseController<Reader>{
    private final ReaderDAO readerDAO;
    public ReaderController(){
        readerDAO=new ReaderDAO(HibernateUtil.getSessionFactory());
        super.setBaseDAO(readerDAO);
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Reader> getAll(){
        return readerDAO.getAll();
    }
}
