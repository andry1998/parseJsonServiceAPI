package com.java.roomDescription.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Cameras {
    private boolean success;
    private CamerasData data;
}
