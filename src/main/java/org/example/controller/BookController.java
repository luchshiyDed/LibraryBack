package org.example.controller;

import com.google.inject.Inject;
import jakarta.ws.rs.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.DAO.BaseDAO;
import org.example.DAO.BookDAO;
import org.example.DTO.BookBorrowDTO;
import org.example.DTO.DateDTO;
import org.example.entity.Book;
import org.example.entity.BorrowedBook;
import org.example.entity.Reader;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.util.HibernateUtil;

import java.util.Date;
import java.util.List;


@Path("/api/v1/book")
public class BookController extends BaseController<Book> {
    private final BookDAO bookDAO;
    public BookController(){
        bookDAO=new BookDAO(HibernateUtil.getSessionFactory());
        super.setBaseDAO(bookDAO);
    }



    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Book> getAll(){
        return bookDAO.getAll();
    }
    @Path("/report/")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public List<BorrowedBook> getReport(DateDTO dto){
        return bookDAO.getReport(dto.getDate1(),dto.getDate2());
    }
    @Path("/borrow")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrowBook(BookBorrowDTO dto){
       bookDAO.borrowBook(dto.getBook(),dto.getReader());
       return Response.ok().build();
    }
    @Path("/return")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnBook(BookBorrowDTO dto){
        bookDAO.returnBook(dto.getBook(),dto.getReader());
        return Response.ok().build();
    }
}
