# Forms Backend Application

FormsApp is a web-based application that allows users to create, submit, and track forms. It provides functionalities such as form creation, field definition, form submission, and form response tracking, all using RESTful APIs. The application supports user authentication, and forms can be dynamically generated and managed.

**App URL**: https://forms.makeitcoder.com/admin

## Flow
![flow](https://raw.githubusercontent.com/devanshukushwah/forms-app-backend/refs/heads/main/images/flow.svg)
## Application Screenshot
![Mockup](https://raw.githubusercontent.com/devanshukushwah/cdn/refs/heads/main/forms-app/images/mockup.jpg)

## Application Repositories
- [Form App Angular UI](https://github.com/devanshukushwah/forms-app-ui)
- [Form App Backend Application](https://github.com/devanshukushwah/forms-app-backend)
- [Form App Kafka Consumer](https://github.com/devanshukushwah/forms-app-consumer)

## Features

- Spring MVC Design pattern.
- Kafka Producer.
- Redis Cache.
- DTO Design Pattern.
- Swagger UI (API Documentation).
- Spring Aspect Oriented Programming (AOP).
- Spring Security (JWT).
- Spring JPA (Hibernate ORM).
- Database Transaction handling.
- Actuators (Production ready endpoints).
- Log4j2 logging (File & Console based).
- Junit & Mockito (Unit Testing & Mocking Bean).
- Create, Read, Update & Delete Operation (CRUD).
- Handle Multithreading.
- Global Exception Handling.
- Download CSV File (Form Responses).
- Cross Origin Configuration.
- Environment based beans handle (local, dev, qa & prod).
- Flag based beans handle (For disable kafka).
- Custom Exception.
- Custom Annotation.
- Custom Date formatting.
- Custom Id generation for Form.

## Basic Details

- **Form Management**: Create and update forms with customizable fields.
- **Form Submission**: Allows users to submit forms.
- **Form Responses**: Fetch responses for a specific form.
- **Authentication**: Secured endpoints using JWT (JSON Web Token) authentication.
- **Unique Form ID Generation**: Automatically generates unique form IDs using date-based patterns.
- **Form Field Customization**: Ability to create different types of fields (text, radio, checkbox, etc.) and configure attributes.

## Technologies Used

- **Backend**: Java 21, Spring Boot 3
- **Database**: Relational Database (Hibernate + JPA)
- **Authentication**: OAuth2, JWT (JSON Web Token)
- **API Documentation**: Swagger (or OpenAPI)
- **Testing**: JUnit, Mockito
- **Logging**: Log4J2

## Changelogs

### 2.2.0
- Redis cache

### 2.1.0
- Stable version

### 2.0.0
- Updated to `JDK 21` and `Springboot 3.4.2`

### 1.0
- Built using `JDK 8` and `Springboot 2.7.18`

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/devanshukushwah/forms-app-backend.git
   cd forms-app-backend
   ```
    
2. You can run the application locally and application will be available at http://localhost:9080/.
    
3. Ensure that your database is configured correctly in the application.properties file. You can modify it to match your database settings.
    

API Documentation
-----------------

You can access the Swagger-based API documentation after starting the application:

*   **Base URL**: http://localhost:9080/
    
*   **Swagger UI**: http://localhost:9080/swagger-ui.html

Contributing
------------

Contributions are welcome! If you find a bug or want to add a new feature, feel free to fork the repository and submit a pull request.

License
-------

This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgements
----------------

*   [Spring Boot](https://spring.io/projects/spring-boot) for building the application.
    
*   [JWT](https://jwt.io/) for user authentication.
    
*   [Hibernate](https://hibernate.org/) for ORM (Object-Relational Mapping).
