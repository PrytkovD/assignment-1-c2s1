package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.UserToRoomService;

import java.io.IOException;
import java.util.List;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.DASHBOARD;
import static ru.itis.prytkovd.webapp.util.WebPaths.DASHBOARD_PAGE;

@WebServlet(name = "DashboardServlet", value = DASHBOARD)
public class DashboardServlet extends MyInjectableAJAXServlet {
    @Inject
    private UserToRoomService userToRoomService;

    @Override
    public void init() throws ServletException {
        super.init();
        injector.injectFields(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                          IOException {
        HttpSession session = request.getSession(false);

        User user = (User)session.getAttribute(AUTHENTICATED_USER);
        List<Room> rooms = userToRoomService.findRoomsByUserId(user.getId());

        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher(DASHBOARD_PAGE).forward(request, response);
    }
}
