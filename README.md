# 🚀 PulseDesk - AI Comment-to-Ticket Triage System

PulseDesk is a backend system that automatically converts user comments into structured support tickets using AI (Hugging Face Inference API).

The goal of this project is to simulate a real-world support system where incoming feedback is intelligently classified and prioritized.

---

# 🧠 Features

- Submit user comments via REST API
- Automatically analyze comments using AI (Hugging Face)
- Decide whether a comment becomes a support ticket
- Generate structured ticket data:
  - Title
  - Category (bug / feature / billing / account / other)
  - Priority (low / medium / high)
  - Summary
- Store comments and tickets in memory / H2 database
- Retrieve all tickets or single ticket

---

# 🏗️ Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- REST API
- Hugging Face Inference API

---

# 📡 API Endpoints

## ➤ Create Comment

POST /comments

Request body:
{
  "text": "App crashes when I click save"
}
➤ Get All Comments
GET /comments
➤ Get All Tickets
GET /tickets
➤ Get Ticket by ID
GET /tickets/{id}
🤖 AI Integration

This project uses the Hugging Face Inference API to analyze comments and return structured JSON.

Example prompt output:

{
  "isTicket": true,
  "title": "App crash on save",
  "category": "bug",
  "priority": "high",
  "summary": "Application crashes when user clicks save button"
}
⚙️ Setup & Run
1. Clone repository
git clone https://github.com/DariusJuch/Comment-to-Ticket-Triage
cd Comment-to-Ticket-Triage/backend/pulsedesk
2. Set environment variables

Create system environment variable:

HF_API_KEY=your_huggingface_api_key
3. Run application
mvn spring-boot:run

Application will start on:

http://localhost:8080
🧪 Example Usage
Create a comment:
curl -X POST http://localhost:8080/comments \
-H "Content-Type: application/json" \
-d '{"text":"Payment not working"}'
Get tickets:
curl http://localhost:8080/tickets
🧱 Architecture Overview
User → REST API → Comment Service → Hugging Face API
                                  ↓
                          AI Response Parser
                                  ↓
                           Ticket Generator
                                  ↓
                             Database
📌 Future Improvements
Add authentication (Spring Security)
Replace in-memory DB with PostgreSQL
Add frontend dashboard
Improve AI prompt accuracy
Add ticket status workflow (OPEN / IN_PROGRESS / CLOSED)
Deploy to cloud (AWS / GCP)
💡 Author

Darius Juch

Project built for backend internship application demonstrating:

REST API design
AI integration
Clean architecture
Spring Boot backend development
🏁 Summary

PulseDesk demonstrates how AI can be integrated into backend systems to automate support workflows and improve efficiency.
