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

var Drivers = function(Drivers) {
    var self = this;
    self.Drivers = ko.observableArray(Drivers);
 
    self.addDrivers = function() {
        self.Drivers.push({
            id: "",
            name: ""
        });
    };
  
  self.sortByName = function() {
        self.Drivers.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removeDrivers = function(Drivers) {
        self.Drivers.remove(Drivers);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.Whs));
    };
};
 
var viewModel = new Drivers([
   {  "id": 1,
      "name": "Jad Driver"
    },
       {  "id": 2,
      "name": "Elie Driver"
    }
]);

ko.applyBindings(viewModel);
 
