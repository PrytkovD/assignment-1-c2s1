package ru.itis.prytkovd.webapp.persist;

import jakarta.inject.Inject;
import ru.itis.prytkovd.persist.Persistor;
import ru.itis.prytkovd.webapp.entities.UserToRoom;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;

import java.util.List;
import java.util.Optional;

@ContextScoped
public class UserToRoomRepository extends EntityRepository<UserToRoom, Long> {
    private final String USER_ID_EQUALS;
    private final String ROOM_ID_EQUALS;

    @Inject
    public UserToRoomRepository(Persistor persistor) {
        super(persistor, UserToRoom.class, UserToRoom::new);
        USER_ID_EQUALS = "userId = ?";
        ROOM_ID_EQUALS = "roomId = ?";
    }

    public List<UserToRoom> findByUserId(Long userId) {
        return persistor.select(entityType)
            .where(USER_ID_EQUALS, userId)
            .queryForList(entitySupplier);
    }

    public List<UserToRoom> findByRoomId(Long roomId) {
        return persistor.select(entityType)
            .where(ROOM_ID_EQUALS, roomId)
            .queryForList(entitySupplier);
    }

    public Optional<UserToRoom> findByUserAndRoomId(Long userId, Long roomId) {
        return persistor.select(entityType)
            .where(USER_ID_EQUALS, userId)
            .where(ROOM_ID_EQUALS, roomId)
            .queryForObject(entitySupplier);
    }

    public void deleteByUserId(Long userId) {
        persistor.delete(entityType)
            .where(USER_ID_EQUALS, userId)
            .execute();
    }

    public void deleteByRoomId(Long roomId) {
        persistor.delete(entityType)
            .where(ROOM_ID_EQUALS, roomId)
            .execute();
    }

    public void deleteByByUserAndRoomId(Long userId, Long roomId) {
        persistor.delete(entityType)
            .where(USER_ID_EQUALS, userId)
            .where(ROOM_ID_EQUALS, roomId)
            .execute();
    }
}
