$(document).ready(function () {
    let name = $('#name');
    let email = $('#email');
    let password = $('#password');
    let confirmation = $('#confirmation');

    let nameError = $('#nameError');
    let emailError = $('#emailError');
    let passwordError = $('#passwordError');
    let confirmationError = $('#confirmationError');

    noError(name, nameError);
    noError(email, emailError);
    noError(password, passwordError);
    noError(confirmation, confirmationError);

    $('#submit').click(function (event) {
        const form = $('#sign_up_form');

        event.preventDefault();

        $.post(form.attr('action'), form.serialize(), function (data) {
            noError(name, nameError);
            noError(email, emailError);
            noError(password, passwordError);
            noError(confirmation, confirmationError);

            if (data.success === true) {
                window.location = data.redirect;
            } else {
                switch (data.errorMessage) {
                    case 'NAME_IS_EMPTY':
                        error(name, nameError, 'Заполните это поле');
                        break;
                    case 'EMAIL_IS_EMPTY':
                        error(email, emailError, 'Заполните это поле');
                        break;
                    case 'PASSWORD_IS_EMPTY':
                        error(password, passwordError, 'Заполните это поле');
                        break;
                    case 'PASSWORD_CONFIRMATION_IS_EMPTY':
                        error(confirmation, confirmationError, 'Заполните это поле');
                        break;
                    case 'NAME_IS_INVALID':
                        error(name, nameError, 'Имя может содержать не более 256 символов');
                        break;
                    case 'EMAIL_IS_INVALID':
                        error(email, emailError, 'Введите корректный адрес электронной почты');
                        break;
                    case 'PASSWORD_IS_INVALID':
                        error(password, passwordError, 'Пароль должен содержать хотя бы одну строчную и одну заглавную латинскую букву и одну цифру и содержать не менее 8 символов');
                        break;
                    case 'PASSWORDS_DO_NOT_MATCH':
                        error(confirmation, confirmationError, 'Пароли не совпадают');
                        break;
                    case 'EMAIL_IS_ALREADY_TAKEN':
                        error(email, emailError, 'Пользователь с таким адресом электронной почты уже существует');
                        break;
                }
            }
        });
    });
});