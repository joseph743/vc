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
    ptypeVisible(false);
    freeptypeVisible(false);
    whsVisible(true);
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
    brandsVisible(true);
    driversVisible(true);
    catsVisible(true);
    ptypeVisible(true);
    freeptypeVisible(true);
    whsVisible(true);
  }
});
var CategoriesModel = function(Categories) {
    var self = this;
    self.Categories = ko.observableArray(Categories);
 
    self.addCat = function() {
        self.Categories.push({
            id: "",
            name: "",
            reference: ""
        });
    };
 	
 	self.sortByName = function() {
        self.Categories.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };

    self.removeCat = function(cat) {
        self.Categories.remove(cat);
    };
 
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.Categories));
    };
};
 
var viewModel = new CategoriesModel([
    {
      "id": 1,
      "name": "Beverages",
      "reference": 3306
    },
    {
      "id": 2,
      "name": "Tissues",
      "reference": 5900
    }
]);
ko.applyBindings(viewModel);