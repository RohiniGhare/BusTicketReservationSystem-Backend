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

    <title>Feedback</title>
</head>
<body>

  <br><br>


<div class="container mt-3" style="width:500px;">
  <h3>Give Feedback !!</h3>
  <form action="home" method="get">
    <!-- <h1>Rate Our Service</h1> -->
    <label class="form-check-label">Rate Our Service</label>
    <div class="form-check">
      <input class="form-check-input" type="radio" name="flexRadioDefault" id="5">
      <label class="form-check-label" for="5">
        5
      </label>
    </div>

    <div class="form-check">
      <input class="form-check-input" type="radio" name="flexRadioDefault" id="4">
      <label class="form-check-label" for="4">
        4
      </label>
    </div>

    <div class="form-check">
      <input class="form-check-input" type="radio" name="flexRadioDefault" id="3">
      <label class="form-check-label" for="3">
        3
      </label>
    </div>

    <div class="form-check">
      <input class="form-check-input" type="radio" name="flexRadioDefault" id="2">
      <label class="form-check-label" for="2">
        2
      </label>
    </div>

    <div class="form-check">
      <input class="form-check-input" type="radio" name="flexRadioDefault" id="1">
      <label class="form-check-label" for="1">
        1
      </label>
    </div>

    <div class="form-floating">
      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
      <label for="floatingTextarea">Comments</label>
    </div>

    <!-- <input type="submit" class="btn btn-primary" onclick="return giveFeedback()" /> -->
    <input type="submit" class="btn btn-primary" value="Proceed" onclick="giveFeedback()">
    <!-- Submit</button> -->
  </form>


</div>

<script>
  function giveFeedback(){
    alert("Your feedback was submitted successfully!");
    /* window.location.href='ticket-booking'; */
    return true;
  }
</script>


<!-- 
<div class="Rate">
<input type="radio" id="star5" name="rate" value="5" />
<label for="star5" title="text">5 stars</label>
<input type="radio" id="star4" name="rate" value="4" />
<label for="star4" title="text">4 stars</label>
<input type="radio" id="star3" name="rate" value="3" />
<label for="star3" title="text">3 stars</label>
<input type="radio" id="star2" name="rate" value="2" />
<label for="star2" title="text">2 stars</label>
<input type="radio" id="star1" name="rate" value="1" />
<label for="star1" title="text">1 star</label>
        </div>
         <div class="textarea">
        <textarea cols="30" placeholder="Describe your experience.."></textarea>
      </div>
      

<button onclick="myFunction()">post</button>
<p id="demo"></p>
<script>

  function myFunction() {
    document.getElementById("demo").innerHTML = "Thanks for feedback";
  }
</script> -->
</body>

</html>



