package com.mb.basiclearningspring;

import java.time.LocalDateTime;

public record CustomerResponse(long id,
                               String firstName,
                               String lastName,
                               LocalDateTime createdAt,
                               LocalDateTime modifiedAt
                               ) {
}
