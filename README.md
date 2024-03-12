## Student Management System - Web UI Automation and CI/CD

**Purpose:** 
*  This web application streamlines student record management for educational institutions. It simplifies adding, modifying, and searching for student data, manages course enrollments, and provides features to filter students based on their activity status.

**Technologies:**
*  Java 
*  Spring Boot
*  Thymeleaf 
*  H2 Database 
*  JPA 
* Lombok
*  Selenium 
*  GitHub Actions
*  CircleCI

**Features:**

* Student Management:
  * Create new student records
  * Modify existing student details (name, email, etc.)
  * Enroll students in courses
  * View a student's total course credits

* Search:
    * Find students by ID
    * Find students by first or last name (partial matches)

* Student Filtering:
    * List all active students

**Testing and Automation**

*  **Unit Tests:** Unit tests ensure that individual components of the codebase (controllers, services, etc.) function as expected.
*  **Web UI Tests (Selenium):** Selenium tests automate interactions with the web interface, verifying user flows such as adding a student, searching, and updating records.
*  **GitHub Actions 1:**  [test-build.yaml] 
   * **Triggers**: Changes pushed to the 'main' and 'develop' branches, as well as pull requests targeting these branches. 
   * **Actions**:
     * Sets up Java environment
     * Executes Maven build and tests (mvn package)
     * Stores test results
 
* **Issue Commenting Automation:** Automatically adds a friendly acknowledgment message to new issues, reassuring users that their issue is being noticed.

*  **CircleCI Redundancy:** Provides an alternative CI/CD pipeline on CircleCI, ensuring testing reliability and redundancy.

**How to Run the Project**

**Prerequisites:**
*   Java Development Kit (JDK) version 17+
*    Apache Maven

**Steps:**
1) Clone the repository: ``git clone https://github.com/[your-username]/[your-repo-name]``
2) Build and Run: 
   ``cd [your-repo-name]``
   ``mvn package``
   ``mvn spring-boot:run``
3) Access the application: Open ``http://localhost:8080/student/`` in your web browser.


**Project Structure** 


* src/main/java: Contains the main Java source code
  * ada.edu.demo.webtest.controller: Controller classes handling web requests
  * ada.edu.demo.webtest.entity: Entity classes representing student data and courses
  * ada.edu.demo.webtest.service: Service classes handling business logic
  * ada.edu.demo.webtest.repository: Repository interfaces for data access
* src/test/java: Contains test cases
* src/main/resources:
  * templates: Thymeleaf templates for the web views
  * application.properties: Basic application configuration
* pom.xml: Maven project configuration


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/jwssRZI4)
