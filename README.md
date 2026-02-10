# Customer Support Ticket Tracker (MVP)

## Overview

This project is a minimal full-stack **Customer Support Ticket Tracker** built as part of a FullStack engineering case study.

The objective of this MVP is to provide a simple internal tool for support teams to:

- Create customer support tickets
- View all tickets in one place
- Update ticket status as work progresses

The focus of this implementation is on **clean architecture, maintainability, and practical engineering decisions** rather than feature completeness or heavy UI polish.

---

## Tech Stack

### Frontend

- React (Vite)
- Tailwind CSS
- Axios
- Responsive UI with reusable components

### Backend

- Java 25
- Spring Boot
- REST APIs
- In-memory data storage (ConcurrentHashMap)
- Maven

---

## Architecture & Design Approach

The project follows a clean layered architecture to ensure maintainability and scalability.

### Backend Architecture

- **Controller Layer** → Handles HTTP requests and responses
- **Service Layer** → Contains business logic and validations
- **Repository Layer** → Handles in-memory data storage
- **DTO Layer** → Separates request/response from internal models
- **Global Exception Handling** → Centralized error handling

This layered design ensures separation of concerns and makes it easy to scale the application in future.

### Frontend Architecture

- Component-based modular structure
- Reusable UI components
- Centralized API service layer
- Clean state management using React hooks
- Responsive layout using Tailwind CSS

---

## Core Features Implemented

### 1. Create Support Ticket

Users can create a new support ticket with:

- Customer Name
- Customer Email
- Subject
- Description
- Priority (Low / Medium / High)

System automatically:

- Generates a unique ticket ID
- Captures creation timestamp
- Sets default status to **OPEN**

---

### 2. View All Tickets

Displays all tickets in a clean and responsive list/table view showing:

- Ticket ID
- Subject
- Priority
- Status
- Creation Date

---

### 3. Update Ticket Status

Tickets can move through the following states:

```
OPEN → IN_PROGRESS → RESOLVED
```

Status transitions are validated in backend and reflected instantly in UI.

---

## Data Storage Decision

This MVP uses **in-memory storage** instead of a database.

**Reason:**
Since the exercise is scoped to a 2–3 hour MVP, using an in-memory data layer allows focus on:

- API design
- Architecture
- Functionality
- Code quality

In a production environment, this can be easily replaced with:

- PostgreSQL
- MySQL
- MongoDB

without major structural changes.

---

## Running the Project Locally

### Backend (Spring Boot - Java 25)

Navigate to backend folder:

```
cd backend
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

### Frontend (React + Vite)

Navigate to frontend folder:

```
cd frontend
npm install
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

---

## API Endpoints

### Create Ticket

```
POST /api/tickets
```

### Get All Tickets

```
GET /api/tickets
```

### Update Ticket Status

```
PATCH /api/tickets/{id}
```

---

## Trade-offs & Engineering Decisions

Given the MVP scope and time constraints (2–3 hours):

- Used in-memory storage instead of database
- Focused only on core ticket workflow
- Avoided authentication and advanced features
- Kept UI simple but clean and responsive
- Prioritized clean architecture and readability

---

## AI & Productivity Tools Usage

Tools used:

- ChatGPT / AI assistant for architectural planning and structuring
- GitHub Copilot / AI tools for accelerating boilerplate generation

Example:
Some generated suggestions introduced unnecessary complexity, which were simplified to maintain MVP scope and keep the architecture clean and practical.

All final design decisions and code structure were reviewed and adjusted manually.

---

## Author

**Mehakjot Singh**
Full Stack Developer (React + Java/Spring Boot)
