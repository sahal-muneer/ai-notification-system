#  AI Notification System

##  Overview

An AI-powered backend system that dynamically determines notification priority and delivery channel using a Large Language Model (Groq API). The system processes incoming messages and intelligently decides how and when to notify users.

---

##  Key Features

*  **AI Decision Engine** (Groq LLM integration)
*  Dynamic **priority classification** (HIGH / LOW)
*  Smart **channel selection** (EMAIL / SMS / PUSH)
*  Clean architecture using **Service Layer Pattern**
*  **Strategy Pattern** for scalable delivery services
*  Notification lifecycle tracking (PENDING / SENT / FAILED)
*  H2 in-memory database (no setup required)
*  Secure configuration using environment variables

---

##  Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Groq API (LLM)
* REST APIs

---

##  Architecture

Controller → Service → Repository → Database
                                                            ↓
                                                    AI Decision Engine
                                                            ↓
                                                    Delivery Strategy (EMAIL / SMS / PUSH)

---

##  Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/ai-notification-system.git
cd ai-notification-system
```

---

### 2️⃣ Set Environment Variables

####  Windows

```bash
setx GROQ_API_KEY "5daSw9i50KYtbWeghfXEWGdyb3FYHQzgH4gOC5aD1cVACI2pr2Ne"
setx GROQ_API_URL "https://api.groq.com/openai/v1/chat/completions"
```

####  Mac/Linux

```bash
export GROQ_API_KEY="5daSw9i50KYtbWeghfXEWGdyb3FYHQzgH4gOC5aD1cVACI2pr2Ne"
export GROQ_API_URL="https://api.groq.com/openai/v1/chat/completions"
```

⚠️ Restart your IDE after setting environment variables

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

### 4️⃣ Access H2 Database

Open browser:

```
http://localhost:8080/h2-console
```

Credentials:

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: *(leave empty)*

---

## API Endpoints

### 🔹 Create Notification

POST `/notifications?userId=1&message=Your OTP is 1234`

---

### 🔹 Get User Notifications

GET `/notifications/user/{userId}`

---

### 🔹 Get Pending Notifications

GET `/notifications/pending`

---

##  Sample Flow

1. User sends message
2. AI analyzes content
3. Determines:

    * Priority (HIGH / LOW)
    * Channel (EMAIL / SMS / PUSH)
4. System routes to correct delivery service
5. Notification status updated (SENT / FAILED)

---

##  Key Concepts Used

* Dependency Injection
* Strategy Pattern
* REST API Design
* External API Integration
* Environment-based configuration
* Layered Architecture

---

##  Future Enhancements

* Retry mechanism for failed notifications
* Scheduler for delayed notifications
* Real email/SMS integration (SMTP, Twilio)
* DTO layer for clean API responses
* Logging & monitoring

---

## ‍ Author

Sahal Muneer,
Backend Developer | Spring Boot Enthusiast

---
