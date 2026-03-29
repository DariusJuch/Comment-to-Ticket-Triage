# PulseDesk - AI Comment-to-Ticket System

## 🚀 Overview

This project automatically converts user comments into structured support tickets using AI.

## 🛠 Tech Stack

* Java 17+
* Spring Boot
* H2 Database
* Hugging Face Inference API

## 📡 API Endpoints

### Create comment

POST /api/comments

### Get comments

GET /api/comments

### Get tickets

GET /api/tickets

## ⚙️ Setup

1. Clone repo
2. Set environment variable:

   ```
   HF_API_KEY=your_key
   ```
3. Run:

   ```
   mvn spring-boot:run
   ```

## 🤖 AI Integration

Uses Hugging Face model to classify comments and generate ticket data.

## 📌 Features

* Automatic ticket creation
* Category detection
* Priority assignment
* REST API

## 💡 Future Improvements

* Add authentication
* Add frontend UI
* Deploy to cloud
