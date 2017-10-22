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
      <li class="active" ><a href="mainpage.jsp">Home</a></li>
      <li data-bind="visible: brandsVisible" id="brands"><a href="brands.jsp">Brands</a></li>
      <li data-bind="visible: catsVisible" id="cats"><a href="categories.jsp">Categories</a></li>
      <li data-bind="visible: driversVisible" id="drivers"><a href="drivers.jsp">Drivers</a></li>
      <li id="account"><a href="account.jsp">Account</a></li>
      <li  data-bind="visible: ptypeVisible" id="ptype"><a href="ptype.jsp">Product Type</a></li>
      <li data-bind="visible: freeptypeVisible" id="freeptype"><a href="freeptype.jsp">Free Product Type</a></li>
      <li data-bind="visible: whsVisible" id="whs"><a href="whs.jsp">Whs</a></li>
      <li data-bind="visible: productsVisible" id="products"><a href="products.jsp">Products</a></li>
    </ul>
  </div>
</nav>
<div class="container"><div class="page-header">
    <h2>HomePage</h2>      
  </div>
  <form action='/someServerSideHandler'>
    <p>You have <span data-bind='text: Operations().length'>&nbsp;</span> Operation(s)</p>
    <br/>
    <div style="color: red; font-size: large;" data-bind="visible: alert" class="alert alert-danger">
      <strong>Already asigned and in transit!</strong>
    </div>
    <br/>
    <table data-bind='visible: Operations().length > 0' class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Source Name</th>
                <th>Destination</th>
                <th>Status</th>
                <th />
            </tr>
        </thead>
        <tbody data-bind='foreach: Operations'>
            <tr>
                <td><input class='required' data-bind='value: id, uniqueName: true' readonly /></td>
                <td><input class='required number' data-bind='value: type, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: source_name, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: destination_name, uniqueName: true' /></td>
                <td><input class='required number' data-bind='value: status, uniqueName: true' /></td>
                <td>&nbsp;&nbsp;&nbsp;<a href='#' data-target="#driversview" data-bind='click: $root.Assign, visible: enableAssign'>Assign</a></td>
                <td>&nbsp;&nbsp;&nbsp;<a href='#' data-bind='click: $root.removeOperation'>Hide</a></td>
                 <!--  <div data-bind="foreach: $data.products">
                    <td><input class='required number' data-bind='value: $data.qty, uniqueName: true' /></td>
                  </div> -->
             </tr>
           
        </tbody>
    </table>
    <br/>
    <a class="flat-butt" data-bind='click: sortByName'>Sort By Type</a>
     <div class="modal fade driversview" id="driversview">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                <h3 class="modal-title"></h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6 product_img">
                        <img src="images/avatar.png" class="img-responsive">
                    </div>
                    <div class="col-md-6 product_content">
                        <h3>Select a driver: </h3>
                        <select id="driverselect" class="selectpicker" data-bind="options: drivers,
                       optionsText: 'name',
                       value: 'id',
                       optionsCaption: 'Choose...'">
                        </select>
                    </div>
                    <br/>
                <div class="col-md-6">                
                    <br/>
                    <div><h3>Departure Date: </h3><input type="time" name="Departure"></div>
                    <br/>
                    <div><h3>Expected Date: </h3><input type="time" name="Expected"></div>
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
 <script type="text/javascript" src="js/mainpage.js"></script>
</html>