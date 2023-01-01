package ru.itis.prytkovd.webapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.*;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {
    DASHBOARD,
    SIGN_OUT,
    CREATE_ROOM,
    DELETE_ROOM,
    JOIN_ROOM,
    ROOMBOARD
})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isSignedIn = session != null && session.getAttribute(AUTHENTICATED_USER) != null;
        boolean isSignInRequest = httpRequest.getRequestURI().equals(SIGN_IN);

        if (isSignedIn || isSignInRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(SIGN_IN);
        }
    }
}
