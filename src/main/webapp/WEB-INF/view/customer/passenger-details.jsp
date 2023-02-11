<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Passenger Details</title>

  </head>
  <body onload="return checkPassengerCount()">

<nav class="navbar navbar-expand-lg navbar-light navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">BTR</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home">Home</a>
        </li>
      </ul>
      <div class="mr-3 text-end" style="margin-right:30px;">
      <div class="row">
      <!-- <div class="col">
      			<form class="form-inline" action="profileCustomer/${sessionScope.customerId}" method="get"> 
		 		<button type="submit" class="btn" style="color:white;  width:120px; height:50px;">
		      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-lines-fill" viewBox="0 0 16 16">
				  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z"/>
				</svg> Profile</button>
				</form>
		   </div> -->
      
      		<div class="col">
	      <form class="form-inline" action="logoutCustomer" method="get"> 
		 		<button type="submit" class="btn" style="color:white;  width:120px; height:50px;">
		 		<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
					  <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
					  <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
					</svg> Logout</button>
	  		</form>
	  		</div>
  		</div>
  		</div>
  </div>
  </div>
</nav>
  
  <div class="mx-auto mt-5" style="width: 300px; text-align:center;">
    	<h2>Passenger Details</h2><br>
  </div>
  
  <div class="container mt-3" style="width:500px;">
  	
  	
  	<form:form action="savePassenger" method="post" modelAttribute="passenger">
		    <form:input type="hidden" path="customerId" value="${c.customerId }"/>
  		<div class="row">
	  	<div class="col">
		    <label>Name</label>
		    <form:input type="text" class="form-control" placeholder="Enter name" path="passengerName" required="required"/>
		    <p style="color:red;"><form:errors path="passengerName" cssClass="error"></form:errors></p>
		</div>
	
		<div class="col">
			 <label>Mobile Number</label>
		     <form:input type="number" class="form-control" placeholder="Enter PhoneNo" path="phoneNo" required="required"/>
	     	 <p style="color:red;"><form:errors path="phoneNo" cssClass="error"></form:errors></p>
		</div>
		</div>
		<div class="row">
		<div class="col">
		    <label>Age</label>
		    <form:input type="number" class="form-control" placeholder="Enter name" path="age" required="required"/>
		    <p style="color:red;"><form:errors path="age" cssClass="error"></form:errors></p>
		</div>
		<div class="col">
		    <label>Email address</label>
		    <form:input type="email" class="form-control"  placeholder="Enter email" path="emailId" required="required"/>
		    <p style="color:red;"><form:errors path="emailId" cssClass="error"></form:errors></p>
		</div>
		</div>
		<div class="row">
 		

		<div class="col">
		    <label for="exampleInputAddress1">Address</label>
		    <form:input type="text" class="form-control" placeholder="Enter Address" path="address" required="required"/>
		    <p style="color:red;"><form:errors path="address" cssClass="error"></form:errors></p>
		</div>
		</div>
			  <!-- <form:button type="submit" class="btn btn-primary" onclick="return checkPassengerCount()">Submit</form:button> -->
			  <input type="submit" class="btn btn-primary" value="Submit"/>
		</form:form>
		<br>

	</div>
	
	<script>
		
		function checkPassengerCount(){
			var seats = ${noOfPassenger};
			var pass = ${currentCount};
			if(seats >= (pass+1)){
				alert("Your passenger list has "+(pass+1)+" passengers./n Check for updations if needed!!");
				return false;
			}
			if(seats < (pass+1)){
				alert("Your passenger list has "+(pass+1)+" passengers./n Edit passengers to update or continue with current passengers!!");
				return false;
			}
			alert("Everything is fine!!!!");
			return false;
		}

	</script>



	
	<!-- Section -->
	
	<div class="mx-auto mt-3" style="width: 500px; text-align:center;">
    	<h2>Below are your registered Passengers for your last trip</h2>
  </div>
  
  
  <div class="container mt-3">
		<div class="row">
			<div class="col-md-12">

				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">PhoneNo</th>
							<th scope="col">Email Id</th>
							<!-- <th scope="col">Action</th> -->

						</tr>
					</thead>
					<tbody>
					<tr>
								<th scope="row">${c.customerId}</th>
								<td>${c.customerName}</td>
								<td>${c.phoneNo}</td>
								<td>${c.emailId}</td>
								<!-- <td></td> -->
		      </tr>
					
						<c:forEach items="${plist }" var="p">
							<tr>
								<th scope="row">${p.passengerId}</th>
								<td>${p.passengerName}</td>
								<td>${p.phoneNo}</td>
								<td>${p.emailId}</td>
								<!-- <td><a href="passenger-list" class="btn">
		      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
  <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
</svg>
		      edit</a>
		      
		      </td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
				<!-- <div class="row">
					<div class="col">
					<div class="position-absolute mt-3 start-30">
						<a href="passenger-list" class="btn btn-primary">Edit your passengers</a>
					</div>
					</div>
				</div> -->
				
				<div class="container px-4">
  				<div class="row gx-5">
    				<div class="col">
		  				<a href="passenger-list" class="btn btn-primary active" aria-current="page">Edit your passengers</a>
  					</div>
  					<div class="col">
		  				<!-- <a href="#" class="btn btn-primary">Link</a> -->
		  				<a href="showBill" class="btn btn-primary" onclick="return confirmAlert()">Pay</a>
		  			</div>
		  			<div class="col">
		 				<!--  <a href="#" class="btn btn-primary">Link</a> -->
		  				<a href="feedback" class="btn btn-primary">Feedback</a>
		  			</div>
		  		</div>
			</div>
			
				</div>
				</div>
				</div>
	
	
	
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>