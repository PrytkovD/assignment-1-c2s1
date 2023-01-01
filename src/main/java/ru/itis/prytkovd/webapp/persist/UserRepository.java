package ru.itis.prytkovd.webapp.persist;

import jakarta.inject.Inject;
import ru.itis.prytkovd.persist.Persistor;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;

import java.util.Optional;

@ContextScoped
public class UserRepository extends EntityRepository<User, Long> {
    @Inject
    public UserRepository(Persistor persistor) {
        super(persistor, User.class, User::new);
    }

    public Optional<User> findByName(String name) {
        return persistor.select(entityType)
            .where("name = ?", name)
            .queryForObject(entitySupplier);
    }

    public Optional<User> findByEmail(String email) {
        return persistor.select(entityType)
            .where("email = ?", email)
            .queryForObject(entitySupplier);
    }
}
