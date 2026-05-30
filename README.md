# 🏨 Enterprise Hotel Reservation System

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Data_Structures-HashMaps-blue?style=for-the-badge" alt="Data Structures"/>
  <img src="https://img.shields.io/badge/OOP-Principles-4CAF50?style=for-the-badge" alt="OOP"/>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntelliJ IDEA"/>
</p>

Welcome to the **Hotel Reservation System**. This repository contains a robust, console-based (CLI) application developed entirely in **Core Java** for the CodeAlpha Internship (Task 4). It demonstrates a strong command over core programming concepts, data structures, and clean code architecture.

## 🚀 Projects Overview
This application simulates a real-world hotel booking environment. It allows users to view available rooms categorized by tiers (Standard, Deluxe, Suite), make secured bookings, cancel reservations, and provides an admin dashboard for overall management.

### ✨ Key Features
* **Dynamic Room Categorization:** Enums used for clean categorization of rooms (`STANDARD`, `DELUXE`, `SUITE`) with dynamic pricing.
* **Smart Booking Engine:** Automatically filters available rooms using **Java Streams API** and assigns them to guests.
* **Unique Identification:** Utilizes `java.util.UUID` to generate highly unique, alphanumeric alphanumeric booking tracking IDs.
* **Admin Dashboard:** A dedicated module that iterates through current bookings using **HashMaps** and calculates the total revenue generated.
* **Input Validation & Exception Handling:** Strict RegEx matching ensures names contain only alphabets and avoids application crashes during invalid CLI inputs.

## 🧠 Technical Architecture
The system is built with a highly decoupled architecture following Object-Oriented Principles:
1. `Room.java`: Entity class defining room attributes, status, and pricing logic.
2. `Reservation.java`: Entity class managing guest details, booking duration, and total billing.
3. `HotelReservationService.java`: The core service layer utilizing `ArrayList` for inventory management and `HashMap<String, Reservation>` for O(1) retrieval of bookings.
4. `HotelReservationApplication.java`: The main execution point featuring an interactive, loop-driven CLI menu.

## 💻 How to Run
1. Ensure you have **Java Development Kit (JDK) 22** or higher installed.
2. Clone this repository:
   ```bash
   git clone [https://github.com/AkramSE/CodeAlpha_HotelReservationSystem.git](https://github.com/AkramSE/CodeAlpha_HotelReservationSystem.git)
