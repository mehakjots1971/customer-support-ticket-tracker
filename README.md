# Customer Support Ticket Tracker MVP

A simple, full-stack application to track customer support tickets. This MVP demonstrates a 3-tier architecture using React (Frontend), Spring Boot (Backend), and in-memory data storage.

## Architecture

- **Frontend**: React + Vite + Tailwind CSS
- **Backend**: Spring Boot (Java 17) + REST API
- **Data Layer**: In-Memory Storage (ConcurrentHashMap)

## Prerequisites

- Node.js (v18+)
- Java JDK 17+
- Maven (v3.8+)

## Setup & Run Instructions

### 1. Run the Backend (Spring Boot)

Open a terminal in the root directory:

```bash
cd backend
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`.

### 2. Run the Frontend (React)

Open a **second** terminal in the root directory:

```bash
cd front-end
npm install
npm run dev
```

The frontend application will be available at `http://localhost:5173`.

## API Endpoints

| Method | Endpoint            | Description          | Payload Example                                                                                                                   |
| ------ | ------------------- | -------------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| POST   | `/api/tickets`      | Create a new ticket  | `{"customerName": "John", "customerEmail": "john@example.com", "subject": "Help", "description": "Issue...", "priority": "HIGH"}` |
| GET    | `/api/tickets`      | Get all tickets      | N/A                                                                                                                               |
| GET    | `/api/tickets/{id}` | Get ticket by ID     | N/A                                                                                                                               |
| PATCH  | `/api/tickets/{id}` | Update ticket status | `{"status": "IN_PROGRESS"}`                                                                                                       |

## Design Decisions & Tradeoffs

- **In-Memory Storage**: Chose `ConcurrentHashMap` for speed and simplicity as per MVP requirements. **Tradeoff**: All data is lost when the application restarts.
- **REST Protocol**: Standard RESTful design for clear separation of concerns.
- **No Authentication**: Omitted to fit within the 2-3 hour timeframe.
- **Validation**: Basic validation added (e.g., required fields, email format).
- **Lombok Removal**: Removed Lombok to ensure maximum compatibility and reduce build complexity during improved stability.

## AI Usage

Used AI tools (GitHub Copilot / Coding Assistant) for:

- Generating boilerplate code (Spring Boot Controller/Service structure).
- Creating basic React components.
- Debugging compilation errors (Lombok configuration issues).
- **Modification**: Manually stepped in to remove Lombok when it caused persistent build failures, opting for stable manual code over tool-dependent convenience.
