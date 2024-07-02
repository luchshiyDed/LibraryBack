package org.example.DAO;
import com.google.inject.Inject;
import org.example.entity.Book;
import org.example.entity.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class ReaderDAO extends BaseDAO<Reader>{
    public ReaderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public List<Reader> getAll(){
        List<Reader> list=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            list=session.createQuery("FROM "+Reader.class.getSimpleName(),Reader.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
