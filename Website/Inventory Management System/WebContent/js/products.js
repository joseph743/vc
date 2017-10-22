"use strict";
var brandsVisible= ko.observable(true);
var catsVisible= ko.observable(true);
var brandsVisible= ko.observable(true);
var ptypeVisible= ko.observable(true);
var freeptypeVisible= ko.observable(true);
var whsVisible= ko.observable(true);
var productsVisible = ko.observable(true);
var driversVisible = ko.observable(true);
$(document).ready(function() {
  if (localStorage.getItem("role") == 'whs'){
    productsVisible(false);
    brandsVisible(false);
    driversVisible(false);
    catsVisible(false);
    whsVisible(true);
    ptypeVisible(false);
    freeptypeVisible(false);
  }  if (localStorage.getItem("role") == 'driver'){
    productsVisible(false);
    brandsVisible(false);
    driversVisible(true);
    catsVisible(false);
    ptypeVisible(false);
    freeptypeVisible(false);
    whsVisible(false);
  }if (localStorage.getItem("role") == 'inv'){
    productsVisible(true);
    whsVisible(true);
    brandsVisible(true);
    driversVisible(true);
    catsVisible(true);
    ptypeVisible(true);
    freeptypeVisible(true);
  }
});

var products = function(products) {
    var self = this;
    self.products = ko.observableArray(products);
 
    self.addProduct = function() {
        self.products.push({
            id: "",
            name: "",
            Product_type_name: "",
            total_quantity: 0
        });
    };
  
  self.sortByName = function() {
        self.products.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removeProduct = function(products) {
        self.products.remove(products);
    };
    
    self.GoTo = function(products) {
      if (products.id == '')
        return;
      if (products.id == null)
        return;
      if (products.id == undefined)
        return;
      $('#product_view .modal-title').html(products.name);
      $('#product_view #Productid').html(products.id);
      $('#product_view #Category').html(products.category_name);
      $('#product_view #Name').html(products.name);
      $('#product_view #description').html(products.description);
      $('#product_view #price').html(products.sale_price);
      $('#product_view #cost').html(products.cost);
      $('#product_view #Weight').html(products.weight);
      $('#product_view #Expiry').html(products.expiry_date);
      $('#product_view #barcode').html(products.barcode);
      $('#product_view #Code').html(products.code);
      $('#product_view #TotalQty').html(products.total_quantity);
      $('#product_view #Product_type_name').html(products.Product_type_name);
      // var s = "?ID=" + products.id + "&Name=" + products.name + "&Description=" + products.description
      // s += "&TotalQuantity=" + products.total_quantity + "&Code=" + products.code + "&Cost=" + products.cost
      // s += "&CategoryName=" + products.category_name + "&ExpiryDate=" + products.expiry_date + "&Barcode=" + products.barcode
      // s += "&SalePrice=" + products.sale_price + "&Weight=" + products.weight + "&ProductTypeName=" + products.Product_type_name
      // //s += "&Location=" + JSON.stringify(products.location)
      // location.href = "productdetail.html" + s;
    };

    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.products));
    };
};
 
