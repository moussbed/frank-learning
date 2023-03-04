package com.mb.basiclearningspring.entities;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class ID {

    @Id
    private UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }

}
