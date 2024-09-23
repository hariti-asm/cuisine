# BatiCuisine Management System

BatiCuisine is a project management and estimation system designed for the construction industry. It allows users to manage clients, projects, labor, materials, and estimates, providing a comprehensive tool for handling project-related data efficiently.

## Table of Contents

1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Technologies Used](#technologies-used)
4. [Database Structure](#database-structure)
   - [Client](#client)
   - [Project](#project)
   - [Component](#component)
   - [Labor](#labor)
   - [Material](#material)
   - [Estimate](#estimate)
5. [Installation and Setup](#installation-and-setup)
6. [Usage](#usage)
7. [Future Enhancements](#future-enhancements)
8. [Contributing](#contributing)

## Overview

The BatiCuisine Management System helps construction companies manage their workflow, track project progress, and estimate the necessary resources. The platform integrates components such as clients, projects, labor, materials, and cost estimates, making it easier to handle construction-related operations in one place.

## Key Features

- **Client Management**: Add, update, delete, and search for clients.
- **Project Management**: Create and manage projects associated with clients, track progress, assign labor and materials.
- **Estimate Generation**: Generate cost estimates for projects based on the associated labor, materials, and other factors.
- **Labor Management**: Track labor resources and their costs for each project.
- **Material Management**: Manage materials used in construction projects, track availability, and associated costs.
- **Component Structure**: Shared structure for managing both labor and materials as components of a project.
- **Integration**: The system integrates with a PostgreSQL database for persistence and efficient data management.

## Technologies Used

- **Java**: Core programming language.
- **PostgreSQL**: Relational database used to store all project data.
- **JDBC**: Java Database Connectivity to connect Java application with PostgreSQL.
- **Maven**: For dependency management and project build.

## Database Structure

The system uses the following database tables:

### Client

Represents the clients associated with different projects.

| Column       | Type    | Description              |
|--------------|---------|--------------------------|
| id           | UUID    | Unique identifier         |
| name         | String  | Client's name             |
| email        | String  | Client's email            |
| phone_number | String  | Client's phone number     |

### Project

Represents the construction projects handled by the company.

| Column       | Type    | Description                     |
|--------------|---------|---------------------------------|
| id           | UUID    | Unique identifier               |
| client_id    | UUID    | Associated client ID            |
| name         | String  | Project name                    |
| start_date   | Date    | Project start date              |
| end_date     | Date    | Project end date                |
| status       | String  | Status (e.g., In Progress, Done)|

### Component

The `Component` table is the parent class for both `Labor` and `Material`. It contains fields common to both types of project resources.

| Column   | Type    | Description        |
|----------|---------|--------------------|
| id       | UUID    | Unique identifier  |
| name     | String  | component name     |
| quantity | Integer | Component quantity |
| project_id    | UUID    | Associated project ID        |
| component_type| String  | Component type (Labor/Material)|
| cost          | Double  | Cost of the component        |

### Labor

Represents labor resources used in a project, inheriting from the `Component` table.

| Column        | Type    | Description                    |
|---------------|---------|--------------------------------|
| id            | UUID    | Unique identifier (from `Component`) |
| cost_per_hour | Double  | Hourly cost                     |
| hours_worked  | Double  | Total hours worked              |

### Material

Represents the materials used in construction projects, also inheriting from the `Component` table.

| Column      | Type    | Description               |
|-------------|---------|---------------------------|
| id          | UUID    | Unique identifier (from `Component`)|
| unit_price  | Double  | Price per unit             |

### Estimate

Tracks the estimates provided to clients for each project.

| Column        | Type  | Description                       |
|---------------|-------|-----------------------------------|
| id            | UUID  | Unique identifier                 |
| project_id    | UUID  | Associated project ID             |
| issue_date    | Date  | Date of estimate creation         |
| validity_date | Date  | Date until the estimate is valid   |
| status        | Enum  | Estimate status (Pending, Approved, Rejected) |

## Installation and Setup

### Prerequisites

- JDK 8 or higher
- PostgreSQL
- Maven

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/hariti-asm/cuisine.git
