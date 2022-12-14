package com.java.roomDescription.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data

public class BaseModel {
    private String name;
    private String snapshot;
    private String room;
    private boolean favorites;
}
