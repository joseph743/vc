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
      <li  class="active"   id="account"><a href="account.jsp">Account</a></li>
      <li data-bind="visible: ptypeVisible" id="ptype"><a href="ptype.jsp">Product Type</a></li>
      <li data-bind="visible: freeptypeVisible" id="freeptype"><a href="freeptype.jsp">Free Product Type</a></li>
      <li data-bind="visible: whsVisible" id="whs"><a href="whs.jsp">Whs</a></li>
      <li data-bind="visible: productsVisible" id="products"><a href="products.jsp">Products</a></li>
    </ul>
  </div>
</nav>
<div class="container">
<div class="page-header">
    <h2>Account Information</h2>      
  </div>
      <div class="row">
      <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
       <br>
      </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
   
        <div data-bind='foreach: Info'>
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title" data-bind='text: name'> </h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center">
                 <img alt="User Pic" src="images/avatar.png" class="img-circle img-responsive"> </div>
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Role:</td>
                        <td data-bind = 'text: role_name'></td>
                      </tr>
                      <tr>
                        <td>Mobile</td>
                        <td data-bind = 'text: mobile'></td>
                      </tr>
                      <tr>
                        <td>Phone</td>
                        <td data-bind = 'text: phone'></td>
                      </tr>
                        <tr>
                        <td>Fax</td>
                        <td data-bind = 'text: fax'></td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td><a data-bind="text: email, 
                              attr: {href: 'mailto:'+ email}" /></a></td>
                      </tr>
                      <tr>
                        <td>City</td>
                        <td data-bind= 'text: city'>
                        </td>
                           
                      </tr>
                      <tr>
                        <td>Address</td>
                        <td data-bind= 'text: address'>
                        </td>
                           
                      </tr>
                     
                    </tbody>
                  </table>
                  
                  <a href="index.jsp" class="btn btn-danger">Logout</a>
                </div>
              </div>
            </div>
          </div>
        </div>
          </div>
        </div>
      </div>
</body>
 <script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="js/knockout-3.4.0.js"></script>
 <script type="text/javascript" src="js/account.js"></script>
</html>