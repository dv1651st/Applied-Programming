# Overview

Welcome to my login website walkthrough! 
This website will demonstrate the functionality of users being able to login, logout, register, and for superusers (admin) to access a dashboard with extra information.

It is recommended that you use a virtual environment before running the command: python manage.py runserver
This is to ensure that your development environment will be independent of the host operating system. 

Extra care was put into making the login and register forms extra pretty. Bootstrap was used, which is a popular HTML, CSS and Javascript library that works well with Django. Crispy forms, a django add-on was also used to make the forms pop out a little bit more. 

[Software Demo Video](https://youtu.be/WE2dC0X7YAo)

# Web Pages

Home page:
* The homepage is simply a heading with a link below it asking the user if they want to login. 
Login page:
* The login page contains the first form. It asks for the username and password. If the username does not exist, an error will be thrown, alerting the user that no such username exists. 
Likewise with password too, if they type it incorrectly. If they don't know or don't have login info, then they can create a new user following a link.
Register page:
* The register page is the final form. It allows the user to create an account. The password is hidden as they are typing it in. They will need to type in the same password twice 
for confirmation. They will need to follow certain rules in selecting their password. Their email is also asked. 
The Logout User page:
* Once they register, they will need to login again. If they successfully login, they will directed to the logout user page which is like the homepage, except it asks if the user wants
to logout.
* Once they logout, they will be directed to an auto-generated Django webpage confirming to the user that they have successfully logged out.
The Admin page:
* The admin page allows for super-users to see all the accounts that have been created and to view information that normal users aren't able to. This page is also auto-generated. 


# Development Environment

* Django 3.2.4
* Python 3.9
* Bootstrap v5.0
* Crisp Forms
* Vs-code 64-bit

# Useful Websites

{Make a list of websites that you found helpful in this project}
* [Bootstrap](https://getbootstrap.com/docs/5.0/getting-started/introduction/)
* [Django](https://www.djangoproject.com/)
* [django-crispy-forms](https://django-crispy-forms.readthedocs.io/en/latest/)

# Future Work

{Make a list of things that you need to fix, improve, and add in the future.}
* Figure out purpose of website
* Create and import models
* Create a home page that is visually friendly
* Allow users to upload their own information via models