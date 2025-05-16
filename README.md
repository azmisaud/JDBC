# JDBC Demo Project

## Overview
This project demonstrates various JDBC (Java Database Connectivity) operations using MySQL. It includes examples of connecting to a database, creating tables, inserting, updating, deleting, and retrieving data, as well as batch processing and transaction handling.

## Project Structure
- **src/**: Contains all Java source files.
  - **DBConfig.java**: Configuration file for database connection details.
  - **Problem1.java**: Demonstrates connecting to a database and displaying a success message.
  - **Problem2.java**: Creates a students table with id, name, and age.
  - **Problem3.java**: Inserts 10 employees into an employees table using batch execution.
  - **Problem4.java**: Allows users to search for players by name using wildcard characters.
  - **Problem5.java**: Displays column names and types of the students table using ResultSetMetaData.
  - **InsertData.java**: Inserts data into the students table.
  - **UpdateData.java**: Updates data in the students table.
  - **DeleteData.java**: Deletes data from the students table.
  - **RetrieveData.java**: Retrieves data from the students table.
  - **CallableStatementDemo**: Using CallableStatement to Execute Stored Procedures.
  - **InsertDataUsingPreparedStatement.java**: Inserts data using PreparedStatement.
  - **UpdateDataUsingPreparedStatement.java**: Updates data using PreparedStatement.
  - **DeleteDataUsingPreparedStatement.java**: Deletes data using PreparedStatement.
  - **RetrieveDataUsingPreparedStatement.java**: Retrieves data using PreparedStatement.
  - **BatchProcessingDemo.java**: Demonstrates batch processing for inserting data.
  - **BatchProcessingUsingPreparedStatement.java**: Demonstrates batch processing using PreparedStatement.
  - **TransactionHandlingDemo.java**: Demonstrates transaction handling for account transfers.

## Prerequisites
- Java Development Kit (JDK) installed.
- MySQL Server installed and running.
- MySQL JDBC Driver (e.g., `mysql-connector-java-8.0.xx.jar`) added to the project's classpath.

## Setup Instructions
1. **Database Configuration**: Ensure the database details in `DBConfig.java` are correct.
2. **Compile the Project**: Use the following command to compile all Java files:
   ```bash
   javac -cp .:path/to/mysql-connector-java-8.0.xx.jar src/*.java
   ```
3. **Run the Project**: Execute the desired Java file using:
   ```bash
   java -cp .:path/to/mysql-connector-java-8.0.xx.jar src.Problem1
   ```
   Replace `Problem1` with the desired class name.

## Usage
- Each Java file demonstrates a specific JDBC operation. Refer to the comments within each file for detailed explanations.
- Ensure the MySQL server is running and the database is accessible before executing the Java files.

> **Author** : Mohd Saud

## License
This project is licensed under the MIT License - see the LICENSE file for details.