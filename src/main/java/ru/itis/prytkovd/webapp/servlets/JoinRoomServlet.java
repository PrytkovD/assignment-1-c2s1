package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.RoomService;
import ru.itis.prytkovd.webapp.services.UserToRoomService;

import java.io.IOException;
import java.util.Optional;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.*;

@WebServlet(name = "JoinRoomServlet", value = JOIN_ROOM)
public class JoinRoomServlet extends MyInjectableAJAXServlet {
    @Inject
    private RoomService roomService;

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
        String code = request.getParameter("code");
        Optional<Room> existingRoom = roomService.findByCode(code);

        if (existingRoom.isPresent()) {
            HttpSession session = request.getSession(false);
            User user = (User)session.getAttribute(AUTHENTICATED_USER);
            Room room = existingRoom.get();
            userToRoomService.addUserToRoom(user, room);
            session.setAttribute("room", room);
            request.getRequestDispatcher(ROOMBOARD).forward(request, response);
        } else {
            request.getRequestDispatcher(ROOM_NOT_FOUND).forward(request, response);
        }
    }
}
