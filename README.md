Employee Management System

roject Overview
The **Employee Management System** is a web-based application designed to manage employee data efficiently.  
It provides **role-based access** where **Admin** and **Employee** have different functionalities.

- **Admin** can manage all employee records.
- **Employee** can log in and view only their own details.

tect stack
Frontend
-HTML
-CSS 
-JS

Backend
-Java
-Spring Boot
-Spring Security
-JWT Authentication

Database
-MySQL

👨‍💼 Admin
- Admin login
- Add new employees
- View all employees
- Edit employee details
- Delete employee records
- Perform full **CRUD operations**

👩‍💻 Employee
- Employee login
- View personal profile/details only
- Cannot access other employees' data

🗄️ Database
- MySQL is used for data storage
- Tables include:
  - Admin
  - Employee

🗄️ Admin Setup (Manual Database Entry)

Admin records are **not created through the UI**.  
They must be inserted directly into the **MySQL database** using an SQL query.

for password encryption the `PasswordGenerator` file is used

How to Run the Project
Backend (Spring Boot)
1. Open the `Backend` folder in your IDE
2. Configure MySQL credentials in `application.properties`
3. Run the application
4. Backend runs on
http://localhost:8080

Frontend
1. open `Frontend` folder
2. Run `login.html` in the browser
3. Login as Admin or employee


