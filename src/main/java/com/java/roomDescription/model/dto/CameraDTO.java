package com.java.roomDescription.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CameraDTO {
    private Long id;
    private String name;
    private String snapshot;
    private String room;
    private boolean favorites;
    private boolean rec;
}
