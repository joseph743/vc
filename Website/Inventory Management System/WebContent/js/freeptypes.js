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

var FreePTypeModel = function(FreePTypes) {
    var self = this;
    self.FreePTypes = ko.observableArray(FreePTypes);
 
    self.addFreePType = function() {
        self.FreePTypes.push({
            id: "",
            name: ""
        });
    };
  
  self.sortByName = function() {
        self.FreePTypes.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removeFreePType = function(freeptype) {
        self.FreePTypes.remove(freeptype);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.FreePTypes));
    };
};
 
var viewModel = new FreePTypeModel([
    {
      "id": 1,
      "name": "Sample"
    },
    {
      "id": 2,
      "name": "Damaged"
    },
    {
      "id": 3,
      "name": "Consumable"
    }
]);
ko.applyBindings(viewModel);
 
// // Activate jQuery Validation
// $("form").validate({ submitHandler: viewModel.save });

