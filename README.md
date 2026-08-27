# 📋 Leave Management System

> A complete employee leave management application built with Java and Spring Boot. Handles leave requests, approvals, and team calendar views.

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![JPA](https://img.shields.io/badge/JPA_/_Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

---

## ✨ Features

- 📝 **Leave Requests** — Submit, edit, and track leave requests
- ✅ **Approval Workflow** — Manager review and approval pipeline
- 📊 **Dashboard** — Overview of team availability and leave balances
- 👥 **Role-Based Access** — Employee, Manager, and HR roles
- 📅 **Calendar View** — Visual team calendar with leave overlays

## 🚀 Quick Start

```bash
# Clone
git clone https://github.com/salahABOU03/leave-management-system.git

# Configure database in application.properties
# Run
mvn spring-boot:run
```

## 🏗️ Architecture

```
src/main/java/
├── controller/     # REST & MVC controllers
├── entity/         # JPA entities (Employee, LeaveRequest, Department)
├── repository/     # Spring Data JPA repositories
├── service/        # Business logic & approval workflow
├── dto/            # Request/Response DTOs
└── security/       # Authentication & authorization config
```

## 📄 License

© 2025 Salah Eddine Abouelkemhe
