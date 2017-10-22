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
      <li  data-bind="visible: ptypeVisible" id="ptype"><a href="ptype.jsp">Product Type</a></li>
      <li data-bind="visible: freeptypeVisible" id="freeptype"><a href="freeptype.jsp">Free Product Type</a></li>
      <li data-bind="visible: whsVisible" id="whs"><a href="whs.jsp">Whs</a></li>
      <li class="active" data-bind="visible: productsVisible" id="products"><a href="products.jsp">Products</a></li>
    </ul>
  </div>
</nav>
<div class="container">
<div class="page-header">
    <h2>Product Details</h2>      
  </div>
    ID: <label data-bind='text: ID'></label>&nbsp;&nbsp;
    Name: <label data-bind='text: Name'></label>&nbsp;&nbsp;
    Description: <label data-bind='text: Description'></label>&nbsp;&nbsp;
    Total Quantity: <label data-bind='text: TotalQuantity'></label>&nbsp;&nbsp;
    <br/>
    Code: <label data-bind='text: Code'></label>&nbsp;&nbsp;
    Cost: <label data-bind='text: Cost'></label>&nbsp;&nbsp;
    Sale Price: <label data-bind='text: SalePrice'></label>&nbsp;&nbsp;
    Weight: <label data-bind='text: Weight'></label>&nbsp;&nbsp;
    <br/>
    Product Type Name: <label data-bind='text: ProductTypeName'></label>&nbsp;&nbsp;
    Category Name: <label data-bind='text: CategoryName'></label>&nbsp;&nbsp;
    Expiry Date: <label data-bind='text: ExpiryDate'></label>&nbsp;&nbsp;
    Barcode: <label data-bind='text: Barcode'></label>&nbsp;&nbsp;
    <br/>
  </div>	
</body>
 <script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="js/knockout-3.4.0.js"></script>
 <script type="text/javascript" src="js/productsdetail.js"></script>
</html>