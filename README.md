# Project Overview

## Project Introduction: Backend Replica of MakeMyTrip using Spring Boot Microservices Architecture

The objective of this project is to develop a backend replica of the MakeMyTrip application using a microservices architecture with Spring Boot. MakeMyTrip is a leading online travel company that offers a comprehensive range of travel services, including flight bookings, hotel reservations, holiday packages, and more. The backend of this application needs to handle a vast amount of data, ensure high availability, and provide seamless service to users.

To achieve this, the project will be designed using microservices, a modern architectural approach that divides the application into small, independent services that communicate over well-defined APIs. Each microservice will focus on a specific business capability, such as user management, booking services, payment processing, and search functionality. This approach not only enhances scalability and flexibility but also simplifies development, testing, and deployment.

Spring Boot will be utilized as the primary framework due to its support for microservices development, ease of integration with various components, and robust ecosystem. The project will also incorporate other essential technologies and tools such as Spring Cloud for service discovery and configuration management, Apache Kafka for event-driven communication, and JUnit for thorough testing.

Overall, this project aims to create a scalable, resilient, and maintainable backend system that replicates the core functionalities of the MakeMyTrip application, providing a solid foundation for future enhancements and innovations.


## How to Run the Project

To run the backend replica of the MakeMyTrip application, follow these steps:

### Prerequisites

Ensure you have the following installed on your system:

- **Java Development Kit (JDK) 11 or higher**
- **Apache Maven**
- **Docker (optional for running services in containers)**
- **Apache Kafka**
- **PostgresSQL or any preferred database**
- **Cassandra**
- **Redis**


### Step-by-Step Guide

1. **Clone the Repository**

   ```bash
   git clone https://github.com/osamahossiny/Scalabe.git
   ```

2. **Set Up the Configuration Server**

   - Navigate to the configuration server directory:

     ```bash
     cd config-server
     ```

   - Build and run the configuration server:

     ```bash
     mvn clean install
     mvn spring-boot:run
     ```

3. **Set Up Kafka**

   - Make sure Kafka is running. If using Docker, you can start Kafka with:

     ```bash
     docker-compose up -d
     ```

   - Ensure that the Kafka topics required by the services are created. You can use Kafka's command-line tools or a Kafka management interface to do this.

4. **Run Each Microservice**

   Navigate to each microservice directory and run the following commands:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Repeat this process for each of the following services:

   - **User Service**
   - **Booking Service**
   - **Config Service**
   - **Search Service**
   - **Admin Service**
   - **API gateway Service**

5. **Database Setup**

   - Ensure your database is running. Start the PostgreSQL service.

6. **Running Services in Docker (Optional)**

   If you prefer to run the services in Docker containers, you can use the provided Dockerfile in each service directory:

   ```bash
   docker build -t service-name .
   docker run -p port:port service-name
   ```

   Replace `service-name` with the name of the microservice and `port` with the appropriate port number.

7. **Accessing the Application**

   Once all services are up and running, you can access the application via the endpoints defined in each microservice. For example, the user service might be accessible at:

   ```bash
   http://localhost:8080/api/user
   ```

   Refer to the API documentation for detailed information on available endpoints and their usage.

### Troubleshooting

- **Configuration Issues**: Ensure the configuration server is running and properly configured.
- **Kafka Issues**: Check if Kafka is running and that all necessary topics are created.
- **Database Issues**: Verify the database connection settings and that the required tables are present.

By following these steps, you should be able to successfully run the backend replica of the MakeMyTrip application. If you encounter any issues, please refer to the project's issue tracker or documentation for further assistance.


# Flight Search Service

## Overview
This service provides endpoints for searching and booking flights. The following functionalities are included:

1. One-Way Flights
2. Two-Way Flights
3. Round Trips
4. Filtered Search

## Endpoints

### 1. One Way
- **URL:** `http://localhost:8082/api/v1/flight/OneWay`
- **Method:** POST
- **Description:** Get direct flights by specifying the departure location, arrival location, and departure date.
- **Request Example:**
  ```json
  {
    "from": "Egypt",
    "depDate": "2024-05-24",
    "to": "Germany"
  }
  ```
- **Response Example:**
  ```json
  [
    {
      "id": 3,
      "distance": 500.4,
      "extraBaggagePrice": 500.0,
      "departureLocation": "Egypt",
      "flightId": 3,
      "plane": {
        "id": 4,
        "name": "Boeing77746",
        "airline": {
          "id": 4,
          "name": "Dawly",
          "customerServiceNumber": "01000000000",
          "iban": "456981684"
        },
        "type": "Airbus",
        "hibernateLazyInitializer": {}
      },
      "depDate": "2024-05-24",
      "flightPrice": 10000.0,
      "depAirport": "CAI",
      "arrivalAirPort": "BER",
      "insurancePrice": 1000.0,
      "timeOfDep": "18:38:02.113786500",
      "timeOfArrival": "18:38:02.113786500",
      "arrivalLocation": "Germany",
      "arrivalDate": "2024-05-24"
    }
  ]
  ```

