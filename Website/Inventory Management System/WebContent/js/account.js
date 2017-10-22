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
  viewModel.getAccount(localStorage.getItem('name'))
});
var AccountModel = function(Account) {
    var self = this;
    self.Account = ko.observableArray(Account);
    self.Info = ko.observableArray();

    self.sortByName = function() {
        self.Account.sort(function(a, b) {
            return a.name < b.name ? -1 : 1;
        });
    };
    self.getAccount = function(name) {
        var match = ko.utils.arrayFirst(self.Account(), function(item) {
             return item.name === name;
        });
        self.Info.push(match);
        };
    self.save = function(form) {
        alert("Could now transmit to server: " + ko.utils.stringifyJson(self.Operations));
    };
};
var viewModel = new AccountModel([
{
      "id": 1,
      "role_id": 1,
      "role_name": "Driver Manager",
      "name": "Driver",
      "email": "driver@isae.edu.lb",
      "mobile": "78980767",
      "phone": "04234212",
      "fax": "04234213",
      "city": "Furn Al cheback",
      "address": "Furn Al cheback , facing total station",
      "latitude": "37.8",
      "longitude": "39.0"
    },
    {
      "id": 2,
      "role_id": 2,
      "role_name": "Inventory Manager",
      "name": "Admin",
      "email": "admin@isae.edu.lb",
      "mobile": "70621560",
      "phone": "01898205",
      "fax": "0101218",
      "city": "Jdeideh",
      "address": "Facing Rebound Club",
      "latitude": "",
      "longitude": ""
    },
    {
      "id": 3,
      "role_id": 3,
      "role_name": "Warehouse Manager",
      "name": "WHS",
      "email": "whs@isae.edu.lb",
      "mobile": "70621562",
      "phone": "08201235",
      "fax": "01972789",
      "city": "Dekweneh",
      "address": "City Rama Next To Sea sweet",
      "latitude": "",
      "longitude": ""
    }
]);
ko.applyBindings(viewModel);