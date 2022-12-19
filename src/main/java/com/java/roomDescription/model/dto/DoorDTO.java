package com.java.roomDescription.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoorDTO {

    Long id;

    String name;

    String room;

    String snapshot;

    Boolean favorites;

}
