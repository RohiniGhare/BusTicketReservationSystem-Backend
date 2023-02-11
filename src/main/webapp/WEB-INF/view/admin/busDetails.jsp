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

    <title>Bus Details</title>
  </head>
  <body>
 
	<div class="mx-auto mt-5" style="width: 300px; text-align:center;">
    	<h2>Details</h2><br>
    </div>
    
    <div class="container mt-3" style="width:500px;">
    <form:form action="validateUpdateBus/${sessionScope.busId}" method="post" modelAttribute="bus">
    
    <%-- ${admin }<br> --%>
    
    	<%-- <form:input type="number" path="adminID" value="${admin.adminId }" readonly="true"/> --%>
    	
    	<div class="form-group">
		    <!-- <label for="id">Bus Id</label> -->
    		<form:input type="hidden" class="form-control" id="id" path="busId" value="${bus.busId }" readonly="true"/>
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="name">Name</label>
    		<form:input type="text" class="form-control" id="name" path="busName" value="${bus.busName }" requird="required"/>
    	</div>
    	<br>
    	    	
    	<div class="form-group">
		    <label for="totalseats">Capacity</label>
    		<form:input type="number" class="form-control" id="totalseats" path="total_seats" value="${bus.total_seats }" requird="required"/>
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="startPoint">Start Point</label>
		   	<form:input type="text" class="form-control" id="startPoint" path="startPoint" value="${bus.startPoint }" requird="required"/>
		</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="endPoint">End Point</label>
    		<form:input type="text" class="form-control" id="endPoint" path="endPoint" value="${bus.endPoint }" requird="required"/>
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="pricePerSeat">Price Per Seat</label>
    		<form:input type="number" class="form-control" id="pricePerSeat" path="pricePerSeat" value="${bus.pricePerSeat }" requird="required"/>
    	</div>
    	<br>
    	
    	<div class="form-group">
		    <label for="datet">Price Per Seat</label>
    		<form:input type="number" class="form-control" id="pricePerSeat" path="pricePerSeat" value="${bus.pricePerSeat }" requird="required"/>
    	</div>
    	
    	<form:button type="submit" class="btn btn-primary">Update</form:button>
    </form:form>
    <form:form action="deleteBus/${sessionScope.busId}" method="post" modelAttribute="bus">
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