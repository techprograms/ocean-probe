=================================================================
                  Remote Ocean Probe - REST API
=================================================================

1. PROJECT OVERVIEW
--------------------
This project is a Spring Boot REST API that allows remote control of an
ocean probe navigating the seabed. The probe can:
- Move forwards and backwards.
- Rotate left and right.
- Stay within the defined grid.
- Avoid obstacles.
- Keep track of visited coordinates.

=================================================================

2. TECHNOLOGIES USED
--------------------
- Java 17+
- Spring Boot 3+
- Maven
- JUnit 5
- Mockito (for unit testing)

=================================================================

3. API ENDPOINTS
-----------------

3.1 Initialize Probe
---------------------
- URL: /api/probe/initialize
- Method: POST
- Request Body (JSON):
  {
    "x": 2,
    "y": 2,
    "direction": "NORTH"
  }
- Response:
  "Probe initialized at (2,2) facing NORTH"

------------------------------------------------------------

3.2 Move Probe
--------------
- URL: /api/probe/move
- Method: POST
- Request Body (JSON):
  {
    "commands": "FFRFF"
  }
- Response:
  "(4,0) facing EAST"

- Command List:
  - F → Move forward
  - B → Move backward
  - L → Turn left
  - R → Turn right

------------------------------------------------------------

3.3 Get Visited Coordinates
---------------------------
- URL: /api/probe/visited
- Method: GET
- Response:
  ["2,2", "2,1", "2,0", "3,0", "4,0"]

=================================================================

4. SETUP & RUN INSTRUCTIONS
---------------------------

4.1 Prerequisites
-----------------
Ensure you have installed:
- Java 17+
- Maven (to build and run the project)

4.2 Build & Run
---------------
Run the following commands:
$ mvn clean install
$ mvn spring-boot:run

Once started, the API will be available at:
http://localhost:8080

4.3 Running Tests
-----------------
To run unit tests, execute:
$ mvn test

=================================================================

5. DESIGN CONSIDERATIONS
-------------------------
✅ Clean Architecture - Follows domain-driven design principles
✅ Single Responsibility Principle (SRP) - Separate concerns for maintainability
✅ Encapsulation - Grid, Probe, and Movement logic are independent
✅ Test-Driven Development (TDD) - Unit tests ensure correctness
✅ Scalability - Can extend with additional sensors or commands

=================================================================

6. AUTHOR & CONTACT
-------------------
🚀 Name: Ganesh Prasad Rai
📧 Email: tech.gpr@gmail.com
💻 GitHub: https://github.com/techprograms/ocean-probe

=================================================================
