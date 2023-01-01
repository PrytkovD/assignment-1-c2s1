$(document).ready(function () {
    let email = $('#email');
    let password = $('#password');
    let emailError = $('#emailError');
    let passwordError = $('#passwordError');

    noError(email, emailError);
    noError(password, passwordError);

    $('#submit').click(function (event) {
        const form = $('#sign_in_form');

        event.preventDefault();

        $.post(form.attr('action'), form.serialize(), function (data) {
            noError(email, emailError);
            noError(password, passwordError);

            if (data.success === true) {
                window.location = data.redirect;
            } else {
                noError(email, emailError);
                noError(password, passwordError);

                switch (data.errorMessage) {
                    case 'EMAIL_IS_EMPTY':
                        error(email, emailError, 'Заполните это поле');
                        break;
                    case 'PASSWORD_IS_EMPTY':
                        error(password, passwordError, 'Заполните это поле');
                        break;
                    case 'EMAIL_IS_INVALID':
                        error(email, emailError, 'Введите корректный адрес электронной почты');
                        break;
                    case 'PASSWORDS_DO_NOT_MATCH':
                        error(password, passwordError, 'Неверный пароль');
                        break;
                    case 'USER_DOES_NOT_EXIST':
                        error(email, emailError, 'Пользователь с таким адресом электронной почты не существует');
                        break;
                }
            }
        });
    });
});