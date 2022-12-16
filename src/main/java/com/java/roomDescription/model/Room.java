package com.java.roomDescription.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//@AllArgsConstructor
@Data
@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity{
    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @JsonManagedReference
    List<Camera> cameras;

    public Room(String name) {
        this.name = name;
    }

    public Room() {

    }
}
