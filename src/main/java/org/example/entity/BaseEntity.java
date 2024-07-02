package org.example.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
     Long id;
     String name;
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())){
            return Objects.equals(((Book) obj).getId(), this.getId());
        }
        return false;
    }
}
