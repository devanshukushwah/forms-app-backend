# Forms App

FormsApp is a web-based application that allows users to create, submit, and track forms. It provides functionalities such as form creation, field definition, form submission, and form response tracking, all using RESTful APIs. The application supports user authentication, and forms can be dynamically generated and managed.

## Features

- **Form Management**: Create and update forms with customizable fields.
- **Form Submission**: Allows users to submit forms.
- **Form Responses**: Fetch responses for a specific form.
- **Authentication**: Secured endpoints using JWT (JSON Web Token) authentication.
- **Unique Form ID Generation**: Automatically generates unique form IDs using date-based patterns.
- **Form Field Customization**: Ability to create different types of fields (text, radio, checkbox, etc.) and configure attributes.

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: Relational Database (Hibernate + JPA)
- **Authentication**: OAuth2, JWT (JSON Web Token)
- **API Documentation**: Swagger (or OpenAPI)
- **Testing**: JUnit, Mockito
- **Logging**: SLF4J with Logback

## Flow
![flow](images/flow.svg)

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/devanshukushwah/forms-app.git
   cd forms-app
   ```

2.  Make sure you have Java 8+
    
3.  You can run the application locally and application will be available at http://localhost:9080/.
    
4.  Ensure that your database is configured correctly in the application.properties file. You can modify it to match your database settings.
    

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
