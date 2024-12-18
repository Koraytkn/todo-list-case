
  

# Case: Todo List Application

  

This repository contains a **Todo List Application** developed as a solution for the SAP case study. It consists of a **backend** built with Spring Boot and a **frontend** built with React.js. Both are dockerized to ensure seamless deployment and testing.

  

## Project Structure

  

```plaintext

/

├── docker-compose.yml # Orchestrates the backend and frontend services

├── todo_list_sap_case # Backend project folder

│ ├── src # Backend source code

│ ├── pom.xml # Maven dependencies

│ ├── Dockerfile # Docker setup for the backend

├── todo_list_sap_case_frontend

│ ├── my-todo-list # Frontend project folder

│ │ ├── src # Frontend source code

│ │ ├── Dockerfile # Docker setup for the frontend

│ │ ├── package.json # npm dependencies

```

  

### Backend

  

-  **Architecture**: Spring Boot with a layered architecture:

-  `controller` for handling HTTP requests.

-  `service` for business logic.

-  `model` for domain objects.

- Singleton in-memory storage (no database or repository used).

-  **Tests**:

- Unit tests for `controller`, `service`, and `model`.

-  **Dependencies**:

- Spring Web for API development.

  

### Frontend

  

-  **Architecture**: React.js with a modular structure:

-  `pages` for main views (e.g., `TodoList`, `WelcomePage`).

-  `components` for reusable UI elements (e.g., `TaskInput`, `TaskList`).

-  `services` for API communication (`TodoService`).

-  `assets` for static files like `favicon.ico` and images.

-  **Build Tool**: Vite.js for faster development and production builds.

  

---

  

## Features

  

1.  **Backend**:

- RESTful API to manage todos.

- Exception handling with custom responses.

- Singleton in-memory storage for todo data.

2.  **Frontend**:

- Interactive UI for managing todos.

- Dynamic updates using API integration.

3.  **Dockerized Deployment**:

- Prebuilt Dockerfiles for backend and frontend services.

- Docker Compose for multi-container orchestration.

  

---

  

## Getting Started

  

### Prerequisites

- Install [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/).

  

### Build and Run

  

1. Clone the repository:

```bash

git clone https://github.com/Koraytkn/todo-list-case.git your_test_directory

cd your_test_directory

```

  

2. Build and run the Docker containers:

```bash

docker-compose up --build

```

  

3. Access the application:

-  **Frontend**: [http://localhost:3000](http://localhost:3000)

-  **Backend API**: [http://localhost:8080](http://localhost:8080/todos)

  

---

  

## Folder Structure

  

### Backend (`todo_list_sap_case`)

```plaintext

src/

├── main/

│ ├── java/com/example/todo_list_sap_case/

│ │ ├── config/ # Configuration files (e.g., CORS settings)

│ │ ├── controller/ # REST controllers

│ │ ├── exception/ # Custom exceptions and handlers

│ │ ├── mapper/ # Classes for mapping between DTOs and models

│ │ ├── model/ # Domain models

│ │ ├── service/ # Business logic

│ │ └── TodoListSapCaseApplication.java


└── test/

│ └── java/com/example/todo_list_sap_case/

│ |  ├── controller/ # Controller tests

│ |  ├── model/ # Model tests

│ |  ├── service/ # Service tests

├── pom.xml # Maven build configuration file

└── Dockerfile # Docker configuration for backend

```

  

### Frontend (`my-todo-list`)

```plaintext

src/

| ├── assets/ # Static assets (images, favicon)

| ├── components/ # Reusable UI components

| ├── pages/ # Main pages

| ├── services/ # API services

| ├── App.jsx # Root component

| ├── main.jsx # App entry point

| └── index.css # Global styles

├── Dockerfile # Docker configuration for frontend

├── vite.config.js # Vite configuration file

├── package.json # Frontend dependencies and scripts

```

  

---

  

## Technology Stack

  

-  **Backend**:

- Java 17, Spring Boot

- Maven

-  **Frontend**:

- React.js, Vite.js

- Axios (for HTTP requests)

-  **Deployment**:

- Docker, Docker Compose

  

---

  

## Notes

  

- The **logs** are printed to the console (no external log files).

- Tests are included and run during the Maven build process.

  

---

  

## Contributions

  

Feel free to fork this repository and submit pull requests to enhance the project. If you encounter any issues, please open an issue on the repository.
