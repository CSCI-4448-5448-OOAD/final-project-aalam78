[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/M_5_MKPE)
# Final Project(Project 5,6,7)

Team Members:
1. Name Arnib Alam Farooqui
2. Name Daniel Hernandez


Java Version: "21.0.1" 
MySQL. Version: "8.0.34"

Additional Comments:

# Web-based Supply Chain Management System (WBSCM)
Overview
This project focuses on the development of a web-based Supply Chain Management (SCM) system, incorporating REST APIs to enhance user interactions. The system is designed to efficiently manage various aspects of the supply chain, providing a robust platform for seamless operations. The system streamlined and automated the entire supply chain lifecycle within a company. Key objectives included easy addition and management of suppliers, comprehensive product details display, efficient addition of new products, and a smooth purchase process, individual customer cart management, and an order tracking mechanism.

# Key Features
Web Interface: The system features an intuitive web interface for user interactions, ensuring a user-friendly experience.

# REST APIs: 
Utilizing RESTful APIs, the SCM system allows for smooth communication and data exchange between different components.

# Java Implementation: 
The core logic of the SCM system is implemented in Java, leveraging the language's versatility and object-oriented principles.

# MySQL Database: 
We employ a MySQL relational database to store and organize data systematically, ensuring data integrity and optimal retrieval performance.

# Spring Boot
The system is built around the Spring Boot framework, an open-source framework simplifying the development of production-ready applications. Maven and Maven dependencies, along with Spring Boot, form the basis of the project.

# Software Architecture: 
The project demonstrates a strong emphasis on software architecture principles, resulting in a well-structured and scalable system. Detail information is present in Project 7 Write-up.

# Assumptions:
In developing the Supply Chain Management (SCM) system, we made certain assumptions to streamline the project's scope and focus. These assumptions serve as foundational elements guiding the design and implementation. It's essential for users and contributors to be aware of these assumptions for a comprehensive understanding of the system.

1. Single Payment Mode
We assume that each customer associated with the SCM system uses a single payment mode for transactions. Consequently, a "payment mode" column is added to the "customers" table to record this information.
2. RESTful API Integration
The project heavily relies on the integration of RESTful APIs for communication between different components of the SCM system. This assumes that RESTful principles are suitable for the desired communication patterns.
3. Relational Database Model
The project assumes the effectiveness of a relational database model for managing supply chain data. This choice is based on the structured nature of supply chain information, with relationships between entities such as customers, orders, and products.
4. Security Measures
Certain security measures, such as user authentication and authorization, are assumed to be handled by external systems or frameworks. The focus of this project is on functionality and data management.

# Test
ApiControllerTest.java
The ApiControllerTest class serves as a comprehensive testing suite for the functionalities of the Supply Chain Management (SCM) system. In this test scenario, various aspects of the system are rigorously evaluated. The testing process begins by clearing previous entries for a specific customer and product data, ensuring a clean state for testing. Subsequently, it covers the listing of electronic and furniture products, simulating the addition of a MacBook Pro and an Office Chair to the system. The test then proceeds to validate the process of adding items to the customer's shopping cart, followed by the execution of the checkout process. This involves verifying that the order is successfully placed and obtaining the associated order ID. Lastly, the test assesses the accuracy of retrieving the order status for the specified customer and order ID. 
Postman for API Testing:
Postman is used for testing RESTful API calls. Postman provides a convenient and efficient way to test API endpoints, ensuring their functionality and validating responses.

# Running the appliction:
To initiate the application, execute the "SCMApplication" class, which serves as the main class orchestrating the functionality. Run the "SCMApplication" class to launch the application.


