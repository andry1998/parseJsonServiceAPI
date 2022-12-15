package com.java.roomDescription.model.dto;

import com.java.roomDescription.model.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
