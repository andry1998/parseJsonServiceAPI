package com.java.roomDescription.model;

import com.java.roomDescription.model.dto.DoorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doors {
    private boolean success;
    private List<DoorDTO> data;
}
