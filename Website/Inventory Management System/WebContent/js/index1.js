"use strict";
    var viewModel = {
        Username: ko.observable(''),
        Password: ko.observable(''),
        login: function(){
            window.location.href = 'mainpage.jsp';
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