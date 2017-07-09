$(document).ready(
    function() {
        isSignUp();
        $('#facebook-signup').click(function() {
            registerFacebookUser();
            console.log("First");
        });
    }
);

var userInfo;

function isSignUp() {
    var statusCode;
    $.ajax({
        type: 'GET',
        url: '/fb-controller/is_exist',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(''),
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        },
        dataType: 'json',
        error: function (xhr) {
            statusCode = xhr.status;
        }
    }).done(function (json) {
        var action = $('#action');
        if(json.login === null) {
            userInfo = json;
            action.show();
        } else {
            fillAndSendForm(json);
        }
    }).fail(function () {

    });
}

function fillAndSendForm(json) {
    var form = $('#facebook-login-form');
    $('#facebook-username').val(json.login);
    $('#facebook-password').val(json.id);
    form.submit();
}

function registerFacebookUser() {
    var statusCode;
    console.log("register function");
    userInfo.login = $('#new-login').val();
    $.ajax({
        type: 'POST',
        url: '/fb-controller/sign-up',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userInfo),
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        },
        dataType: 'json',
        error: function (xhr) {
            statusCode = xhr.status;
        }
    }).done(function (json) {
        console.log("OK");
    }).fail(function () {
        if(statusCode === 409) {
            console.log("409");
        } else {
            console.log("Err");
        }
    });
}