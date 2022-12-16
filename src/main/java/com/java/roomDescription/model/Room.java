package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
