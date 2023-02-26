package com.mb.basiclearningspring.repositories;

import com.mb.basiclearningspring.entities.Customer;
import jakarta.transaction.Transactional;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;



import static  org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = {CustomerRepositoryTest.Initializer.class})
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @ClassRule
    public static final PostgreSQLContainer<?> postgres =  new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("mydatabase")
            .withUsername("sa")
            .withPassword("sa")
            .waitingFor(Wait.defaultWaitStrategy());

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            postgres.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + String.format("jdbc:tc:postgresql://localhost:%s/%s", postgres.getFirstMappedPort(), postgres.getDatabaseName()),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword()

            ).applyTo(applicationContext.getEnvironment());
        }
    }

//    @BeforeEach
//    void tearDown(){
//        customerRepository.deleteAll();
//    }


    @Test
    @Transactional
    public void itShouldCheckWhenCustomerEmailExists(){
        // Given
        Customer customer = new Customer("Frank", "Bomkoss");
        final String email = "mb@spring.io";
        customer.setEmail(email);
        customerRepository.save(customer);
        // When
        Boolean existsEmail = customerRepository.selectExistsEmail(email);
        // Then
        assertThat(existsEmail).isTrue();
    }

    @Test
   // @Order(1)
    @Transactional
    public void itShouldCheckWhenCustomerEmailDoesNotExists(){
        // Given
        final String email = "mb@spring.io";
        // When
        Boolean existsEmail = customerRepository.selectExistsEmail(email);
        // Then
        assertThat(existsEmail).isFalse();
    }
}
