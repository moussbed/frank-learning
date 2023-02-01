package com.mb.basiclearningspring;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class ID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    public long getId() {
        return id;
    }
}
