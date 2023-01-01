package ru.itis.prytkovd.webapp.persist;

import jakarta.inject.Inject;
import ru.itis.prytkovd.persist.Persistor;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;

import java.util.Optional;

@ContextScoped
public class RoomRepository extends EntityRepository<Room, Long> {
    @Inject
    public RoomRepository(Persistor persistor) {
        super(persistor, Room.class, Room::new);
    }

    public Optional<Room> findByName(String name) {
        return persistor.select(entityType)
            .where("name = ?", name)
            .queryForObject(entitySupplier);
    }

    public Optional<Room> findByCode(String code) {
        return persistor.select(entityType)
            .where("code = ?", code)
            .queryForObject(entitySupplier);
    }
}
