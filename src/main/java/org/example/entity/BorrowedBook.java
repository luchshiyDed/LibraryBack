package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedBook extends BaseEntity{
    @ManyToOne
    private Reader reader;
    @ManyToOne
    private Book book;
    private Date borrowDate;
    private Date returnDate;
}
