"use strict";
var alert= ko.observable(false);

$(document).ready(function() {
            alert(false);
});
    var viewModel = {
        Username: ko.observable(''),
        Password: ko.observable(''),
        login: function(){
            alert(false);
            if (viewModel.Username() == 'admin'){
                window.location.href = 'mainpage.jsp?role=inv';
                localStorage.setItem("role", 'inv');
                localStorage.setItem("name", 'Admin');
            }
            else{
                 if (viewModel.Username() == 'driver'){
                    window.location.href = 'mainpage.jsp?role=driver';
                    localStorage.setItem("role", 'driver');
                    localStorage.setItem("name", 'Driver');
                }
                 if (viewModel.Username() == 'whs'){
                    window.location.href = 'mainpage.jsp?role=whs';
                    localStorage.setItem("role", 'whs');
                    localStorage.setItem("name", 'WHS');
                }
                else{
                    alert(true);
                }

            }
            // var User = { Username: viewModel.Username(), Password: viewModel.Password() }
            // $.ajax({
            //     url: 'http://cfl.azurewebsites.net/api/Users/ValidateUser',
            //     data: JSON.stringify(User),
            //     async: true,
            //     method: 'POST',
            //     contentType: "application/json",
            //     success: function (data) {
            //         if (data.User == '') {
            //             alert('Invalid Username/Password');
            //         }
            //         else {
            //             var mapped = $.map(data.User, function (item, index) {
            //                 if(item.role == 'Inventory Manager')
            //                     window.location.href = 'mainpage.html';
            //                 window.location.href = 'mainpage.html';

            //             });
            //         }
            //     },
            //     error: function (request, status, error) {
            //         alert('Please try again!');
            //     }

            // });
            }
    };
    viewModel.Username('Username');
    viewModel.Password('Password');
ko.applyBindings(viewModel);