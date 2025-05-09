📌 Project Overview
The USSD Request Analyzer is a full-stack project that ingests, analyzes, and displays USSD request-response transactions for voucher purchases.

Tech Stack Highlights:
💻 Spring Boot (Java 17) backend with REST APIs
🌐 React.js + Tailwind CSS frontend via Vite
🗄️ PostgreSQL with Flyway for DB migrations
📘 OpenAPI/Swagger for API docs and testing

🚀 How to Clone and Run the Monorepo
1️⃣ Clone the Repository

bash
Copy
Edit
git clone https://github.com/your-username/ussd-analyzer.git
cd ussd-analyzer
2️⃣ Run the Frontend (React UI)

bash
Copy
Edit
cd ussd-ui
npm install
npm run dev
🔹 UI will be available at: http://localhost:5173
🔹 Use VS Code or any modern editor to modify the frontend.

3️⃣ Run the Backend (Spring Boot API)

✅ Option A: Using IntelliJ IDEA
Open ussd-analyzer in IntelliJ.
Run UssdAnalyzerApplication.java inside:

swift
Copy
Edit
src/main/java/com/matrix/ussdanalyzer/UssdAnalyzerApplication.java
Ensure your PostgreSQL DB is running locally.

✅ Option B: Using Git Bash or Terminal

bash
Copy
Edit
  mvn clean install         # Builds the backend with dependencies
  mvn spring-boot:run       # Runs the Spring Boot app
  
Upload Excel Records to PostgreSQL using Postman:

-Save your Excel files in .xlsx format first.

-Postman POST Request:
-Excel file upload API: http://localhost:8081/api/excel/upload

-Select form-data

-Key: requestFile

-Key: responseFile

Send the post request, and the Excel records will be saved to PostgreSQL.

✅ API base URL: http://localhost:8081

🛢️ PostgreSQL Database Configuration
Ensure PostgreSQL is running locally with a database named ussd_db.

Sample DB credentials from application.yml:

yaml
Copy
Edit
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ussd_db
    username: postgres
    password: yourpassword
🛠 You can edit this in ussd-backend/src/main/resources/application.yml.

✅ Flyway will auto-create tables on app startup.

📖 API Testing & Documentation
Swagger UI: http://localhost:8081/swagger-ui.html

🧩 SQL Setup & Auto-Migrations
Schema is automatically created using Flyway on startup:

ussd_requests

ussd_responses

blacklisted_msisdns

Indexed views for analytics & fraud detection.
Optional: Manual SQL scripts provided in schema.sql.

🧠 Project Features
Feature	Description
✅ Summary Reporting	Success/failure counts per request status.
✅ Paginated Transactions	Latest N requests shown in descending order.
✅ Fraud Detection	Flags MSISDNs using multiple voucher codes.
✅ Blacklisting	Persisted list of blacklisted MSISDNs.
✅ Swagger Docs	Auto-generated OpenAPI documentation.
✅ Clean UI	Responsive, modern React.js UI via Tailwind.

🏗️ Project Summary
To support high traffic loads, ensure low latency, and enable robust system-to-system communication, the USSD Request Analyzer is built on scalable backend engineering principles and optimized system design strategies.

⚙️ Concurrency & Resource Management
Thread Pool Management:
All asynchronous processing (e.g., fraud detection) uses a dedicated @Async thread pool, configured with custom executors (TaskExecutor) to avoid resource starvation under heavy load.

Database Connection Pooling:
Uses HikariCP, the industry-standard high-performance JDBC connection pool, tuned for optimal performance and low overhead in high-concurrency environments.

Batch Processing:
Critical DB operations (e.g., logs, analytics) use batched read/write operations to reduce transaction costs and avoid I/O bottlenecks.

⚡ Performance Optimizations
Caching Layer:
Frequently accessed responses (e.g., MSISDN fraud status) are cached using Spring Cache, backed by Redis or in-memory fallback, significantly reducing DB hits.

Efficient Querying:
All data-heavy endpoints (e.g., transaction logs) implement offset-based pagination and avoid N+1 queries.

Compression & Optimization:
GZIP compression is enabled for all REST responses, reducing payload size and improving API response times.

Indexing & Partitioning:
Strategic indexing on correlation_id, msisdn, and timestamp ensures fast lookups. The schema is partition-ready by time to support future horizontal sharding or time-based archiving.

📡 Communication & Integration
RESTful APIs:
Exposes well-structured JSON-based REST APIs, secured with OAuth2 and JWT. API contracts are documented with OpenAPI/Swagger.

Asynchronous Messaging:

Kafka: Core events (e.g., fraud detection triggers) are published to Kafka topics to enable real-time analytics and downstream consumption.

RabbitMQ (optional): Configurable queue for sending external alerts, retries, or inter-system communication (e.g., blacklisting services).

Resilience Patterns:
Fault-tolerant integrations use Resilience4j for retry, fallback, and rate-limiting. Circuit breakers prevent cascading failures.

🔐 Security & Compliance
Authentication & Authorization:
Implements JWT-based authentication with fine-grained role-based access control (RBAC).

Transport & API Security:
Enforces HTTPS, CORS, and secure headers,Spring Security. REST APIs are protected against common threats (CSRF, XSS, injection).

Secret Management:
Credentials and sensitive configs are externalized via Spring Cloud Config, Docker secrets, or vault providers to avoid code-level leakage.

☁️ Scalability & Observability
Containerization:
Backend is Dockerized for environment consistency and horizontal scaling across multiple nodes , infrastructure Autoscaling configurations using kubernets.yml files.

Kubernetes-Ready:
Supports deployment to Kubernetes, with integration hooks for service discovery, config maps, autoscaling, and resource quotas.

Observability:

Integrated with Prometheus for real-time metrics.

Supports Zipkin/Sleuth for distributed tracing of request flows.

Application logs are structured (JSON) and centralized for log aggregation tools (e.g., ELK stack).

🔭 Future Enhancements
Feature	Benefit
Kafka Streams	Real-time fraud analytics and stream processing
Grafana Dashboards	Visual monitoring of request rates, latency, errors
CQRS + Event Sourcing	Improves scalability and auditability of write models
WebSockets	Real-time transaction updates to UI clients
API Gateway	Centralized auth, rate-limiting, and request routing

 Conclusion
By combining asynchronous processing, caching, observability, and message-driven communication, the USSD Request Analyzer is architected to withstand production-level traffic while remaining flexible, modular, and secure. The design is aligned with modern backend engineering principles and enables smooth integration with external systems and analytics platforms.
