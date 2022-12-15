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
public class Room {
    @Id
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
//    private List<Camera> camera;

    public Room(String name) {
        this.name = name;
    }

    public Room() {

    }
}
