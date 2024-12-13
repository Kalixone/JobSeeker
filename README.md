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

## ⭐ Features Overview

### Authentication Management Endpoints
#### Available for Everybody:

🌐 POST: /api/auth/registration - registers a new user.
🌐 POST: /api/auth/login - sign in for an existing user.

### Article Management Endpoints
#### User Available:

👤 GET: /api/articles - fetches all articles available for the authenticated user.
👤 GET: /api/articles/byKeyword?word={keyword} - fetches articles by a specific keyword.
👤 GET: /api/articles/byTitle?title={title} - fetches articles by title.
👤 GET: /api/articles/byDescription?description={description} - fetches articles by description.

### CV Management Endpoints
#### User Available:

👤 POST: /api/cv/generate - generates a CV file based on provided data and returns it as a downloadable file.

### Email Management Endpoints
#### User Available:

👤 POST: /email/send-email - allows an authenticated user to send an email by providing recipient, subject, and text.
👤 GET: /email - retrieves all emails associated with the authenticated user.
👤 GET: /email/search-by-recipient?recipient={recipient} - searches emails sent by the authenticated user where the recipient contains the specified string.
👤 GET: /email/search-by-date?startDate={startDate}&endDate={endDate} - searches emails sent by the authenticated user within the specified date range.

### Job Management Endpoints
#### User Available:

👤 POST: /api/jobs - create a new job with all necessary details.
👤 GET: /api/jobs - retrieve all available jobs.
👤 GET: /api/jobs/{id} - fetch job details by ID.
👤 PUT: /api/jobs/{id}/feedback - update feedback date for a specific job by its ID.
👤 PUT: /api/jobs/{id}/status - update the job status for a specific job by its ID.
👤 GET: /api/jobs/filtr - fetch a list of jobs sorted by salary in ascending order.
👤 GET: /api/jobs/applied - fetch jobs with the status 'APPLIED'.
👤 GET: /api/jobs/rejected - fetch jobs with the status 'REJECTED'.
👤 GET: /api/jobs/expired - fetch jobs with the status 'EXPIRED'.
👤 GET: /api/jobs/findByKilometers?from={from}&to={to} - find jobs within the specified kilometers range.
👤 PUT: /api/jobs/{id}/companyName - update the company name for a specific job by its ID.
👤 PUT: /api/jobs/{id}/position - update the position/title for a specific job by its ID.
👤 PUT: /api/jobs/{id}/location - update the location for a specific job by its ID.
👤 PUT: /api/jobs/{id}/salary - update the salary for a specific job by its ID.
👤 PUT: /api/jobs/{id}/applicationDate - update the application date for a specific job by its ID.
👤 PUT: /api/jobs/{id}/interviewDate - update the interview date for a specific job by its ID.
👤 PUT: /api/jobs/{id}/jobType - update the job type (e.g., full-time, part-time) for a specific job.
👤 PUT: /api/jobs/{id}/link - update the link to the job posting.
👤 PUT: /api/jobs/{id}/companyWebsite - update the company website link for a specific job.
👤 PUT: /api/jobs/{id}/contactEmail - update the contact email for a specific job.
👤 PUT: /api/jobs/{id}/kilometers - update the distance for a specific job offer.
👤 PUT: /api/jobs/{id}/notes - update the notes for a specific job.
👤 PUT: /api/jobs/{id}/tags - update the tags associated with a specific job.
👤 DELETE: /api/jobs/{id} - delete a specific job offer by its ID.
👤 GET: /api/jobs/favourite - retrieve all the favourite jobs of the authenticated user.
👤 PUT: /api/jobs/favourite/{id} - add a specific job to the authenticated user's favourite list.

### Recruitment Review Management Endpoints
#### User Available:

👤 POST: /api/reviews/create - create a recruitment review for a company based on the user's experience.
👤 GET: /api/reviews/all - fetch a list of all recruitment reviews.
👤 GET: /api/reviews/users - fetch a list of all recruitment reviews created by the authenticated user.
👤 DELETE: /api/reviews/{id} - delete a recruitment review based on its ID.
👤 GET: /api/reviews/search - search recruitment reviews by company name.
👤 GET: /api/reviews/searchForUser - search reviews left by a user for a specific company.
👤 GET: /api/reviews/searchByRating - search reviews by rating.
👤 GET: /api/reviews/searchByRatingForUser - search user reviews by rating.
👤 GET: /api/reviews/searchByStages - search reviews by recruitment stage.
👤 GET: /api/reviews/searchByStagesForUser - search user reviews by recruitment stage.
👤 PUT: /api/reviews/update/{id} - update a specific recruitment review.

### Remote API Job Management Endpoints
#### User Available:

👤 GET: /api/remote-api-jobs/remotive - fetch a list of all jobs saved from the Remotive API.
👤 GET: /api/remote-api-jobs/apply/{id} - apply for a job by its ID.
👤 GET: /api/remote-api-jobs/search/company - search jobs by company name.
👤 GET: /api/remote-api-jobs/search/position - search jobs by position.
👤 GET: /api/remote-api-jobs/search/tags - search jobs by tags (e.g., frameworks, technologies).
👤 GET: /api/remote-api-jobs/search/jobType - search jobs by job type (e.g., full-time, contract, remote).
👤 GET: /api/remote-api-jobs/search/applicationDate - search jobs by application date range.
👤 GET: /api/remote-api-jobs/search/location - search jobs based on candidate required location.

### User Management Endpoints
#### User Available:

👤 PUT: /api/user/update/frameworks - update the frameworks of the authenticated user.
👤 PUT: /api/user/update/english-level - update the English language proficiency level of the authenticated user.
👤 PUT: /api/user/update/programming-language - update the programming languages of the authenticated user.
👤 PUT: /api/user/update/experience-level - update the experience level of the authenticated user.
👤 GET: /api/user/profile/info - fetch the profile information of the authenticated user.
👤 PUT: /api/user/update/city - updates the city of the authenticated user.
👤 PUT: /api/user/update/dailyGoal - updates daily goals for the authenticated user.
👤 DELETE: /api/user/reset/frameworks - resets the frameworks list for the authenticated user.
👤 DELETE: /api/user/reset/programmingLanguages - resets the programming languages list for the authenticated user.
👤 DELETE: /api/user/reset/experienceLevel - resets the experience level of the authenticated user.

### User Statistics Endpoints
#### User Available:

👤 GET: /api/statistics/total-applications - gets the total number of applications made by the authenticated user.
👤 GET: /api/statistics/total-expired - gets the total number of expired job listings for the authenticated user.
👤 GET: /api/statistics/total-rejected - gets the total number of rejected job listings for the authenticated user.
👤 GET: /api/statistics/total-applied - gets the total number of jobs that the authenticated user has applied to.
👤 GET: /api/statistics/preferred-jobType - gets the preferred job type for the authenticated user.
👤 GET: /api/statistics/count-applications-in-date-range - gets the total number of applications made by the authenticated user within a specific date range.