### 2. Two Way
- **URL:** `http://localhost:8082/api/v1/flight/TwoWay`
- **Method:** POST
- **Description:** Get flights with layovers where the first flight departs from the specified location and the second flight arrives at the destination.
- **Request Example:**
  ```json
  {
    "from": "Spain",
    "depDate": "2024-05-23",
    "to": "US"
  }
  ```
- **Response Example:**
  ```json
  [
    [
      {
        "id": 5,
        "distance": 500.4,
        "extraBaggagePrice": 10000.0,
        "departureLocation": "Spain",
        "flightId": 5,
        "plane": null,
        "depDate": "2024-05-23",
        "flightPrice": 500.4,
        "depAirport": "BAR",
        "arrivalAirPort": "LDN",
        "insurancePrice": 10000.0,
        "timeOfDep": "12:48:40",
        "timeOfArrival": "16:41:00",
        "arrivalLocation": "UK",
        "arrivalDate": "2024-05-23"
      },
      {
        "id": 6,
        "distance": 500.4,
        "extraBaggagePrice": 10000.0,
        "departureLocation": "UK",
        "flightId": 6,
        "plane": null,
        "depDate": "2024-05-24",
        "flightPrice": 500.4,
        "depAirport": "LDN",
        "arrivalAirPort": "FLR",
        "insurancePrice": 10000.0,
        "timeOfDep": "00:48:40",
        "timeOfArrival": "12:41:00",
        "arrivalLocation": "US",
        "arrivalDate": "2024-05-24"
      }
    ]
  ]
  ```

### 3. Round Trip
- **URL:** `http://localhost:8082/api/v1/flight/RoundTrip`
- **Method:** POST
- **Description:** Get round trip flights by specifying the departure location, arrival location, departure date, and return date.
- **Request Example:**
  ```json
  {
    "from": "UK",
    "depDate": "2024-05-24",
    "to": "US",
    "returnDate": "2024-05-29"
  }
  ```
- **Response Example:**
  ```json
  [
    [
      {
        "id": 6,
        "distance": 500.4,
        "extraBaggagePrice": 10000.0,
        "departureLocation": "UK",
        "flightId": 6,
        "plane": null,
        "depDate": "2024-05-24",
        "flightPrice": 500.4,
        "depAirport": "LDN",
        "arrivalAirPort": "FLR",
        "insurancePrice": 10000.0,
        "timeOfDep": "00:48:40",
        "timeOfArrival": "12:41:00",
        "arrivalLocation": "US",
        "arrivalDate": "2024-05-24"
      },
      {
        "id": 9,
        "distance": 500.4,
        "extraBaggagePrice": 10000.0,
        "departureLocation": "US",
        "flightId": 9,
        "plane": null,
        "depDate": "2024-05-29",
        "flightPrice": 500.4,
        "depAirport": "LDN",
        "arrivalAirPort": "FLR",
        "insurancePrice": 10000.0,
        "timeOfDep": "18:48:40",
        "timeOfArrival": "23:41:00",
        "arrivalLocation": "UK",
        "arrivalDate": "2024-05-29"
      }
    ]
  ]
  ```

### 4. Filtered Search
- **URL:** `http://localhost:8082/api/v1/flight/Filtered`
- **Method:** POST
- **Description:** Get flights according to specified filters such as departure destination, arrival destination, departure and arrival airports, flight duration, maximum price, and departure date.
- **Request Example:**
  ```json
  {
    "from": "Egypt",
    "depDate": "2024-05-23",
    "to": "Germany",
    "price": "10000000",
    "arrAirport": "BER",
    "depAirport": "CAI",
    "duration": "13940"
  }
  ```
- **Response Example:**
  ```json
  [
    {
      "id": 10,
      "distance": 500.4,
      "extraBaggagePrice": 10000.0,
      "departureLocation": "Egypt",
      "flightId": 10,
      "plane": null,
      "depDate": "2024-05-23",
      "flightPrice": 500.4,
      "depAirport": "CAI",
      "arrivalAirPort": "BER",
      "insurancePrice": 10000.0,
      "timeOfDep": "22:48:40",
      "timeOfArrival": "02:41:00",
      "arrivalLocation": "Germany",
      "arrivalDate": "2024-05-24"
    }
  ]
  ```

