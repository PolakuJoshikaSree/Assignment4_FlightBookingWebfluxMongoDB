# ✈️ Flight Booking Application (Reactive – Spring WebFlux + R2DBC)

A fully reactive **Flight Booking System** built using **Spring WebFlux**, **R2DBC MySQL**, and **Project Reactor**.  
This application manages airlines, flights, bookings, payments, passengers, and seats — all implemented in a fully non-blocking architecture.

---

## 📁 Included Reference Files

### 📊 API Endpoints (Downloadable Excel)
👉 [**Download API Design (Flight_API_Design_Final.xlsx)**](./Flight_API_Design_Final.xlsx)

This Excel file contains **every API endpoint** used in the project with method, path, request, response, and description.

---

## 🖼️ Swagger UI Previews

### **Swagger UI – Main View**
![Swagger UI Screenshot 1](./SwaggerUI_Image1.jpeg)

---

### **Swagger UI – Schemas View**
![Swagger UI Screenshot 2](./SwaggerUI_Image2.jpeg)

---

## 🧱 UML Diagram

### **Complete Class Diagram**
![UML Class Diagram](./UML_Flightbooking_ClassDiagram.jpeg)

---

## 🚀 Tech Stack

- **Spring WebFlux** (Reactive REST API)
- **Spring Boot 3**
- **R2DBC MySQL Driver**
- **Project Reactor (Mono/Flux)**
- **Springdoc OpenAPI for Swagger UI**
- **Lombok**
- **JUnit + Mockito for testing**

---

## 📦 Features Implemented

### ✅ Airline Management
- Add airline  
- Get airline by code  
- Get all airlines  
- Delete airline  

### ✅ Flight Management
- Add flight  
- Search flights  
- Update flight  
- Delete flight  
- Get flight by ID  

### ✅ Booking Management
- Create booking  
- Get booking by PNR  
- Cancel booking  
- Delete booking  

### ✅ Passenger Management
- Passengers automatically linked to bookings  
- Supports name, age, gender, meal preference, luggage weight  

### ✅ Payment Management
- Make payment for a booking  
- Store payment mode, amount, status, and timestamps  

### ✅ Seat Management
- Assign seats to flights  
- Track seat status (Booked / Available)

---

## 🧪 Testing
Unit tests included for:
- AirlineController  
- FlightController  
- BookingService  
- PaymentService  
- AirlineService  
- FlightService  

All tests follow reactive patterns using `Mono` and `Flux`.

---

## 📘 Swagger API Documentation

Swagger UI is enabled using **springdoc-openapi WebFlux**.

After starting the application, open:

👉 **http://localhost:8090/swagger-ui.html**

You will see all available API endpoints categorized under:
- Airlines  
- Flights  
- Bookings  
- Payments

---

## ⚙️ How to Run

### 1️⃣ Configure MySQL
```sql
CREATE DATABASE flight_booking;
