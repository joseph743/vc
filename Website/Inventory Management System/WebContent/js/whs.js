"use strict";
var enableAdd = ko.observable(true);
var enableDelete = ko.observable(true);
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
     enableDelete(false);
     whsVisible(true);
    enableAdd(false);
    productsVisible(false);
    brandsVisible(false);
    driversVisible(false);
    catsVisible(false);
    ptypeVisible(false);
    freeptypeVisible(false);
  }  if (localStorage.getItem("role") == 'driver'){
     enableDelete(false);
    enableAdd(false);
    whsVisible(true);
    productsVisible(false);
    brandsVisible(false);
    driversVisible(true);
    catsVisible(false);
    ptypeVisible(false);
    freeptypeVisible(false);
  }if (localStorage.getItem("role") == 'inv'){
     enableDelete(true);
    enableAdd(true);
    whsVisible(true);
    productsVisible(true);
    brandsVisible(true);
    driversVisible(true);
    catsVisible(true);
    ptypeVisible(true);
    freeptypeVisible(true);
  }
});

var Whs = function(Whs) {
    var self = this;
    self.Whs = ko.observableArray(Whs);
 
    self.addWhs = function() {
        self.Whs.push({
            id: "",
            name: "",
            description: "",
            address: "",
            city: "",
            latt: "",
            long: ""
        });
    };
  
  self.sortByName = function() {
        self.FreePTypes.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removeWhs = function(Whs) {
        self.Whs.remove(Whs);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.Whs));
    };
};
 
var viewModel = new Whs([
   {
      "id": 0,
      "name": "Warehouse A",
      "description": "Warehouse A",
      "address": "Dewkeneh",
      "city": "Beirut",
      "latt": "0.12121",
      "long": "1.12121"
    },
    {
      "id": 1,
      "name": "Warehouse B",
      "description": "Warehouse B",
      "address": "Mkalles",
      "city": "Beirut",
      "latt": "0.121",
      "long": "1.0911"
    }
]);

ko.applyBindings(viewModel);
 
