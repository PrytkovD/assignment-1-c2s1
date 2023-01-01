package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.SignUpService;
import ru.itis.prytkovd.webapp.util.MD5Hash;
import ru.itis.prytkovd.webapp.util.StringValidator;

import java.io.IOException;
import java.util.Optional;

import static ru.itis.prytkovd.webapp.util.WebPaths.*;

@WebServlet(name = "SignUpServlet", value = SIGN_UP)
public class SignUpServlet extends MyInjectableAJAXServlet {
    @Inject
    private SignUpService signUpService;

    @Override
    public void init() throws ServletException {
        super.init();
        injector.injectFields(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        request.getRequestDispatcher(SIGN_UP_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmation");

        Optional<Error> error;

        if ((error = validateName(name)).isPresent()) {
            signalError(error.get());
            return;
        }

        if ((error = validateEmail(email)).isPresent()) {
            signalError(error.get());
            return;
        }

        if ((error = validatePassword(password, confirmation)).isPresent()) {
            signalError(error.get());
            return;
        }

        String hashedPassword = MD5Hash.hash(password);

        User user = User.builder()
            .name(name)
            .email(email)
            .password(hashedPassword)
            .build();

        if (!signUpService.signUp(user)) {
            signalError(Error.EMAIL_IS_ALREADY_TAKEN);
            return;
        }

        sendRedirect(SIGN_IN);
    }

    private Optional<Error> validateName(String name) {
        if (name.isEmpty()) {
            return Optional.of(Error.NAME_IS_EMPTY);
        }

        if (!StringValidator.of(name)
            .minLength(1)
            .maxLength(256)
            .validate()) {
            return Optional.of(Error.NAME_IS_INVALID);
        }

        return Optional.empty();
    }

    public Optional<Error> validateEmail(String email) {
        if (email.isEmpty()) {
            return Optional.of(Error.EMAIL_IS_EMPTY);
        }

        if (!StringValidator.of(email)
            .matchesRegex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
            .validate()) {
            return Optional.of(Error.EMAIL_IS_INVALID);
        }

        return Optional.empty();
    }

    private Optional<Error> validatePassword(String password, String confirmation) {
        if (password.isEmpty()) {
            return Optional.of(Error.PASSWORD_IS_EMPTY);
        }

        if (!StringValidator.of(password)
            .isAlphanumeric()
            .containsUppercase()
            .containsLowercase()
            .containsDigit()
            .minLength(8)
            .maxLength(256)
            .validate()) {
            return Optional.of(Error.PASSWORD_IS_INVALID);
        }

        if (confirmation.isEmpty()) {
            return Optional.of(Error.PASSWORD_CONFIRMATION_IS_EMPTY);
        }

        if (!password.equals(confirmation)) {
            return Optional.of(Error.PASSWORDS_DO_NOT_MATCH);
        }

        return Optional.empty();
    }

    private enum Error {
        NAME_IS_EMPTY,
        EMAIL_IS_EMPTY,
        PASSWORD_IS_EMPTY,
        PASSWORD_CONFIRMATION_IS_EMPTY,
        NAME_IS_INVALID,
        EMAIL_IS_INVALID,
        PASSWORD_IS_INVALID,
        PASSWORDS_DO_NOT_MATCH,
        EMAIL_IS_ALREADY_TAKEN
    }
}
