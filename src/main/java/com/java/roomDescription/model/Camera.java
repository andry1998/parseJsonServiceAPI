package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cameras")
public class Camera extends LongIdEntity {

    private String name;
    private String snapshot;
//    @OneToMany(mappedBy = "camera", cascade = CascadeType.ALL)
//    private List<Room> room;
    private String room;
    private boolean favorites;
    private boolean rec;
}


