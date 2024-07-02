package org.example.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reader extends Person{
    private Date membershipJoinDate=new Date();

}
