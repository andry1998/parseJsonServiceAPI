package com.java.roomDescription.model.dto;

import lombok.Data;

@Data
public class CarsWrapper<T> {

    Boolean status;

    T data;
}
