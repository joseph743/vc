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
      <li  ><a href="mainpage.jsp">Home</a></li>
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
    <h2>Products</h2>      
  </div>
  <form action='/someServerSideHandler'>
    <p>You have <span data-bind='text: products().length'>&nbsp;</span> Products</p>
    <br/>
    <br/>
    <table data-bind='visible: products().length > 0' class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Product Type Name</th>
                <th>Total Quantity</th>
                <th />
            </tr>
        </thead>
        <tbody data-bind='foreach: products'>
            <tr>
                <td><input class='required' data-bind='value: id, uniqueName: true' readonly /></td>
                <td><input class='required number' data-bind='value: name, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: Product_type_name, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: total_quantity, uniqueName: true' /></td>
                <td>&nbsp;&nbsp;&nbsp;<a href='#' data-toggle="modal" data-target="#product_view" data-bind='click: $root.GoTo'>View More</a></td>
                <td>&nbsp;&nbsp;&nbsp;<a href='#' data-bind='click: $root.removeProduct'>Delete</a></td>
           </tr>
        </tbody>
    </table>
    <br/>
    <a class="flat-butt" data-bind='click: sortByName'>Sort By Name</a>
    <a class="flat-butt" data-bind='click: addProduct'>Add Product</a>
    <a class="flat-butt" data-bind='enable: products().length > 0' type='submit'>Submit</a>
    <div class="modal fade product_view" id="product_view">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                <h3 class="modal-title"></h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6 product_img">
                        <img src="images/item.png" class="img-responsive">
                    </div>
                    <div class="col-md-6 product_content">
                        <h4>Product ID: <span id="Productid"></span></h4>
                        <h4>Product Code: <span id="Code"></span> </h4>
                        <h4>Name: <span id="Name"></span></h4>
                        <h4>Category: <span id="Category"></span> </h4>
                        <h4>Product Type Name: <span id="Product_type_name"></span> </h4>
                        <h4>Description: <span id="description"></span> </h4>
                        <h4>Barcode: <span id="barcode"></span> </h4>
                        <h3 class="cost">Sale Price:<span id="price" class="glyphicon glyphicon-usd"></span> <span class="pre-cost">Cost: <span id="cost" class="glyphicon glyphicon-usd"></span></span></h3>
                        <h4>Weight: <span id="Weight"></span> </h4>
                        <h4>Total Quantity: <span id="TotalQty"></span> </h4>
                        <h4>Expiry Date: <span id="Expiry"></span> </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
</div>	
</body>
 <script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="js/knockout-3.4.0.js"></script>
 <script type="text/javascript" src="js/products.js"></script>
</html>