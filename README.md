# Reimbursment-System

## Project Description

Employee Reimbursment System that starts you off in a login page where you can either log in as an employee or manager. 
If you login correctly it will take you the landing page of either the employee or manager. Employee page shows all
reimbursments employee has requested and whether they are pending, approved, or denied. Employees are able to add new
reimbursments and have to wait for manager approval. In the manager landing page you are able to filter by reimbursment
type (Pending, Approved, Denied) and you can approve/deny reimbursment requests. With either employee or manager you are able
to logout and it will take you back to the login page.

## Technologies Used

* Servlets
* Vanilla JS
* Bootstrap
* PostgreSQL

## Features

List of features ready and TODOs for future development
* Minimalistic table design that shows all reimbursment information for both employee and manager
* User validation where a bad login will take you to a bad login page
* Timestamp is automatically generated without user having to input it

To-do list:
* Add images for receipts

## Getting Started
   
* git clone https://github.com/daviles98/Reimbursment-System.git
* Set up with a test H2 database

## Usage

> Login to employee. Add a reimbursment, type should be pending. Log out of user and now log into a manager account. Click filter button
and filter by pending. Approve/Deny the reimbursment you had just made with the employee account. Then, log back into the employee account
and the reimbursment type should have changed.

## Contributors

> @daviles98
