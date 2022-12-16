package com.java.roomDescription.model;


import lombok.Data;

@Data
public class CarsWrapper<T> {

    Boolean status;

    T data;
}
