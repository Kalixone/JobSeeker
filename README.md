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

## 📊 Project Structure

**Controllers**
- **AuthenticationController:** Handles user login, registration, and authentication requests.
- **CvController:** Manages CV upload, update, and retrieval.
- **EmailController:** Handles email sending, updates, and other email-related operations.
- **JobController:** Oversees job listings, updates, and job-related operations.
- **RecruitmentReviewController:** Manages the recruitment review process, including adding, updating, and reviewing recruitment statuses.
- **RemoteApiJobController:** Handles integration with remote job APIs, fetching job listings, and synchronizing them.
- **UserController:** Manages user-related operations, such as profile management, role updates, and permissions.
- **UserStatisticsController:** Provides user statistics, including activity tracking and engagement metrics.

**DTOs (Data Transfer Objects)**
- **DTOs, such as:** CvDto, EmailDto, JobDto, UserDto, RecruitmentReviewDto, UserStatisticsDto, LocationDto, UpdateDtos – Used for transferring data between controllers and services, handling CV, email, job, user, recruitment review, user statistics, location, and entity updates. There are more DTOs, these are just examples.

**Mappers**
- **CvMapper:** Maps CV-related DTOs to entities and vice versa.
- **EmailMapper:** Converts email-related DTOs to entities and vice versa.
- **JobMapper:** Maps job-related DTOs to entities and vice versa.
- **RecruitmentReviewMapper:** Maps recruitment review DTOs to entities.
- **UserMapper:** Converts user DTOs to entities and vice versa.
- **UserStatisticsMapper:** Maps user statistics DTOs.
- **LocationMapper:** For mapping location data to entities and DTOs.

**Services**
- **AuthenticationService:** Handles login, registration, and authentication logic.
- **CvService:** Manages operations related to CV handling.
- **EmailService:** Manages sending and receiving emails.
- **JobService:** Handles job-related business logic, including creating, updating, and retrieving job listings.
- **JobFetcherService:** Fetches jobs from remote APIs and synchronizes with the local database.
- **RecruitmentReviewService:** Manages recruitment review operations, including reviewing applicants and updating statuses.
- **UserService:** Handles user operations such as profile updates, password management, and user status.
- **UserStatisticsService:** Provides business logic for calculating and retrieving user statistics.
- **ValidationService:** Manages validation logic for various entities, such as email validation, user input validation, etc.

**Repositories**
- **BadgeRepository:** Handles badge-related database operations.
- **CvRepository:** Manages CV data persistence.
- **EmailRepository:** Stores and retrieves email-related data.
- **JobApiRepository:** For storing and managing jobs fetched from external APIs.
- **JobRepository:** Handles CRUD operations for job listings.
- **RecruitmentReviewRepository:** Manages recruitment reviews data persistence.
- **RoleRepository:** Handles role-related data persistence.
- **UserRepository:** Manages user-related data persistence.
- **UserStatisticsRepository:** Stores and retrieves user statistics.

**Exceptions**
- **AuthenticationException:** Handles errors related to authentication failures.
- **EntityNotFoundException:** Used when an entity (like a user, job, or CV) is not found.
- **LocationNotFoundException:** Thrown when a location is not found.
- **RegistrationException:** Used for errors during user registration.
- **CustomErrorResponse:** Standard error response format.
- **CustomGlobalExceptionHandler:** A global exception handler to catch and manage all application-level exceptions.

**Security**
- **AuthenticationController:** Manages user login and registration processes.
- **CustomUserDetailsService:** A custom service for loading user details for authentication.
- **JwtAuthenticationFilter:** Filter for handling JWT-based authentication.
- **JwtUtil:** Utility for generating and validating JWT tokens.
- **SecurityConfig:** Configuration for Spring Security, including security policies and authentication mechanisms.

**Configuration**
- **application.properties:** Main application configuration file with database, server settings, etc.
- **liquibase.properties:** Configuration file for managing database changes.
- **liquibase.changelog:** Liquibase change log file containing database schema updates and migration scripts.
- **Dockerfile:** Defines the steps to build the Docker container for the application.
- **docker-compose.yml:** Docker Compose configuration file for managing multi-container setups (e.g., app + database).

**Models**
- **Badge:** Entity for user badges.
- **Cv:** Entity for storing CVs.
- **Email:** Entity for storing email details.
- **Job:** Entity representing a job listing.
- **JobApi:** Entity for jobs fetched from remote APIs.
- **RecruitmentReview:** Entity for recruitment review data.
- **Role:** Entity for user roles.
- **User:** Entity for users in the system.
- **UserStatistics:** Entity for storing user activity and engagement statistics.
- **Location:** Entity for managing user or job location data.

**Validation**
- **EmailValidator:** Validates email formats.

**Infrastructure**
- **Docker Configuration:** Dockerfile, docker-compose.yml for running the application in Docker containers.
- **Liquibase Change Logs:** Contains scripts for updating and initializing the database.

**Swagger Documentation**
- **API documentation using Swagger for all controllers, providing API endpoints and descriptions.**
