# 💳 Cashfree Payment Gateway Integration (Spring Boot + Thymeleaf)

This project demonstrates a seamless **integration of the Cashfree Payment Gateway** using **Spring Boot** for the backend and **Thymeleaf** for the frontend. It simulates a real-world checkout and payment flow, supports **card tokenization**, and uses **webhooks** for transaction status updates. All relevant transaction and order data is securely stored in a **MySQL database**.

---

## 🔧 Tech Stack

- **Backend**: Spring Boot (REST APIs, Webhooks)
- **Frontend**: Thymeleaf (Checkout Page)
- **Database**: MySQL
- **Payment Gateway**: Cashfree (Test Environment)
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

---

## ✅ Features

- User-friendly checkout page using Thymeleaf
- Order and transaction creation with status tracking
- Card tokenization support for future use
- Secure capture of order and payment information in MySQL
- Webhook integration to update final payment status automatically
- Clean, modular backend service with proper validations and error handling

---

## 🧠 Database Design

The system uses a normalized database structure to store:
- **Customers**
- **Orders**
- **Transactions**
- **Card Details**

📎 ER Diagram is included in the repository as `ER.mwb` (MySQL Workbench format).

---

## 🧪 How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/niteshkumarsaini/Cashfree-Integration.git
   cd cashfree-integration
