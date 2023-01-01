package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.RoomService;
import ru.itis.prytkovd.webapp.services.UserToRoomService;

import java.io.IOException;
import java.util.Optional;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.*;

@WebServlet(name = "DeleteRoomServlet", value = DELETE_ROOM)
public class DeleteRoomServlet extends MyInjectableAJAXServlet {
    @Inject
    private RoomService roomService;

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
            Room room = existingRoom.get();
            roomService.delete(room);
        }
    }
}
