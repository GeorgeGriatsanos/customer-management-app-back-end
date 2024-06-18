# Customer Management Application

This repository contains the backend implementation of a Customer Management Application, developed using Java and Spring Boot framework. It provides RESTful API endpoints for managing users and customers.

## Features

- **User Management**
  - Register a new user
  - Login an existing user
  - Check if a username exists

- **Customer Management**
  - Add a new customer
  - Retrieve all customers for a specific user
  - Retrieve a customer by ID
  - Update customer details
  - Delete a customer

## Technologies Used

- Java 11
- Spring Boot 2.5.0
- Swagger 3.0 for API documentation
- MySQL (or your preferred database)
- Maven for dependency management

## Getting Started

To run this application locally, follow these steps:

1. **Clone the repository:**


2. **Configure Database:**

- Create a MySQL database named `customer_management_db`.
- Update the `application.properties` file in `src/main/resources` with your database connection details.

3. **Build and Run:**


The application will start on `http://localhost:8080`.

4. **Explore APIs:**

Use Swagger UI for exploring and testing the APIs:
- Open `http://localhost:8080/swagger-ui/index.html` in your browser.

## API Endpoints

### User Management

- `POST /api/user/register`: Register a new user.
- `POST /api/user/login`: Login an existing user.
- `GET /api/user/checkUsername/{username}`: Check if a username exists.

### Customer Management

- `POST /api/customer/add`: Add a new customer.
- `GET /api/customer/all?userId={userId}`: Retrieve all customers for a specific user.
- `GET /api/customer/find/{id}`: Retrieve a customer by ID.
- `PUT /api/customer/update`: Update an existing customer.
- `DELETE /api/customer/delete/{id}`: Delete a customer by ID.

## Contributing

Contributions are welcome! Here's how you can contribute:
- Fork the repository
- Create a new branch (`git checkout -b feature`)
- Make your changes
- Commit your changes (`git commit -am 'Add new feature'`)
- Push to the branch (`git push origin feature`)
- Create a new Pull Request


