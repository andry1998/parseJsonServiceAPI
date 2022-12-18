package com.java.roomDescription.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "cameras")
public class Camera extends LongIdEntity {

    String name;

    String snapshot;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room")
    Room room;

    Boolean favorites;

    Boolean rec;

    public Camera(Long id, String name, Room room, String snapshot, Boolean favorites, Boolean rec) {
        this.setId(id);
        this.name = name;
        this.room = room;
        this.snapshot = snapshot;
        this.favorites = favorites;
        this.rec = rec;
    }
}


