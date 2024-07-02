package org.example.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
    private Integer publishYear;


}
