package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "cameras")
public class Camera {
    @Id
    private int id;
    private String name;
    private String snapshot;
    private String room;
    private boolean favorites;
    private boolean rec;
}
