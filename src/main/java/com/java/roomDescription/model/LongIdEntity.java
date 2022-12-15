package com.java.roomDescription.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
abstract class LongIdEntity extends AbstractEntity {
    @Id
    private Long id;
}