package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.prytkovd.webapp.entities.MoneyRequest;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.RequestService;
import ru.itis.prytkovd.webapp.services.UserToRoomService;

import java.io.IOException;
import java.util.List;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.ROOMBOARD;
import static ru.itis.prytkovd.webapp.util.WebPaths.ROOMBOARD_PAGE;

@WebServlet(name = "RoomboardServlet", value = ROOMBOARD)
public class RoomboardServlet extends MyInjectableAJAXServlet {
    @Inject
    private UserToRoomService userToRoomService;

    @Inject
    private RequestService requestService;

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
        Room room = (Room)session.getAttribute("room");

        List<User> users = userToRoomService.findUsersByRoomId(room.getId());
        List<MoneyRequest> requests = requestService.findByAssigneeIdAndRoomId(user.getId(), room.getId());

        request.setAttribute("users", users);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher(ROOMBOARD_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        HttpSession session = request.getSession(false);

        User user = (User)session.getAttribute(AUTHENTICATED_USER);
        Room room = (Room)session.getAttribute("room");

        String assigneeIdString = request.getParameter("user_select");
        String amountString = request.getParameter("amount");
        String description = request.getParameter("description");

        Long assigneeId = Long.parseLong(assigneeIdString);
        Integer amount = Integer.parseInt(amountString);

        MoneyRequest moneyRequest = MoneyRequest.builder()
            .assigneeId(assigneeId)
            .assignerId(user.getId())
            .roomId(room.getId())
            .amount(amount)
            .description(description)
            .build();

        requestService.save(moneyRequest);
    }
}
