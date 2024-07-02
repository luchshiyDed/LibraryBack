package org.example;


import jakarta.servlet.*;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Reader;
import org.example.util.App;
import org.example.util.HibernateUtil;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URI;

public class Main  {
    public static void main(String[] args) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Author author = new Author();
            author.setName("a1");
            author.setSecondName("a1");
            Book book = new Book(author, 2002);
            book.setName("b1");
            Book book2 = new Book(author, 2003);
            book2.setName("b2");
            Reader reader = new Reader();
            reader.setName("r1");
            Reader reader2 = new Reader();
            reader2.setName("r2");
            transaction = session.beginTransaction();
            session.persist(book);
            session.persist(book2);
            session.persist(reader);
            session.persist(reader2);
            session.flush();
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        ResourceConfig config = new App();
        String baseUri = "http://localhost:8080/";

        GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), config);
    }
}

