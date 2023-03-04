package com.mb.basiclearningspring.models;

import com.mb.basiclearningspring.dto.CustomerRequest;
import com.mb.basiclearningspring.dto.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static  org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonTest
public class CustomerRequestTest {

    @Autowired
    private JacksonTester<CustomerResponse> jacksonTester;


    @Test
    void shouldSerializeObject() throws IOException {
        final LocalDateTime now = LocalDateTime.now();
        CustomerResponse customerResponse = new CustomerResponse(
                UUID.randomUUID(),
                "Frank",
                "Bomkoss",
                now,
                now
                );
        JsonContent<CustomerResponse> content = jacksonTester.write(customerResponse);
        assertThat(content).hasJsonPathStringValue("$.id");
        assertThat(content).hasJsonPathStringValue("$.firstName");
      //  assertThat(content).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(content).extractingJsonPathStringValue("$.firstName").isEqualTo("Frank");
        assertThat(content).extractingJsonPathStringValue("$.createdAt").isEqualTo(now.toString());
        assertThat(content).extractingJsonPathStringValue("$.modifiedAt").isEqualTo(now.toString());

    }
}
