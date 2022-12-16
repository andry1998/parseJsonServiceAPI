package com.java.roomDescription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "doors")
public class Door extends LongIdEntity{
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room")
    private Room room;
    private String snapshot;
    private boolean favorites;

}
