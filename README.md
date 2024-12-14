# 💼 Overview of JobSeeker API

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
- **Maven**: Dependency and project build management.
- **External APIs**: Enables fetching job listings and articles to provide users with learning resources and job opportunities.
- **Email Integration**: Facilitates sending job applications and professional emails directly from the platform, enhancing communication efficiency.
- **CV Template Engine**: Powers the creation of customizable and professional resumes directly through the API or web interface.
- **DOCX Generation Tools**: Ensures high-quality export of CVs and other documents in universally accepted formats.  

---

## 🚀 Running the Project
### 1. Prerequisites:
- **JDK 17:** Install JDK 17.
- **MySQL:** Install and run MySQL server locally.
- **Maven:** Install Maven.
- **Google Account:** Needed for email configuration (if you want to use this feature).
- **Postman:** You'll need Postman to test the API of the application. If you don't have it, download and install Postman.

### 2. Configure MySQL Database:
- Ensure that you have MySQL installed and running on port 3306.
- Create a database. You can name it anything, but for this example, we are using the name cvenjoyer. If you prefer a different name, modify the configuration in the application.properties file accordingly.

- To create the database, run the following query in MySQL:
- **CREATE DATABASE cvenjoyer;**
- If you decide to use a different database name, remember to update the spring.datasource.url value in the application.properties file to reflect the correct database name:
- **spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name**

### 3. Configure application.properties File:
- **In the application.properties file (found in src/main/resources), input the following configuration values:**

| Variable Name                                      | Value                                                        | Description                                                                                     |
|----------------------------------------------------|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| spring.datasource.url                              | jdbc:mysql://localhost:3306/cvenjoyer                        | The URL for connecting to the MySQL database (change `cvenjoyer` to your database name if needed).|
| spring.datasource.username                         | root                                                         | The username for your MySQL database.                                                          |
| spring.datasource.password                         | root                                                         | The password for your MySQL database.                                                          |
| spring.datasource.driver-class-name                | com.mysql.cj.jdbc.Driver                                     | The JDBC driver class used to connect to MySQL.                                                |
| spring.jpa.hibernate.ddl-auto                      | update                                                       | Hibernate configuration for automatic schema updates (can be set to `none`, `create`, `create-drop`).|
| spring.jpa.show-sql                                | true                                                         | Enables the display of SQL queries in the logs.                                                |
| jwt.expiration                                     | 300000000                                                    | The expiration time for the JWT token in milliseconds.                                         |
| jwt.secret                                         | DefinitelyNotASecretDefinitelyNotASecretDefinitelyNotASecret | The secret key used to sign and verify the JWT token.                                          |
| spring.mail.host                                   | smtp.gmail.com                                               | The SMTP server used for email sending (in this case, Gmail).                                  |
| spring.mail.port                                   | 587                                                          | The port used for SMTP communication (587 is the default for Gmail).                           |
| spring.mail.username                               | fill it                                                      | Your email address used for sending emails.                                                    |
| spring.mail.password                               | fill it                                                      | The application-specific password generated for Gmail (not your regular Gmail password).       |
| spring.mail.protocol                               | smtp                                                         | The protocol used for sending emails (SMTP in this case).                                      |
| spring.mail.properties.mail.smtp.auth              | true                                                         | Enables SMTP authentication.                                                                   |
| spring.mail.properties.mail.smtp.starttls.enable   | true                                                         | Enables STARTTLS for secure communication.                                                     |
| spring.mail.properties.mail.debug                  | true                                                         | Enables debugging for email sending (useful for troubleshooting).                              |

### 4. Generating an Application-Specific Password for Gmail:
- If you are using Gmail for email sending, you must generate an application-specific password in your Google account. Here’s how:
- Log in to your Google account.
- Open the Google Account Management page.
- In the address bar of your browser, enter the following URL:
- **https://myaccount.google.com/apppasswords**
- Log in again if prompted.
- Select the application (e.g., "Mail") and device (e.g., "Windows computer") and click Generate.
- Copy the generated application password.
- Paste this password into the application.properties file in the spring.mail.password field.

