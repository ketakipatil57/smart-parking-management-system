# Smart Parking Management System

A RESTful backend API built with Spring Boot and MySQL for managing parking slots in real time. Users can check slot availability and make reservations. The system updates slot status dynamically and maintains booking records.

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- MySQL 8.0
- Maven

## Project Structure

```
src/main/java/com/pict/
├── Controller/
│   ├── ParkingController.java
│   └── BookingController.java
├── Entity/
│   ├── ParkingSlot.java
│   ├── Booking.java
│   ├── SlotStatus.java
│   ├── SlotType.java
│   └── BookingStatus.java
├── Repository/
│   ├── ParkingRepository.java
│   └── BookingRepository.java
├── Service/
│   ├── ParkingService.java
│   └── BookingService.java
└── SmartParkingManagementSystemApp.java
```

## Database Setup

Create a MySQL database before running the project:

```sql
CREATE DATABASE parkingdatabase;
```

## Configuration

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parkingdatabase
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## How to Run

1. Clone the repository
2. Create the MySQL database
3. Update application.properties with your credentials
4. Run `SmartParkingManagementSystemApp.java`
5. Server starts at `http://localhost:8080`

## API Endpoints

### Parking Slots

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /Parking/parkingslot | Get all parking slots |
| GET | /Parking/parkingslot/available | Get available slots only |
| GET | /Parking/parkingslot/{id} | Get slot by ID |
| POST | /Parking/parkingslot | Add a new parking slot |
| PUT | /Parking/parkingslot/{id}/status | Update slot status |

### Bookings

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /Booking/bookslot/{slotNumber} | Book a slot by slot number |
| GET | /Booking/allBooking | Get all bookings |
| PUT | /Booking/checkout/{id} | Checkout and free the slot |
| PUT | /Booking/cancel/{id} | Cancel a booking |

## Sample Requests

Add a parking slot:
```json
POST /Parking/parkingslot
{
  "slotNumber": "A1",
  "slotType": "CAR",
  "location": "Ground Floor"
}
```

Book a slot:
```json
POST /Booking/bookslot/A1
{
  "name": "Ketaki Patil",
  "contactNumber": "9876543210"
}
```

## Features

- Real time slot availability tracking
- Slot types: CAR, BIKE, TRUCK
- Slot status: AVAILABLE, RESERVED, OCCUPIED
- Booking status: ACTIVE, COMPLETED, CANCELLED
- Auto sets booking date and in-time on reservation
- Auto sets out-time on checkout
- Slot status updates automatically on booking, checkout and cancellation
- Input validation using Jakarta Bean Validation
