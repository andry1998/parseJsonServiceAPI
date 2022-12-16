package com.java.roomDescription.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoorDTO {
    private Long id;
    private String name;
    private String room;
    private String snapshot;
    private boolean favorites;
}
