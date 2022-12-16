package com.java.roomDescription.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//@AllArgsConstructor
@Data
@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity{
    @Id
    @Column(name = "name")
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public Room() {

    }
}
