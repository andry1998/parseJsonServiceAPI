package com.java.roomDescription.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity {
    @Id
    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    //@JsonManagedReference
    List<Camera> cameras;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    //@JsonManagedReference
    List<Door> doors;

    public Room(String name) {
        this.name = name;
    }
}