### 5. Build and Run the Application:
- Once all the files are configured and values are set, go to the project’s root directory and build the application by running the following command in your terminal:
- **mvn clean package**

- After the build process is complete, run the application with the following command:
- **mvn spring-boot:run**
- The application should now be running locally on port 8080 (by default). To begin testing its features:
- In the Browser: Go to http://localhost:8080/api to check if the application is working properly.
- In Postman: Open Postman and test the various API endpoints. Remember, some endpoints require authentication, so you’ll need to add the JWT token in the request header:
- In Postman, go to the Authorization tab, choose Bearer Token, and paste your JWT token in the Token field.
- Now you can begin testing the application in Postman by making requests to the available endpoints.

### 6. Testing User Features:
- **To test the user features, follow these steps:**

- **Register a New User:**

If you don't have an account yet, you must first register a new user. After registration, you will be able to log in and get your JWT Bearer token.

User Registration Example
![User Registration](https://i.imgur.com/u2C17NL.png)
*Screenshot showing the user registration process.*

- **Log In:**

After registering, log in with your user credentials. You will receive a JWT Bearer token.

User Login Example
![User Login](https://i.imgur.com/eeYkVb8.png)
*Screenshot showing the user login process.*

- **Include the Bearer Token:**

Important: Remember to include the Bearer token in the Authorization header of your requests where required to access the features of the application.

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

**Swagger Documentation**
- **API documentation using Swagger for all controllers, providing API endpoints and descriptions.**

---

## 📸 Screenshots
Below you will find screenshots illustrating the functionalities of selected modules of the application. These are just a few examples—please refer to the full application details for a complete overview.

### User Registration:
![User Registration](https://i.imgur.com/u2C17NL.png)
*Screenshot showing the user registration process.*

### User Login:
![User Login](https://i.imgur.com/eeYkVb8.png)
*Screenshot showing the user login process.*

### Job Offer Creation:
![Job Offer Creation](https://i.imgur.com/DEdOEy7.png)
*Screenshot showing the creation of a job offer.*

### User Information View:
![User Information View](https://i.imgur.com/5OCTL7i.png)
*Screenshot showing the details of the logged-in user.*

### Recruitment Review Creation:
![Recruitment Review Creation](https://i.imgur.com/XrHckB6.png)
*Screenshot showing the process of adding a recruitment review for a company.*

### Fetching Job Offers (Remotive API):
![Fetching Job Offers](https://i.imgur.com/4IjftJ5.png)
*Screenshot showing fetching job offers from the Remotive API.*

### Applying for a Job (Remotive API):
![Applying for a Job](https://i.imgur.com/UJvnLvZ.png)
*Screenshot showing the process of applying for a job from the Remotive API.*

### Fetching IT Articles (Dev.to API):
![Fetching IT Articles](https://i.imgur.com/sBsohBB.png)
*Screenshot showing fetching IT articles from the Dev.to API.*

### CV Creation:
![CV Creation](https://i.imgur.com/E3tLAIz.png)
*Screenshot showing the process of creating a CV in the application.*

### Email Sending:
![Email Sending](https://i.imgur.com/CTcbATd.png)
![Delivered Email](https://i.imgur.com/Vfn93CQ.png)
*Screenshot showing the email sending functionality in the application.*

---

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

### 🚨 Troubleshooting Email Sending Endpoint
- **If you encounter issues with the email sending endpoint, it is possible that your antivirus or firewall is blocking the connection to the Gmail SMTP server. In that case, try temporarily disabling your antivirus software or firewall and test again.**

- **Note: Some security software may block outgoing SMTP traffic, which can prevent email functionality from working properly. Disabling the antivirus or firewall can help resolve this issue during testing.**
