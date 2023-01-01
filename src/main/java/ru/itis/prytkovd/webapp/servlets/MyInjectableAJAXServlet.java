package ru.itis.prytkovd.webapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.json.JSONObject;
import ru.itis.prytkovd.inject.Injector;

import java.io.PrintWriter;
import java.util.Objects;

import static ru.itis.prytkovd.webapp.util.ContextAttributeNames.INJECTOR;

abstract class MyInjectableAJAXServlet extends HttpServlet {
    protected Injector injector;
    protected PrintWriter out;

    @Override
    public void init() throws ServletException {
        injector = (Injector)getServletContext().getAttribute(INJECTOR);
    }

    protected <E extends Enum<E>> void signalError(E error) {
        Objects.requireNonNull(out, "out must not be null");
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("errorMessage", error.name());
        out.print(json);
        out.flush();
    }

    protected void sendRedirect(String location) {
        Objects.requireNonNull(out, "out must not be null");
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("redirect", location);
        out.print(json);
        out.flush();
    }
}
