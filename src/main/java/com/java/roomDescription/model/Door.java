package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "doors")
public class Door {
    @Id
    private int id;
    private String name;
    private String room;
    private String snapshot;
    private boolean favorites;

}
