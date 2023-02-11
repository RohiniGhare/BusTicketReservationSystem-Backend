<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Buses</title>
    
    <!-- For Date -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" rel="stylesheet">    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"> </script>    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"> </script>    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"> </script> 
  </head>
  <body>
  
  <nav class="navbar navbar-expand-lg navbar-light navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Ticket Booking</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="homepage">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="buslist">Buses</a>
        </li>
        <!-- <li class="nav-item">
          <a class="nav-link" href="ticket-booking-records">Booking Records</a>
        </li> -->
      </ul>
      <div class="mr-3 text-end" style="margin-right:30px;">
      <div class="row">
      <div class="col">
      			<form class="form-inline" action="profile/${sessionScope.adminId}" method="get"> 
		 		<button type="submit" class="btn" style="color:white;  width:120px; height:50px;">
		      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-lines-fill" viewBox="0 0 16 16">
				  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z"/>
				</svg> Profile</button>
				</form>
		   </div>
      
      		<div class="col">
	      <form class="form-inline" action="logout" method="get"> 
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
    	<h2></h2><br>
  </div>
  
  <div class="container mt-3" style="width:500px;">
	  	<form:form action="addNewBus" method="post" modelAttribute="bus">
	  	
	  	<legend><h2>Bus Details</h2></legend>
	  	<div class="form-group">
		    <label for="name">Name</label>
		    <form:input type="text" class="form-control" id="name" placeholder="Enter name" path="busName" required="required"/>
		    <p style="color:red;"><form:errors path="busName"></form:errors></p>
		</div>
		<br>
	
		<div class="form-group">
		    <label for="seatNo">Total seats</label>
		    <form:input type="number" class="form-control" id="seatNo" placeholder="Enter Total number of seats" path="total_seats" required="required"/>
		    <p style="color:red;"><form:errors path="total_seats"></form:errors></p>
		</div>
		<br>
		
 		<div class="form-group">
		    <label for="start-Point">Starting location</label>
		    <form:input type="text" class="form-control" id="start-Point" placeholder="Enter start point" path="startPoint" required="required"/>
		    <p style="color:red;"><form:errors path="startPoint"></form:errors></p>
		</div>
		<br>
				
		<div class="form-group">
		    <label for="end-point">Ending location</label>
		    <form:input type="text" class="form-control" id="end-point" placeholder="Enter end point" path="endPoint" required="required"/>
		    <p style="color:red;"><form:errors path="endPoint"></form:errors></p>
		</div>
		<br>
				 
		<div class="form-group">
		    <label for="price">Price Per seat</label>
		    <form:input type="text" class="form-control" id="price" placeholder="Enter price" path="pricePerSeat" required="required"/>
		    <p style="color:red;"><form:errors path="pricePerSeat"></form:errors></p>
		</div>
		<br>
		
		<%-- <div class="form-group">
    					<label for="date">Date</label>
    					<form:input type="text" class="date form-control" id="date" placeholder="Enter date" path="date" required="required"/>
    					<p style="color:red;"><form:errors path="date"></form:errors></p>
    				</div> --%>
				  
		  <form:button type="submit" class="btn btn-primary">Submit</form:button>
		</form:form>
	</div>
	
	<script type="text/javascript">    
        $('.date').datepicker({    
           format: 'yyyy/mm/dd'  
         });    
    </script> 

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
  </body>
</html>