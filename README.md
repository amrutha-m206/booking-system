# AutoAssess 

## Overview

AutoAssess is a full-stack assessment platform that automatically generates quizzes from uploaded documents, evaluates user responses, tracks performance over time, and provides analytical insights.

The system combines document processing, AI-powered quiz generation, event-driven communication, authentication, analytics and notification system into a unified learning platform.

Users can:

- Register and securely log in
- Upload study documents
- Automatically generate quizzes from document content
- Attempt assessments
- Receive instant scoring and detailed feedback
- View assessment history
- Track performance analytics
- Receive system notifications

---

## Key Features

### Authentication & Authorization

- JWT-based authentication
- Protected routes on both frontend and backend
- Secure access to user-specific resources
- Stateless authentication model
- User identity retrieved through Spring Security Context

### Document Management

- Document upload support
- Storage of extracted document content
- User-document ownership validation
- Secure document access controls

### AI Quiz Generation

- Automatic quiz creation from uploaded documents
- Integration with Large Language Models (LLMs)
- Structured MCQ generation
- JSON-based quiz representation

### Assessment Engine

- Real-time quiz evaluation
- Automatic score calculation
- Correct and incorrect answer tracking
- Detailed question-level review

### Assessment History

- Historical attempt tracking
- Previous score visibility
- Performance progression review

### Analytics Dashboard

- Total attempts
- Average score
- Best score
- Worst score
- Performance visualization

### Notification System

- Event-driven notification creation
- User-specific notification tracking
- Assessment completion notifications

---

## Technology Stack

### Frontend
- React.js
- CSS

### Backend
- Spring Boot
- Hibernate
- JWT Authentication

### AI model
- Llama-3.3-70b-versatile"

### Messaging
- Apache Kafka

### Database
- MySQL

---

## Kafka & Event-Driven Architecture

This project uses Apache Kafka to implement an event-driven architecture, allowing different modules to communicate asynchronously while remaining loosely coupled.

Instead of directly invoking other services, producers publish events to Kafka topics, and consumers process those events independently. This improves scalability, maintainability, and extensibility.

### Topics Used

#### 1. document-upload topic

Published when a user uploads a document.

**Producer:**

* Document Service

**Consumer:**

* Quiz Service

The Quiz Service consumes the uploaded document event, extracts the document information, and automatically generates a quiz using the AI integration.

---

#### 2. score-published topic

Published after a quiz is submitted and the assessment score is calculated.

**Producer:**

* Assessment Service

**Consumers:**

* Analytics Service
* Notification Service

The Analytics consumer updates user performance metrics such as total attempts, average score, best score, and worst score. These calculations are performed asynchronously after the score event is published, rather than during quiz submission. This reduces request latency and allows users to receive assessment results immediately without waiting for analytics processing.

The Notification consumer creates user notifications related to assessment completion and scoring events. Notifications are also generated asynchronously through Kafka consumers, ensuring that notification creation does not delay the assessment submission workflow. Once the event is published, both Analytics and Notification services automatically process it independently behind.


---

### Why Kafka?

Kafka was introduced to avoid tight coupling between services.

Benefits include:

* Loose coupling between modules
* Asynchronous communication
* Easier future expansion
* Better maintainability
* Independent service evolution

For example, additional consumers such as Leaderboard, Email, or Achievement services can be added later without modifying the Assessment or Document modules.

This design ensures that core business services remain focused on their responsibilities while Kafka handles event distribution across the system.

---

### Why JWT Authentication?

Traditional session-based authentication stores session data on the server.

JWT was chosen because:

- Stateless authentication
- Better scalability
- No server-side session storage
- Easy frontend integration
- Suitable for REST APIs

#### Authentication Flow

1. User logs in successfully.
2. Backend generates a JWT token.
3. JWT is returned to the frontend.
4. Frontend stores the token.
5. Every request includes the token.
6. Backend validates the token before processing the request.

---

### Why Spring Security Context?

Spring Security Context stores the currently authenticated user.

#### Benefits

- No need to pass user identifiers in every request
- Prevents client-side impersonation
- Simplifies authorization checks

#### Example

Instead of:

```http
GET /api?userId=5
```

the application retrieves the logged-in user directly from the Security Context.

This prevents users from accessing another user's data by modifying request parameters.

---

### Why DTOs?

DTOs (Data Transfer Objects) separate API contracts from database entities.

#### Benefits

- Encapsulation
- Cleaner API design
- Reduced coupling
- Easier future modifications
- Better maintainability
