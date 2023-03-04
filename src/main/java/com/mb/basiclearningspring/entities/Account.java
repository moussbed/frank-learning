package com.mb.basiclearningspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends ID implements BasicEntity{

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("label")
    private String label;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToOne(mappedBy = "account")
    private Customer customer;

    @CreationTimestamp
    @JsonProperty("created_at")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty("modified_at")
    private LocalDateTime modifiedDate;

    private Status status = Status.CREATED;

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
