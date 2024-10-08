
# Life Bridge: Integrated Blood Funds and Equipment Management System

**Life Bridge** is a full-stack Java web application that provides a platform for managing blood inventory, blood requests, equipment inventory, and donations. The system allows users, hospitals, and administrators to interact with blood and equipment inventory, track requests, and manage donations. It also includes an email notification system to keep relevant parties informed about important actions like new inventory additions, updates, and deletions.

## Features

- **Blood Inventory Management**:
  - Add, update, delete, and list blood inventory.
  - Track available units by blood type.
  - Email notifications for key actions (add, update, delete).
  
- **Blood Request Management**:
  - Users can create and manage blood requests.
  - Hospitals or other users can accept blood requests.
  - View history of accepted requests.
  - Email notifications for blood request creation and acceptance.
  
- **Equipment Inventory Management**:
  - Add, update, delete, and list equipment.
  - Track equipment inventory by name, purchase date, and available units.
  - Email notifications for equipment actions (add, update, delete).

- **Donations**:
  - Users and hospitals can manage blood donations and view donation history.

## Technology Stack

- **Backend**:
  - Java with Spring Boot framework
  - RESTful APIs
  - MySQL database for data persistence
  - Hibernate for ORM
  - Spring Data JPA for database interaction
  - Jakarta Servlet for session management
  - Email service integration using ZeptoMail API

- **Frontend**:
  - Thymeleaf for server-side rendering
  - Bootstrap for responsive UI design

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/maddydevgits/life-bridge.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd life-bridge
   ```

3. **Set up the MySQL database**:
   - Create a MySQL database for the project.
   - Update the `application.properties` file with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/life_bridge
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

4. **Configure the ZeptoMail API** for email notifications:
   - Add the API key and email configurations to `application.properties`:
     ```properties
     zeptomail.api.url=https://api.zeptomail.in/v1.1/email
     zeptomail.api.key=your-zeptomail-api-key
     zeptomail.from.email=your-sender-email@example.com
     ```

5. **Build and run the project**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

6. **Access the application**:
   Open your web browser and go to:
   ```
   http://localhost:8080
   ```

## Usage

### Blood Inventory Management
- **Add Inventory**: Navigate to `/inventory/add` to add new blood inventory.
- **View Inventory**: Navigate to `/inventory/list` to view available blood inventory.
- **Update Inventory**: Navigate to `/inventory/update/{id}` to update existing blood inventory.
- **Delete Inventory**: Navigate to `/inventory/delete/{id}` to delete an inventory item.

### Blood Request Management
- **Create Request**: Navigate to `/blood-requests/create` to make a new blood request.
- **View Requests**: Navigate to `/blood-requests/list` to view all blood requests.
- **Accept Request**: Navigate to `/blood-requests/accept/{id}` to accept a blood request.

### Equipment Inventory Management
- **Add Equipment**: Navigate to `/equipment/add` to add new equipment.
- **View Equipment**: Navigate to `/equipment/list` to view the equipment inventory.
- **Update Equipment**: Navigate to `/equipment/update/{id}` to update existing equipment.
- **Delete Equipment**: Navigate to `/equipment/delete/{id}` to delete equipment.

### Donations
- **View Donations**: Navigate to `/donations/list` to view all blood donations.
- **Manage Donations**: Administrators can manage the status of donations.

## Email Notifications

- **New Blood Inventory**: Notifies admins when new blood inventory is added.
- **Blood Inventory Updates**: Notifies admins of any updates to the blood inventory.
- **Blood Request Created**: Notifies the requester and admin when a new blood request is made.
- **Blood Request Accepted**: Notifies both the requester and the accepting user/hospital.
- **Equipment Actions**: Sends notifications for equipment additions, updates, and deletions.

## Contribution

Feel free to fork the repository and make contributions. Pull requests are welcome!

## License

This project is licensed under the MIT License.
