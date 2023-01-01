package ru.itis.prytkovd.webapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.SIGN_IN;
import static ru.itis.prytkovd.webapp.util.WebPaths.SIGN_OUT;

@WebServlet(name = "SignOutServlet", value = SIGN_OUT)
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute(AUTHENTICATED_USER);
        response.sendRedirect(SIGN_IN);
    }
}
