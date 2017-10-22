"use strict";
var enableAssign = ko.observable(false);
var brandsVisible= ko.observable(true);
var catsVisible= ko.observable(true);
var brandsVisible= ko.observable(true);
var ptypeVisible= ko.observable(true);
var freeptypeVisible= ko.observable(true);
var whsVisible= ko.observable(true);
var productsVisible = ko.observable(true);
var driversVisible = ko.observable(true);
var alert = ko.observable(false);
$(document).ready(function() {
    alert(false);
  if (localStorage.getItem("role") == 'whs'){
    enableAssign(false);
    productsVisible(false);
    brandsVisible(false);
    driversVisible(false);
    catsVisible(false);
    whsVisible(true);
    ptypeVisible(false);
    freeptypeVisible(false);
  }  if (localStorage.getItem("role") == 'driver'){
    enableAssign(true);
    productsVisible(false);
    brandsVisible(false);
    driversVisible(true);
    catsVisible(false);
    ptypeVisible(false);
    freeptypeVisible(false);
    whsVisible(false);
  }if (localStorage.getItem("role") == 'inv'){
    productsVisible(true);
    enableAssign(false);
    whsVisible(true);
    brandsVisible(true);
    driversVisible(true);
    catsVisible(true);
    ptypeVisible(true);
    freeptypeVisible(true);
  }
});

var OperationsModel = function(Operations,products) {
    var self = this;
    self.Operations = ko.observableArray(Operations);
    self.products = ko.observableArray(products);
    self.addOperation = function() {
        self.Operations.push({
            id: ""
        });
    };
 	  self.Assign = function(Operation) {
    alert(false);
      if(Operation.status != 'Pending'){
          alert(true);
          $("#driversview").modal("hide");
      }
      else{
          $("#driversview").modal("show");
      }
    }
 	self.sortByName = function() {
    alert(false);
        self.Operations.sort(function(a, b) {
            return a.type < b.type ? -1 : 1;
        });
    };

    self.removeOperation = function(Operation) {
    alert(false);
        self.Operations.remove(Operation);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.Operations));
    };
};
var drivers = ko.observableArray(
  [
     {  "id": 1,
      "name": "Jad Driver"
    },
       {  "id": 2,
      "name": "Elie Driver"
    }
  ])
var viewModel = new OperationsModel([
   {
      "id": 1,
      "type" : "external",
      "source_name": "Warhouse B",
      "source_id": 1,
      "destination_name": "Fahed",
      "destination_id": 2,
      "status": "In Transfer",
      "date": "23-8-2017",
      "products": [
        {
          "products_id": 1,
          "products_name": "Cocacola light",
          "qty": 3
        },
        {
          "products_id": 2,
          "products_name": "Fine Fluffy",
          "qty": 23
        }
      ]
    },
    {
      "id": 2,
      "source_name": "Warhouse A",
      "source_id": 1,
      "type" : "internal",
      "destination_name": "Warhouse B",
      "destination_id": 1,
      "status": "Pending",
      "date": "23-8-2017",
      "products": [
        {
          "products_id": 1,
          "products_name": "Cocacola light",
          "qty": 3
        },
        {
          "products_id": 2,
          "products_name": "Fine Fluffy",
          "qty": 23
        }
      ]
    }
]);
function getQueryString() {
  var result = {}, queryString = location.search.slice(1),
      re = /([^&=]+)=([^&]*)/g, m;

  while (m = re.exec(queryString)) {
    result[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
  }

  return result;
}

ko.applyBindings(viewModel);