package ru.itis.prytkovd.webapp.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import ru.itis.prytkovd.inject.Injector;
import ru.itis.prytkovd.inject.Provide;
import ru.itis.prytkovd.persist.Persistor;
import ru.itis.prytkovd.webapp.entities.MoneyRequest;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.entities.UserToRoom;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;
import ru.itis.prytkovd.webapp.inject.scopes.ContextScope;

import javax.sql.DataSource;

import static ru.itis.prytkovd.webapp.util.ContextAttributeNames.INJECTOR;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        Injector injector = Injector.builder()
            .scope(new ContextScope(context))
            .module(new PersistenceModule())
            .build();

        context.setAttribute(INJECTOR, injector);

        Persistor persistor = injector.instance(Persistor.class);
        persistor.createTable(User.class);
        persistor.createTable(Room.class);
        persistor.createTable(UserToRoom.class);
        persistor.createTable(MoneyRequest.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        Injector injector = (Injector)context.getAttribute(INJECTOR);

        HikariDataSource hikariDataSource = (HikariDataSource)injector.instance(DataSource.class);
        hikariDataSource.close();
    }

    public static final class PersistenceModule {
        @Provide @ContextScoped
        public DataSource provideDataSource() {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/assignment");
            config.setUsername("prytkovd");
            config.setPassword("");
            return new HikariDataSource(config);
        }

        @Provide @ContextScoped
        public Persistor providePersistor(DataSource dataSource) {
            return new Persistor(dataSource);
        }
    }
}
