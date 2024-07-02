package org.example.DTO;

import lombok.Data;
import org.example.entity.Book;
import org.example.entity.Reader;

@Data
public class BookBorrowDTO {
    private Reader reader;
    private Book book;
}
