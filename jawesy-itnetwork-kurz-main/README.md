# Insurance Management System - Spring Boot Application

This project is a demonstration application built using Spring Boot, allowing users to register, create clients (policyholders), and manage their non-life insurance policies. The application is designed to demonstrate basic CRUD functionalities with user and role management using a MySQL database. **This is a demonstrative version and does not reflect real-world insurance operations.**

## Prerequisites

Before running the application, ensure you have the following software installed:

1. **IntelliJ IDEA Community Edition 2024.1.1** or a compatible Java IDE.
2. **Java JDK 19** (tested with Oracle Open JDK 19 or Amazon Corretto JDK 19).
3. **XAMPP 3.3.0** (or a compatible Apache & MySQL server).

## Setup and Run Instructions

Follow these steps to set up and run the application:

### 1. Clone the Repository
* Download or clone the source code from the repository to your local machine.

### 2. Open the Project in IntelliJ IDEA
* Open IntelliJ IDEA and import the project by selecting the folder that contains the project files.
* Ensure the project is using Java 19 SDK.

### 3. Configure the Database (XAMPP)
1. Open **XAMPP** and start the **Apache** and **MySQL** services (it may be necessary to run XAMPP as an administrator).
2. Access **phpMyAdmin** by clicking the **Admin** button next to MySQL in XAMPP.
3. The required tables for the database will be automatically created by the application using JPA and Hibernate from Spring Boot, but they will be empty.

#### 4. Configure Foreign Key Constraints (for Client Deletion)
To ensure that deleting a client also deletes their associated insurance policies and user account, you must modify the foreign key constraints in the MySQL database. Run the following SQL commands in **phpMyAdmin**:

```sql
-- Remove old foreign keys
ALTER TABLE crash_insurances DROP FOREIGN KEY FKn8jb5qncaonqbfkykfl1ely;
ALTER TABLE mandatory_insurances DROP FOREIGN KEY FK90nojnmg076tmc0u1qqj2dvna;
ALTER TABLE property_insurance DROP FOREIGN KEY FKfv2iukd3tsgknrdq9iujj1lco;
ALTER TABLE client_entity DROP FOREIGN KEY FKaym8s8tw0h6ikhewscu0lgwfk;

-- Add new foreign keys with ON DELETE CASCADE
ALTER TABLE crash_insurances 
ADD CONSTRAINT FKn8jb5qncaonqbfkykfl1ely 
FOREIGN KEY (client_id) REFERENCES client_entity(client_id) ON DELETE CASCADE;

ALTER TABLE mandatory_insurances 
ADD CONSTRAINT FK90nojnmg076tmc0u1qqj2dvna 
FOREIGN KEY (client_id) REFERENCES client_entity(client_id) ON DELETE CASCADE;

ALTER TABLE property_insurance 
ADD CONSTRAINT FKfv2iukd3tsgknrdq9iujj1lco 
FOREIGN KEY (client_id) REFERENCES client_entity(client_id) ON DELETE CASCADE;

ALTER TABLE client_entity 
ADD CONSTRAINT FKaym8s8tw0h6ikhewscu0lgwfk 
FOREIGN KEY (user_id) REFERENCES user_entity(user_id) ON DELETE CASCADE;
```

### 5. Run the Application
* In **IntelliJ IDEA**, open the class `BasicInsuranceApplication` (located in the `Insurance` package).
* Right-click on the class and select **Run**. The embedded **Tomcat** server will start automatically.

### 6. Access the Application
* Once the application is running, open your web browser and go to `http://localhost:8080`.
* You should now be able to interact with the application.

## Application Features

### 1. User Registration
* You can register a new user by clicking on the **More Info** section at the bottom of the homepage or by selecting **My Profile** in the top-right corner of the navigation menu.
* After registering, you will be redirected to the **Create Client** page.

### 2. Creating a Client
* On the **Create Client** page, provide the required client information. The data is fictional, but it should be relevant for demonstration purposes.
* After successfully creating a client, you will be redirected to the **Client Profile** page.

### 3. Managing Insurance Policies
* On the **Client Profile** page, you can choose to add one of three non-life insurance policies:
   1. **Crash Insurance**
   2. **Mandatory Liability Insurance**
   3. **Property Insurance**
* Each policy can be individually edited or deleted.
* You can also edit client details, except for the email address.

### 4. Administrator Role and Managing Clients
To delete a client, you must have **administrator privileges**, which allow access to the full client list. Administrator privileges can be granted using **phpMyAdmin**:

1. Open **XAMPP** and click the **Admin** button next to MySQL to open phpMyAdmin.
2. In the right-hand menu, select the **blog** database and then the **user_entity** table.
3. Find the user (the user must already have a client associated with them) and click **Edit**.
4. Change the **admin** field value from `0` to `1`.
5. Log out and log back in with the updated user credentials to activate the administrator privileges.

### 5. Deleting Clients
* Once logged in as an administrator, a **Client List** button will appear, allowing access to all registered clients.
* Administrators can delete clients from this list, which will also delete their associated insurance policies and user accounts.

> **Warning:** It is not recommended to delete the currently logged-in administrator from the client list. This situation is not handled and may lead to loss of administrative privileges.

## Important Notes
This is a **demonstrative version** of the application. It does not reflect real-world insurance functionalities.
