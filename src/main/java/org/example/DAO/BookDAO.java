package org.example.DAO;

import com.google.inject.Inject;
import org.example.entity.Book;
import org.example.entity.BorrowedBook;
import org.example.entity.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.ws.rs.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BookDAO extends BaseDAO<Book> {

    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void borrowBook(Book book, Reader reader){
        BorrowedBook borrowedBook=new BorrowedBook(reader,book,new Date(),null);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(borrowedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
    public void returnBook(Book book,Reader reader){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            BorrowedBook borrowedBook = session.createQuery("SELECT t FROM BorrowedBook t WHERE t.book.id = :bookId AND t.reader.id = :readerId", BorrowedBook.class)
                    .setParameter("bookId", book.getId())
                    .setParameter("readerId", reader.getId())
                    .getSingleResult();
            if(borrowedBook==null){
                throw new Exception("book wasn't borrowed by this reader");
            }
            borrowedBook.setReturnDate(new Date());
            transaction = session.beginTransaction();
            session.merge(borrowedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<BorrowedBook> getReport(Date date1, Date date2){
        List<BorrowedBook> list=new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
             list = session.createQuery("SELECT e FROM BorrowedBook e WHERE e.borrowDate >= :date1 AND (e.returnDate <= :date2 OR e.returnDate IS NULL)", BorrowedBook.class)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2)
                    .getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Book> getAll(){
        List<Book> list=new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            list=session.createQuery("FROM "+Book.class.getSimpleName(),Book.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}

