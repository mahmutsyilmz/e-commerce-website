# E-Commerce Platform

This project is a comprehensive e-commerce platform that allows users to browse products, add items to their cart, place orders, and make payments. Additionally, there is an admin panel for managing products, categories, and orders.

## Features

- **User Registration and Login**: Secure user authentication with registration and login functionality.
- **Product Management**: CRUD operations for products, with different categories and payment types.
- **Order Management**: Users can place orders and track them. Admins can manage all orders and update statuses.
- **Admin Panel**: A dedicated section for administrators to manage products, categories, and orders.
- **Interceptors**: Custom interceptors like `AdminInterceptor` to secure admin routes.

## Technologies Used

- **Backend**: Spring Boot, Java
- **Frontend**: Thymeleaf, HTML, CSS
- **Database**: MySQL
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/mahmutsyilmz/e-commerce-website.git
   cd e-commerce-website

2. **Set up the database**:

   Configure your MySQL database in `application.properties`.

3. **Build the project**:

   ```bash
   mvn clean install

4. **Run the application**:

   ```bash
   mvn spring-boot:run

5. **Access the application**:

   - **User interface**: [http://localhost:8080/api/users/register]
   - **Admin panel**: [http://localhost:8080/api/admin/home]
  
## Screenshots

Here are some screenshots showcasing the main features:

- **Admin Panel**:
  ![Admin Dashboard](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/adminPanel.png)

- **Order Management**:
  ![Order Management Screen](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/ordersPanel.png)

- **Update Order Status**:
  ![Update Order Status](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/updateOrderStatus.png)  

- **Product Management**:
  ![Product Management Screen](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/productManagement.png)

- **Products**:
  ![Products Screen](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/admin-dashboard.png)

- **Add To Order**:
  ![Adding Order A Product](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/addToOrder.PNG)

- **After The Adding Order**:
  ![Product Added To Order](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/afterAddToOrder.png)

- **Pay The Order**:
  ![Process Of Pay The Order Button](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/payToOrder.png)

- **Show Past Order**:
  ![Showing Past Orders](https://github.com/mahmutsyilmz/e-commerce-website/blob/feature/thymeleaf-email-payment/images/showPastOrders.png)




