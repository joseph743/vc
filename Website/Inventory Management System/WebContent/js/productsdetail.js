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

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

window.onload = function ()
{
  viewModel.ID(getUrlParameter('ID'))
  viewModel.Name(getUrlParameter('Name'))
  viewModel.Description(getUrlParameter('Description'))
  viewModel.TotalQuantity(getUrlParameter('TotalQuantity'))
  viewModel.Code(getUrlParameter('Code'))
  viewModel.Cost(getUrlParameter('Cost'))
  viewModel.SalePrice(getUrlParameter('SalePrice'))
  viewModel.Weight(getUrlParameter('Weight'))
  viewModel.ProductTypeName(getUrlParameter('ProductTypeName'))
  viewModel.CategoryName(getUrlParameter('CategoryName'))
  viewModel.ExpiryDate(getUrlParameter('ExpiryDate'))
  viewModel.Barcode(getUrlParameter('Barcode'))
  viewModel.Location(getUrlParameter('Location'))
}
var viewModel={
  ID: ko.observable(),
  Name: ko.observable(),
  Description: ko.observable(),
  TotalQuantity: ko.observable(),
  Code: ko.observable(),
  Cost: ko.observable(),
  SalePrice: ko.observable(),
  Weight: ko.observable(),
  ProductTypeName: ko.observable(),
  CategoryName: ko.observable(),
  ExpiryDate: ko.observable(),
  Location: ko.observableArray(),
  Barcode: ko.observable()
}
ko.applyBindings(viewModel);