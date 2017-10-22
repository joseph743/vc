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

var BrandsModel = function(Brands) {
    var self = this;
    self.Brands = ko.observableArray(Brands);
 
    self.addBrand = function() {
        self.Brands.push({
            id: "",
            name: "",
            reference: ""
        });
    };
 	
 	self.sortByName = function() {
        self.Brands.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removeBrand = function(Brand) {
        self.Brands.remove(Brand);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.Brands));
        // To actually transmit to server as a regular form post, write this: ko.utils.postJson($("form")[0], self.gifts);
    };
};
 
var viewModel = new BrandsModel([
    {
      "id": 1,
      "name": "Fine",
      "reference": 3306
    },
    {
      "id": 2,
      "name": "Cola",
      "reference": 5900
    },
    {
      "id": 3,
      "name": "Seven",
      "reference": 6900
    }
]);
ko.applyBindings(viewModel);