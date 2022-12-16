package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cameras")
public class Camera extends LongIdEntity {

    private String name;
    private String snapshot;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room")
    private Room room;
    private boolean favorites;
    private boolean rec;
}


