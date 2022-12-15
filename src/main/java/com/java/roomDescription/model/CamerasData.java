package com.java.roomDescription.model;

import com.java.roomDescription.model.dto.CameraDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CamerasData {
    private List<String> room;
    private List<CameraDTO> cameras;
}
