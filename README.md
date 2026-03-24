# 🏥 Hospital Management System (Backend)

A backend REST API for managing hospital operations such as patients, doctors, appointments, and billing.
Built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL** following a layered architecture.

---

# 🚀 Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA (Hibernate)
* PostgreSQL
* Maven
* REST APIs
* pgAdmin

---

# 📂 Project Structure

```
HospitalManagementSystem
│
├── controllers
│   ├── AppointmentController.java
│   ├── DoctorController.java
│   ├── PatientController.java
│   └── BillController.java
│
├── service
│   ├── AppointmentService.java
│   ├── DoctorService.java
│   ├── PatientService.java
│   └── BillService.java
│
├── repository
│   ├── AppointmentRepository.java
│   ├── DoctorRepository.java
│   ├── PatientRepository.java
│   └── BillRepository.java
│
├── models
│   ├── Appointment.java
│   ├── Doctor.java
│   ├── Patient.java
│   └── Bill.java
│
└── HospitalManagementSystemApplication.java
```

---

# ⚙️ Features

### 👨‍⚕️ Doctor Management

* Create and manage doctor records

### 🧑 Patient Management

* Register and store patient information

### 📅 Appointment Booking

* Patients can book appointments with doctors

### ⛔ Double Booking Prevention

To ensure a doctor cannot be booked twice at the same time, a **database-level unique constraint** is implemented:

```
UNIQUE (doctor_id, date, time)
```

This prevents duplicate appointment slots for the same doctor.

### 📋 Doctor Schedule API

Retrieve all appointments of a doctor for a given date.

Example:

```
GET /appointments/doctor/{doctorId}/{date}
```

Example Request:

```
GET /appointments/doctor/5/2026-03-17
```

Response:

```
[
  {
    "id": 1,
    "doctorId": 5,
    "patientId": 52,
    "date": "2026-03-17",
    "time": "10:00"
  },
  {
    "id": 2,
    "doctorId": 5,
    "patientId": 60,
    "date": "2026-03-17",
    "time": "11:00"
  }
]
```

---

# 🗄 Database Schema

Main Tables:

* doctors
* patients
* appointments
* bills

Appointment table important constraint:

```
UNIQUE (doctor_id, date, time)
```

This ensures **no two appointments can exist for the same doctor at the same date and time**.

---

# ▶️ Running the Project

### 1️⃣ Clone the repository

```
git clone https://github.com/yourusername/HospitalManagementSystem.git
```

### 2️⃣ Open in IDE

Use **IntelliJ IDEA** or **VS Code**.

### 3️⃣ Configure PostgreSQL

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/hms
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

### 4️⃣ Run the application

```
mvn spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

---

# 🧪 Example API

### Create Appointment

```
POST /appointments
```

Request Body:

```
{
  "patientId": 52,
  "doctorId": 5,
  "date": "2026-03-17",
  "time": "10:00"
}
```

---

# 📌 Future Improvements

* JWT Authentication
* Role-based access (Admin / Doctor / Patient)
* Appointment cancellation
* Pagination & filtering
* Email notifications

---

# 👨‍💻 Author

Prathamesh Nistane

Java | Spring Boot | PostgreSQL
