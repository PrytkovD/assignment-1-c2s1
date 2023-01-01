package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.UserService;
import ru.itis.prytkovd.webapp.util.MD5Hash;
import ru.itis.prytkovd.webapp.util.StringValidator;

import java.io.IOException;
import java.util.Optional;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.*;

@WebServlet(name = "SignInServlet", value = SIGN_IN)
public class SignInServlet extends MyInjectableAJAXServlet {
    @Inject
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        injector.injectFields(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        request.getRequestDispatcher(SIGN_IN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Error> error;

        if ((error = validateEmail(email)).isPresent()) {
            signalError(error.get());
            return;
        }

        if ((error = validatePassword(password)).isPresent()) {
            signalError(error.get());
            return;
        }

        Optional<User> existingUser = userService.findByEmail(email);

        if (existingUser.isEmpty()) {
            signalError(Error.USER_DOES_NOT_EXIST);
            return;
        }

        User user = existingUser.get();

        String hashedPassword = MD5Hash.hash(password);

        if (!user.getPassword().equals(hashedPassword)) {
            signalError(Error.PASSWORDS_DO_NOT_MATCH);
            return;
        }

        HttpSession session = request.getSession(true);

        session.setAttribute(AUTHENTICATED_USER, user);

        sendRedirect(DASHBOARD);
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

    private Optional<Error> validatePassword(String password) {
        if (password.isEmpty()) {
            return Optional.of(Error.PASSWORD_IS_EMPTY);
        }

        return Optional.empty();
    }

    private enum Error {
        EMAIL_IS_EMPTY,
        PASSWORD_IS_EMPTY,
        EMAIL_IS_INVALID,
        PASSWORDS_DO_NOT_MATCH,
        USER_DOES_NOT_EXIST
    }
}
