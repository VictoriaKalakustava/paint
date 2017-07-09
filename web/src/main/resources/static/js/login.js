$(function () {

  $('#login-form-link').click(function (e) {
    $('#login-form').delay(100).fadeIn(100);
    $('#register-form').fadeOut(100);
    $('#register-form-link').removeClass('active');
    $(this).addClass('active');
    e.preventDefault();
  });

  $('#register-form-link').click(function (e) {
    $('#register-form').delay(100).fadeIn(100);
    $('#login-form').fadeOut(100);
    $('#login-form-link').removeClass('active');
    $(this).addClass('active');
    e.preventDefault();
  });

});

function checkPasswordsEquality() {
  if ($('#signup-password').val() !== $('#signup-confirm-password').val()) {
    setActionMessage('Passwords not equals!', true);
    return false;
  } else {
    return true;
  }
}

function getCredits() {
  var tempCredits = {};
  tempCredits.name = $('#signup-name').val();
  tempCredits.surname = $('#signup-surname').val();
  tempCredits.login = $('#signup-login').val();
  tempCredits.password = $('#signup-password').val();
  tempCredits.email = $('#signup-email').val();
  return tempCredits;
}

function clearForm() {
  $('#signup-name').val('');
  $('#signup-surname').val('');
  $('#signup-login').val('');
  $('#signup-password').val('');
  $('#signup-confirm-password').val('');
  $('#signup-email').val('');
}

function createUser(data) {
  var statusCode = 0;
  $.ajax({
    type: 'POST',
    url: '/sign-up',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(data),
    headers: {
      'X-Requested-With': 'XMLHttpRequest'
    },
    dataType: 'json',
    error: function (xhr) {
      statusCode = xhr.status;
    }
  }).done(function () {
    setActionMessage('Account successfully created! Activation link sent on your email.', false);
    clearForm();
  }).fail(function () {
    if(statusCode === 409) {
      setActionMessage('Login already exists!', true)
    } else {
      setActionMessage('Server exception. Error code: ' + statusCode, true)
    }
  });
  setActionMessage('Email sending. Please wait.', false)
}

function setActionMessage(text, isError) {
  var actionMessage = $('#action-message');
  actionMessage.text(text);
  isError === true
    ? actionMessage.css('color', 'red') : actionMessage.css('color', 'green');
}

$(document).ready(function () {
  setActionMessage('', false);
  $('#register-submit').click(function () {
    setActionMessage('', false);
    if (checkPasswordsEquality() === false) {
      return;
    }
    createUser(getCredits());
  });
});

