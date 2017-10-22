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

var pTypesModel = function(pTypes) {
    var self = this;
    self.pTypes = ko.observableArray(pTypes);
 
    self.addPType = function() {
        self.pTypes.push({
            id: "",
            name: ""
        });
    };
 	
 	self.sortByName = function() {
        self.pTypes.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removePType = function(ptype) {
        self.pTypes.remove(ptype);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.pTypes));
    };
};
 
var viewModel = new pTypesModel([
    {
      "id": 1,
      "name": "Promotion"
    },
    {
      "id": 2,
      "name": "Sample"
    },
    {
      "id": 3,
      "name": "Damaged"
    },
    {
      "id": 4,
      "name": "Consumable"
    },
    {
      "id": 5,
      "name": "Service"
    }
]);
ko.applyBindings(viewModel);
 