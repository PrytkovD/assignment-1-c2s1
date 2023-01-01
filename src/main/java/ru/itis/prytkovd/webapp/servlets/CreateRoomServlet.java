package ru.itis.prytkovd.webapp.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.services.RoomService;
import ru.itis.prytkovd.webapp.services.UserToRoomService;
import ru.itis.prytkovd.webapp.util.StringValidator;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.prytkovd.webapp.util.SessionAttributeNames.AUTHENTICATED_USER;
import static ru.itis.prytkovd.webapp.util.WebPaths.CREATE_ROOM;
import static ru.itis.prytkovd.webapp.util.WebPaths.CREATE_ROOM_PAGE;

@WebServlet(name = "CreateRoomServlet", value = CREATE_ROOM)
public class CreateRoomServlet extends MyInjectableAJAXServlet {
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
        request.getRequestDispatcher(CREATE_ROOM_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        String name = request.getParameter("name");

        Optional<Error> error;

        if ((error = validateName(name)).isPresent()) {
            signalError(error.get());
            return;
        }

        HttpSession session = request.getSession(false);

        User user = (User)session.getAttribute(AUTHENTICATED_USER);

        Room room = Room.builder()
            .name(name)
            .code(UUID.randomUUID().toString())
            .build();

        roomService.save(room);
        userToRoomService.addUserToRoom(user, room);

        String link = "http://localhost:8080/rooms/join?code=" + room.getCode();

        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("link", link);
        out.print(json);
        out.flush();
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

    private enum Error {
        NAME_IS_EMPTY,
        NAME_IS_INVALID
    }
}
