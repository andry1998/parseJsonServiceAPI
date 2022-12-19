package com.java.roomDescription.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CameraDTO {

    private Long id;

    private String name;

    private String snapshot;

    private String room;

    private Boolean favorites;

    private Boolean rec;

}
