# **Product Item Manager (paams-d-products-manageItems)**

[![Java](https://img.shields.io/badge/Java-21-skyblue)](https://www.oracle.com/java/)
 &emsp;
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-lightgreen)](https://spring.io/projects/spring-boot)
 &emsp;
[![Maven](https://img.shields.io/badge/Maven-3.9.9-gold)](https://maven.apache.org/)


## **Description**
The **paams-d-products-manageItems** microservice is responsible for managing product data, including creating, retrieving, and deleting product records in the database. This service provides a RESTful API designed for scalability and easy integration into a microservices architecture.


## **Features**
- **Create Product Data**: Register new products in the database.
- **Retrieve Product Data**: Fetch specific product details or filtered product lists.
- **Delete Product Data**: Permanently remove product records from the database.


## **API Endpoints**

- **POST - /api/products/new/data**  
  `product-new-item`: Responsible for creating new product records in the database.
  
- **GET - /api/products/retrieve/filter**  
  `retrieve-filtered-items`: Retrieves a filtered list of product data from the database.
  
- **GET - /api/products/retrieve/data**  
  `retrieve-product-item`: Retrieves specific product data from the database.
  
- **DELETE - /api/products/delete/data**  
  `product-delete-item`: Permanently deletes product records from the database.

## **API Documentation**
This project includes API documentation using swagger. Access the documentation by running it and navigating to:
```bash
http://localhost:8080/swagger-ui/index.html
```

## **Requirements**
Before running this project, ensure the following dependencies are installed:


- **Java 21**: [Download Java](https://www.oracle.com/java/technologies/downloads/#java21l)
- **Maven 3.9.9**: [Download Maven](https://maven.apache.org/download.cgi)
- **MongoDB**: [Download MongoDB](https://www.mongodb.com/try/download/community)


## **Installation**

1. Clone this repository:
    ```bash
    git clone https://github.com/HiramSanchez/paams-d-user-manageData.git
    cd paams-d-products-manageItems
    ```

2. Configure the `application.properties` file with your database credentials:
    ```properties
    spring.datasource.url=jdbc:mongodb://localhost:27017/your_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Build and run the application using Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```  

## **Contact**
For any questions or suggestions, feel free to contact me at:  
hiramsanchez.dev@gmail.com  
