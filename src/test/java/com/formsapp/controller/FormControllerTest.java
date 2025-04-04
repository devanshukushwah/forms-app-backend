package com.formsapp.controller;

import com.formsapp.entity.Form;
import com.formsapp.repository.FormRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FormControllerTest {

    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.2");

    @Autowired
    FormRepository formRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        formRepository.deleteAll();
    }

    @Test
    void shouldGetAllForms() {
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

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/forms")
                .then()
                .statusCode(200)
                .body("data", notNullValue());

    }

}