## Testing
### One-Way Flights
- **URL:** `http://localhost:8082/api/v1/flight/OneWay`
- **Request Example:**
  ```json
  {
    "from": "Egypt",
    "depDate": "2024-05-24",
    "to": "Germany"
  }
  ```

### Two-Way Flights
- **URL:** `http://localhost:8082/api/v1/flight/TwoWay`
- **Request Example:**
  ```json
  {
    "from": "Spain",
    "depDate": "2024-05-23",
    "to": "US"
  }
  ```

### Round Trip
- **URL:** `http://localhost:8082/api/v1/flight/RoundTrip`
- **Request Example:**
  ```json
  {
    "from": "UK",
    "depDate": "2024-05-24",
    "to": "US",
    "returnDate": "2024-05-29"
  }
  ```

### Filtered Search
- **URL:** `http://localhost:8082/api/v1/flight/Filtered`
- **Request Example:**
  ```json
  {
    "from": "Egypt",
    "depDate": "2024-05-23",
    "to": "Germany",
    "price": "10000000",
    "arrAirport": "BER",
    "depAirport": "CAI",
    "duration": "13940"
  }
  ```
Here is the styled README file with all the API endpoints regarding the transaction service extracted from the provided documents:

```markdown
# Transaction Service API Documentation

## Overview
This documentation provides an overview of the API endpoints for the transaction service. It includes endpoints for user registration, plane seat management, flight reservations, and transaction handling.

## Endpoints

### 1. User Registration
- **URL:** `http://localhost:8080/api/v1/auth/register`
- **Method:** POST
- **Description:** Register a new user.
- **Request Example:**
  ```json
  {
    "userName": "John_doe",
    "email": "John.doe@gmail.com",
    "password": "John123",
    "birthDay": "1990-01-01",
    "gender": "Male",
    "maritalStatus": "Single",
    "pinCode": "12345",
    "residence": "Example Residence",
    "mobileNumber": "1234567890",
    "firstName": "John",
    "lastName": "Doe",
    "role": "USER"
  }
  ```

### 2. Plane Seat Management
- **URL:** `http://localhost:8081/api/v1/planeSeat`
- **Method:** POST
- **Description:** Add a new plane seat.
- **Request Example:**
  ```json
  {
    "id": 5,
    "seatNumber": 1,
    "seatCategory": "FirstClassWindow",
    "plane": {
      "id": 1
    },
    "price": 150
  }
  ```

### 3. Flight Reservation
- **URL:** `http://localhost:8081/api/v1/flightReservation`
- **Method:** POST
- **Description:** Add a new flight reservation.
- **Request Example:**
  ```json
  {
    "user": {
      "id": 1
    },
    "flightPackage": {
      "id": 1
    },
    "planeSeat": {
      "id": 6
    },
    "seatChargeable": true,
    "extraBaggage": false,
    "withInsurance": true,
    "paymentMethod": "VISA"
  }
  ```

- **URL:** `http://localhost:8081/api/v1/flightReservation/1?user_id=1&package_id=1&seat_id=9&seatChargeable=true&extraBaggage=true&withInsurance=true&paymentMethod=VISA`
- **Method:** PUT
- **Description:** Update an existing flight reservation.
- **Request Example:**
  ```json
  {
    "user_id": 1,
    "package_id": 1,
    "seat_id": 9,
    "seatChargeable": true,
    "extraBaggage": true,
    "withInsurance": true,
    "paymentMethod": "VISA"
  }
  ```

- **URL:** `http://localhost:8081/api/v1/flightReservation/user`
- **Method:** GET
- **Description:** Get all reservations for a specific user.

- **URL:** `http://localhost:8081/api/v1/flightReservation/1`
- **Method:** DELETE
- **Description:** Delete a specific flight reservation.

### 4. Transaction Handling
- **URL:** `http://localhost:8081/api/v1/transaction`
- **Method:** GET
- **Description:** Get all transactions.

- **URL:** `http://localhost:8081/api/v1/transaction/user`
- **Method:** GET
- **Description:** Get transactions for a specific user.

- **URL:** `http://localhost:8081/api/v1/transaction`
- **Method:** POST
- **Description:** Add a new transaction.
- **Request Example:**
  ```json
  {
    "transactionId": 3,
    "userId": 1,
    "reservationId": 1,
    "transactionDateTime": "2024-01-11T00:00:00",
    "paymentMethod": "VISA",
    "transactionAmount": 50000.00,
    "status": "PENDING"
  }
  ```

