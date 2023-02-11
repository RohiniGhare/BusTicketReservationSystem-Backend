<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ page isELIgnored="false"%>
<!doctype html>
<html lang="en" xmlns:th="http:www.thymeleaf.org">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Registration Page</title>
  </head>
  <body>
  
  <div class="mx-auto mt-5" style="width: 300px; text-align:center;">
    	<h2>Sign up</h2><br>
  </div>
  
  <div class="container mt-3" style="width:500px;">
	  	<form:form action="saveCustomer" method="post" modelAttribute="userForm">
	  	<div class="form-group">
		    <label for="exampleInputName">Name</label>
		    <form:input type="text" class="form-control" id="exampleInputName" placeholder="Enter name" path="customerName" required="required"/>
		    <p style="color:red;"><form:errors path="customerName"></form:errors></p>
		</div>
	
		<div class="form-group">
		    <label for="exampleInputPhoneNo">Mobile Number</label>
		    <form:input type="number" class="form-control" id="exampleInputPhoneNo" placeholder="Enter PhoneNo" path="phoneNo" required="required"/>
		    <p style="color:red;"><form:errors path="phoneNo"></form:errors></p>
		</div>
		
 		<div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <form:input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email" path="emailId" required="required"/>
		    <p style="color:red;"><form:errors path="emailId"></form:errors></p>
		</div>
		
		<div class="form-group">
		    <label for="exampleInputAddress1">Address</label>
		    <form:input type="text" class="form-control" id="exampleInputAddress1" placeholder="Enter Address" path="address" required="required"/>
		    <p style="color:red;"><form:errors path="address"></form:errors></p>
		</div>
		 
		<div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <form:input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" path="password" required="required"/>
		    <p style="color:red;"><form:errors path="password"></form:errors></p>
		</div>
		  
		  <form:button type="submit" class="btn btn-primary">Submit</form:button>
		</form:form>
		<b class="errors" style="color:red">${invalidRegister}</b>
		
		
	</div>
 
  

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>