var viewModel = new products([
  {
      "id": 1,
      "name": "Cocacola light",
      "total_quantity": 2000,
      "code": "330672",
      "cost": 0.2,
      "sale_price": 0.5,
      "weight": "185ml",
      "Product_type_name": "service",
      "Product_type_id": 5,
      "category_name": "Beverages",
      "category_id": 1,
      "created_date": "27-3-2015",
      "expiry_date": "27-3-2018",
      "brand_name": "Cola",
      "brand_id": 1,
      "brand_reference": 3306,
      "description": "it is a beverage refreshing with 0 calories",
      "barcode": "5449000185525",
      "location": [
        {
          "corridor": "c2",
          "shelf": "d",
          "height": "4",
          "warehouse_name": "Warehouse A",
          "quantity": 750
        },
        {
          "corridor": "e7",
          "shelf": "h",
          "height": "4",
          "warehouse_name": "Warehouse B",
          "quantity": 1250
        }
      ]
    },
    {
      "id": 2,
      "name": "Fine Fluffy",
      "total_quantity": 3500,
      "code": "590062",
      "cost": 0.3,
      "sale_price": 1,
      "created_date": "27-3-2018",
      "expiry_date": "27-3-2020",
      "weight": "200 tissue",
      "Product_type_name": "service",
      "Product_type_id": 5,
      "category_name": "Tissues",
      "category_id": 3,
      "brand_name": "Fine",
      "brand_id": 2,
      "brand_reference": 5900,
      "description": "200 white soft sterilsed tissues",
      "barcode" : "6251001211634",
      "location": [
        {
          "corridor": "c2",
          "shelf": "d",
          "height": "1",
          "warehouse_name": "Warehouse A",
          "quantity": 1000
        },
        {
          "corridor": "e7",
          "shelf": "l",
          "height": "4",
          "warehouse_name": "Warehouse B",
          "quantity": 2500
        }
      ]
    },{
      "id": 3,
      "name": "Crush",
      "total_quantity": 100,
      "code": "330677F",
      "cost": 0.4,
      "sale_price": "-",
      "weight": "335ml",
      "Product_type_name": "Sample",
      "Product_type_id": 2,
      "category_name": "Beverages",
      "category_id": 1,
      "created_date": "27-3-2013",
      "expiry_date": "27-3-2019",
      "brand_name": "Cola",
      "brand_id": 1,
      "brand_reference": 3306,
      "description": "Sample For the client",
      "barcode": "A12345678Aasd",
      "location": [
        {
          "corridor": "c2",
          "shelf": "d",
          "height": "5",
          "warehouse_name": "Warehouse A",
          "quantity": 50
        },
        {
          "corridor": "e7",
          "shelf": "h",
          "height": "5",
          "warehouse_name": "Warehouse B",
          "quantity": 50
        }
      ]
    },{
      "id": 4,
      "name": "Easter Promotion",
      "total_quantity": 200,
      "code": "330677P",
      "cost": 20,
      "old_price" : "60",
      "discount" : "50",
      "sale_price": "30",
      "weight": "-",
      "Product_type_name": "Promotion",
      "Product_type_id": 1,
      "category_name": "-",
      "category_id": 0,
      "created_date": "27-3-2013",
      "expiry_date": "27-3-2019",
      "brand_name": "Cola",
      "brand_id": 1,
      "brand_reference": 3306,
      "description": "Easter Promotion : 2 Cola 330 ml + 2 Crush 330ml discounted 50%",
      "barcode": "A12345678Aasd",
      "location": [
        {
          "corridor": "c2",
          "shelf": "d",
          "height": "5",
          "warehouse_name": "Warehouse A",
          "quantity": 100
        },
        {
          "corridor": "e7",
          "shelf": "h",
          "height": "5",
          "warehouse_name": "Warehouse B",
          "quantity": 100
        }
      ]
    },{
      "id": 5,
      "name": "Christmas promotion",
      "total_quantity": 200,
      "code": "330677P",
      "cost": 20,
      "old_price" : "60",
      "sale_price": "30",
      "weight": "-",
      "Product_type_name": "Promotion",
      "Product_type_id": 1,
      "category_name": "-",
      "category_id": 0,
      "created_date": "27-3-2013",
      "expiry_date": "27-3-2019",
      "brand_name": "Cola",
      "brand_id": 1,
      "brand_reference": 3306,
      "description": "Christmas Promotion : 2 Cola 330 ml + 2 Free Crush 330ml ",
      "barcode": "A12345678A",
      "location": [
        {
          "corridor": "c2",
          "shelf": "d",
          "height": "5",
          "warehouse_name": "Warehouse A",
          "quantity": 100
        },
        {
          "corridor": "e7",
          "shelf": "h",
          "height": "5",
          "warehouse_name": "Warehouse B",
          "quantity": 100
        }
      ]
    }
]);
ko.applyBindings(viewModel);
 
// // Activate jQuery Validation
// $("form").validate({ submitHandler: viewModel.save });

