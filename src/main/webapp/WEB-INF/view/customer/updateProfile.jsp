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
    	<h2>Profile</h2><br>
    </div>
    
    <div class="container mt-3" style="width:500px;">
    <form:form action="validateUpdateCustomer/${customerId}" method="post" modelAttribute="customer">
    
    	<div class="form-group">
		    <!--<label for="id">Customer Id</label>-->
    		<form:input type="hidden" class="form-control" id="id" path="customerId" value="${customer.customerId }" readonly="true" />
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="name">Name</label>
    		<form:input type="text" class="form-control" id="name" path="customerName" value="${customer.customerName }" requird="required"/>
    	</div>
    	<br>
    	    	
    	<div class="form-group">
		    <label for="phoneNo">Mobile Number</label>
    		<form:input type="number" class="form-control" id="phoneNo" path="phoneNo" value="${customer.phoneNo }"/>
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		   	<form:input type="email" class="form-control" id="exampleInputEmail1" path="emailId" value="${customer.emailId }"/>
		</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="address">Address</label>
    		<form:input type="text" class="form-control" id="address" path="address" value="${customer.address }" requird="required"/>
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <!--<label for="password">Password</label>-->
    		<form:input type="hidden" class="form-control" id="password" path="password" value="${customer.password }" readonly="true"/>
    	</div>
    	<br>
    	
    	<form:button type="submit" class="btn btn-primary">Update</form:button>
    </form:form>
    <form:form action="delete-Customer/${sessionScope.customerId}" method="post" modelAttribute="customer">
    	<form:button type="submit" class="btn btn-primary">Delete</form:button>
    </form:form>
    </div>
    
	
	
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>
    
    
    