package com.java.roomDescription.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
}


