<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Inventory Management System</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
  <link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/main.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='css/Roboto.css' rel='stylesheet' type='text/css'>
<!--/webfonts-->
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Glg204</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="mainpage.jsp">Home</a></li>
      <li data-bind="visible: brandsVisible" id="brands"><a href="brands.jsp">Brands</a></li>
      <li data-bind="visible: catsVisible" id="cats"><a href="categories.jsp">Categories</a></li>
      <li data-bind="visible: driversVisible" id="drivers"><a href="drivers.jsp">Drivers</a></li>
      <li id="account"><a href="account.jsp">Account</a></li>
      <li data-bind="visible: ptypeVisible" id="ptype"><a href="ptype.jsp">Product Type</a></li>
      <li data-bind="visible: freeptypeVisible" id="freeptype"><a href="freeptype.jsp">Free Product Type</a></li>
      <li class="active"  data-bind="visible: whsVisible" id="whs"><a href="whs.jsp">Whs</a></li>
      <li data-bind="visible: productsVisible" id="products"><a href="products.jsp">Products</a></li>
    </ul>
  </div>
</nav>
<div class="container">
<div class="page-header">
    <h2>Warehouses</h2>      
  </div>
  <form action='/someServerSideHandler'>
    <p>You have <span data-bind='text: Whs().length'>&nbsp;</span> Whs</p>
    <br/>
    <br/>
    <table data-bind='visible: Whs().length > 0' class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Address</th>
                <th>City</th>
                <th>Latt</th>
                <th>Long</th>
                <th />
            </tr>
        </thead>
        <tbody data-bind='foreach: Whs'>
            <tr>
                <td><input class='required' data-bind='value: id, uniqueName: true' readonly /></td>
                <td><input class='required number' data-bind='value: name, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: description, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: address, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: city, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: latt, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: long, uniqueName: true' /></td>
                <td data-bind="visible: enableDelete">&nbsp;&nbsp;&nbsp;<a href='#' data-bind='click: $root.removeWhs'>Delete</a></td>
            </tr>
        </tbody>
    </table>
    <br/>
    <a class="flat-butt" data-bind='click: sortByName'>Sort By Name</a>
    <a class="flat-butt" data-bind='click: addWhs, visible: enableAdd'>Add Whs</a>
    <a class="flat-butt" data-bind='enable: Whs().length > 0, visible: enableAdd' type='submit'>Submit</a>
</form>
</div>	
</body>
 <script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="js/knockout-3.4.0.js"></script>
 <script type="text/javascript" src="js/whs.js"></script>
</html>