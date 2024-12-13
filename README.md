# 🧳 Overview of JobSeeker API

Welcome to the documentation for the **JobSeeker Project**, a Java Spring-based RESTful API developed to streamline job application management and empower job seekers with essential tools and resources.

---

## 🎯 Purpose

The JobSeeker API is designed to:

- **Simplify Job Tracking:** Organize applications, manage statuses, and maintain progress.
- **Streamline Communication:** Use integrated email tools to interact with recruiters or companies.
- **Generate Professional Documents:** Create polished CVs using customizable templates.
- **Offer Insights and Resources:** Fetch job listings and articles through external API integrations.
- **Ensure Security:** Leverage robust authentication and role-based access control for data safety.

---  

## 💡 Features

With the JobSeeker API, users can:

- **Manage Job Applications:** Add details such as company name, position, and salary.
- **Track Application Statuses:** Categorize progress into Applied, Interview, or Rejected.
- **Review Recruitment Processes:** Write and explore reviews about company hiring experiences.
- **Send Professional Emails:** Communicate effectively with employers directly from the platform.
- **Discover Opportunities:** Fetch job listings and articles from external APIs for learning and growth.
- **Generate CVs:** Create professional resumes using ready-made templates.
- **Monitor Statistics:** Track user progress, such as the number of applications submitted or preferred job types.

---  

## 🛠️ Technologies Used

- **Java**: Core language for building the API.
- **Spring Boot**: Framework for efficient and scalable application development.
- **JWT (JSON Web Token)**: Secure authentication and authorization.
- **Spring Security**: Implements role-based access control for user safety.
- **Spring Data JPA**: Simplifies database operations with repository and ORM support.
- **MySQL**: Relational database for storing user and application data.
- **Swagger**: Generates interactive API documentation for better usability.
- **Docker**: Ensures consistent deployment in containerized environments.
- **JUnit 5**: Framework for comprehensive unit testing.
- **Maven**: Dependency and project build management.
- **External APIs**: Enables fetching job listings and articles to provide users with learning resources and job opportunities.
- **Email Integration**: Facilitates sending job applications and professional emails directly from the platform, enhancing communication efficiency.
- **CV Template Engine**: Powers the creation of customizable and professional resumes directly through the API or web interface.
- **PDF Generation Tools**: Ensures high-quality export of CVs and other documents in universally accepted formats.  

---

## 🚀 Running the Project

To run the project, follow these steps:

### 1. Install Required Tools:

Download and install Docker, Maven, and JDK Development Kit.

### 2. Clone the Project Repository:

Clone the project repository using Git.

### 3. Configure Environment Variables:

In the `.env` file, provide the necessary environment variables related to the database and Docker. Below is an example configuration:

| Variable Name         | Value |
|-----------------------|-------|
| MYSQLDB_DATABASE      | jobs  |
| MYSQLDB_USER          | admin |
| MYSQLDB_PASSWORD      | root  |
| MYSQLDB_ROOT_PASSWORD | root  |
| MYSQLDB_LOCAL_PORT    | 3307  |
| MYSQLDB_DOCKER_PORT   | 3306  |
| SPRING_LOCAL_PORT     | 8081  |
| SPRING_DOCKER_PORT    | 8080  |
| DEBUG_PORT            | 5005  |

### 4. Build the Application:
Run the command mvn clean package to build the application.

### 5. Run the Docker Container:
Execute the command docker-compose build to build the Docker container.

Then, use docker-compose up to start the Docker container.

### 6. Accessing the Application:
The application will be available locally at: http://localhost:8081/api.

You can test the application using tools such as Postman or Swagger. For Postman, remember to pass the authentication (Bearer Token) received after logging in.

### 7. Testing Admin and Standard User Features:
- **To test admin features, use the following login credentials:**

Email: admin@example.com,
Password: 123123123

- **For testing standard user features, follow these steps:**

Register a New User:

- **Endpoint: POST /register**
  Request Body: Provide details for email, first name, last name, and password.
  Log In:

- **Endpoint: POST /login**
  Request Body: Use the email and password from the registration step.
  Obtain Bearer Token:

After logging in, you will receive a JWT Bearer token.
Access Standard User Features:

Include the Bearer token in the Authorization header of your requests to access standard user features.

---