package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "name", nullable = false)
    private String name;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Camera camera;

    public Room(String name) {
        this.name = name;
    }
}
