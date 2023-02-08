package com.mb.basiclearningspring.entities;

import java.time.LocalDateTime;

public interface BasicEntity {

     Status getStatus();
     void setStatus(Status status);
     LocalDateTime getCreatedDate();
     LocalDateTime getModifiedDate();
}
