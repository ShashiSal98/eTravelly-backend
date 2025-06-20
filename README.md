# eTravelly-backend
Hotel booking backend system using Java Spring Boot – APIs 

## Features

- Manage hotels and their contracts
- Manage room types linked to contracts
- Search for available rooms based on date and occupancy
- CRUD operations for hotels, contracts, and room types

## Technologies Used

- Java with Spring Boot
- REST API
- Maven or Gradle (your build tool)
- MySQL or other relational database

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven or Gradle
- MySQL or any configured database
- IDE such as IntelliJ IDEA or VS Code

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/ShashiSal98/eTravelly-backend.git
   cd eTravelly-backend
   
2. Configure your database settings in src/main/resources/application.properties or application.yml.

3. Build and run the application:

   ```bash
   ./mvnw spring-boot:run 
   #or
   ./gradlew bootRun
   
4. The backend server will start at http://localhost:8080


## API Endpoints

/hotels - Manage hotels (GET, POST, PUT, DELETE)

/contracts - Manage contracts (GET, POST, DELETE)

/room-types - Manage room types (GET, POST, DELETE)

/search - Search available rooms (POST)


## License
This project is licensed under the MIT License.

Created by Shashi Salwathura
