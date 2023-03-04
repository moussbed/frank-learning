package com.mb.basiclearningspring.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(UUID id,
                               String firstName,
                               String lastName,
                               LocalDateTime createdAt,
                               LocalDateTime modifiedAt,
                               UUID accountId
                               ) {
}
