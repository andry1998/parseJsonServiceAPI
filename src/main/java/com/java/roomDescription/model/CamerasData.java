package com.java.roomDescription.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CamerasData {
    private List<String> room;
    private List<Camera> cameras;
}
