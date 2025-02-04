package com.formsapp.repository;

import com.formsapp.entity.Form;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class FormRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.2");

    @Autowired
    private FormRepository formRepository;

    @Test
    void testConfig() {
        assertTrue(postgreSQLContainer.isCreated());
        assertTrue(postgreSQLContainer.isRunning());
    }

    @BeforeEach
    void setUp() {
        formRepository.deleteAll();
    }

    @Test
    void addFormTest() {
        Form form1 = new Form();
        form1.setFormId("1");
        form1.setTitle("title");
        form1.setDescription("desc");
        form1.setCreatedBy("test@makeitcoder.com");
        form1.setCreatedDate(new Date());

        Form form2 = new Form();
        form2.setFormId("2");
        form2.setTitle("title");
        form2.setDescription("desc");
        form2.setCreatedBy("test@makeitcoder.com");
        form2.setCreatedDate(new Date());

        List<Form> forms = List.of(form1, form2);
        formRepository.saveAll(forms);

        List<Form> result = formRepository.findAll();
        assertEquals(result.size(), forms.size());

    }

}