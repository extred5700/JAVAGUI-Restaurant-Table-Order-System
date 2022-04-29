# Hopium
CSIT-314 Project

1. Entity
- Type in this code on top of the screen: package Main.entity;

2. Boundary
- Type in this code on top of the screen: package Main.boundary;

3. Control
- Type in this code on top of the screen: package Main.control;



Code Files - Files in src folder

--- Main Method ---
1. HopiumRestaurant.java (Just run this, contains main method) 

--- BOUNDARY ---
/* Landing Page*/
1. StartingPage.java - Displays 2 buttons to redirect to specific actors, Staff & Customers (Status: Completed)

/* Staff Profiles */
1. StaffLoginPage.java - Display login fields for staff profiles to log in (Status: Completed)
2. UserAdminPageUI.java - Display all User Admin functions (Status: Only Logout, Create Account completed)

/* Customer */
1. CustomerLoginPage.java - Display table number field (Status: Table Number validation with controller not complete only)
2. CustomerPageUI.java - Display all Customer functions (Status: Functions not started)

--- Controller ---
1. LoginController.java - Contains function to validate the user profile login
2. AddUserController.java - Contains function to check whether the user exist in the DB

--- Entity ---
1. Customer.java
2. Staff.java