- **URL:** `http://localhost:8081/api/v1/transaction/1`
- **Method:** PUT
- **Description:** Update a specific transaction.
- **Request Example:**
  ```json
  {
    "transactionId": 1,
    "userId": 1,
    "reservationId": 1,
    "transactionDateTime": "2024-01-11T00:00:00",
    "paymentMethod": "VISA",
    "transactionAmount": 50000.00,
    "status": "PENDING"
  }
  ```

- **URL:** `http://localhost:8081/api/v1/transaction/1`
- **Method:** DELETE
- **Description:** Delete a specific transaction.

Here is the README file with all the API endpoints extracted from the provided documents in GitHub format:

```markdown
# API Documentation

## Overview
This documentation provides an overview of the API endpoints for various services, including user authentication, profile management, plane seat management, flight reservations, and transaction handling.

## User Service Endpoints

### 1. Register
- **URL:** `http://localhost:8080/api/user/auth/register`
- **Method:** POST
- **Description:** Register a new user.
- **Request Example:**
  ```json
  {
    "firstname": "osama",
    "lastname": "hossiny",
    "email": "osamahossinyali@gmail.com",
    "password": "123",
    "role": "USER",
    "birthDay": "1999-08-01",
    "gender": "MALE",
    "maritalStatus": "MARRIED",
    "pinCode": "1234",
    "residence": "Egypt/Cairo City/Alrehab",
    "mobileNumber": "01098765432"
  }
  ```
- **Response Example:**
  ```json
  {
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvc2FtYWhvc3NpbnlhbGlAZ21haWwuY29tIiwiaWF0IjoxNzE2Nzc4NDc0LCJleHAiOjE3MTY4NjQ4NzR9.cEsErO-bOJ4LT-80f-k5djFuc8GdOS_B6NIXOdnNMFI",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvc2FtYWhvc3NpbnlhbGlAZ21haWwuY29tIiwiaWF0IjoxNzE2Nzc4NDc0LCJleHAiOjE3MTY4NjQ4NzR9.cEsErO-bOJ4LT-80f-k5djFuc8GdOS_B6NIXOdnNMFI"
  }
  ```

### 2. Authenticate
- **URL:** `http://localhost:8084/api/user/auth/authenticate`
- **Method:** POST
- **Description:** Authenticate a user.
- **Request Example:**
  ```json
  {
    "email": "wael.abouelsaadat@guc.edu.eg",
    "password": "123"
  }
  ```
- **Response Example:**
  ```json
  {
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3YWVsLmFib3VlbHNhYWRhdEBndWMuZWR1LmVnIiwiaWF0IjoxNzE2NzkzOTUwLCJleHAiOjE3MTY4ODAzNTB9.8OuKwIACBkaSHaISf1VUWFB_ZwfwWl1CSiZxTFjey10",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3YWVsLmFib3VlbHNhYWRhdEBndWMuZWR1LmVnIiwiaWF0IjoxNzE2NzkzOTUxLCJleHAiOjE3MTY4ODAzNTF9.AMo9WYDubL91j-oBTgXeQ4KcIEg4L_lkukSabs8J3cY"
  }
  ```

### 3. Change Password
- **URL:** `http://localhost:8080/api/user/profile/change-password`
- **Method:** POST
- **Description:** Change user password.
- **Request Example:**
  ```json
  {
    "currentPassword": "123",
    "newPassword": "12345",
    "confirmationPassword": "12345"
  }
  ```

### 4. Logout
- **URL:** `http://localhost:8084/api/user/auth/logout`
- **Method:** POST
- **Description:** Logout a user.

### 5. Get User Profile
- **URL:** `http://localhost:8084/api/user/profile`
- **Method:** GET
- **Description:** Get user profile details.
- **Response Example:**
  ```json
  {
    "firstname": "Wael",
    "lastname": "Abouelsaadat",
    "email": "wael.abouelsaadat@guc.edu.eg",
    "birthDay": "1970-08-19",
    "gender": "MALE",
    "maritalStatus": "MARRIED",
    "pinCode": "1234",
    "residence": "Egypt/Cairo City/GUC",
    "mobileNumber": "01098765432"
  }
  ```

### 6. Edit User Profile
- **URL:** `http://localhost:8084/api/user/profile/edit-profile`
- **Method:** POST
- **Description:** Edit user profile details.
- **Request Example:**
  ```json
  {
    "firstname": "osama",
    "lastname": "hossiny",
    "email": "osamahossinyali@gmail.com",
    "password": "123",
    "birthDay": "1999-08-01",
    "gender": "FEMALE",
    "maritalStatus": "DIVORCED",
    "pinCode": "1234",
    "residence": "Egypt/Cairo City/Alrehab",
    "mobileNumber": "01098765432"
  }
  ```
