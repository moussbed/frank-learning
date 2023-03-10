package com.mb.basiclearningspring;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Customer extends ID implements BasicEntity {

    private String firstName;
    private String lastName;

    @CreationTimestamp
    @JsonProperty("created_at")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @JsonProperty("modified_at")
    private LocalDateTime modifiedDate;

    private Status status = Status.CREATED;

    public Customer() {
    }


    public Customer( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
         this.status = status;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }
}
