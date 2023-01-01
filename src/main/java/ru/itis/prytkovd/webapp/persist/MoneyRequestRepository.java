package ru.itis.prytkovd.webapp.persist;

import jakarta.inject.Inject;
import ru.itis.prytkovd.persist.Persistor;
import ru.itis.prytkovd.webapp.entities.MoneyRequest;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;

import java.util.List;

@ContextScoped
public class MoneyRequestRepository extends EntityRepository<MoneyRequest, Long> {
    @Inject
    public MoneyRequestRepository(Persistor persistor) {
        super(persistor, MoneyRequest.class, MoneyRequest::new);
    }

    public List<MoneyRequest> findByAssigneeIdAndRoomId(Long assigneeId, Long roomId) {
        return persistor.select(entityType)
            .where("assigneeId = ?", assigneeId)
            .where("roomId = ?", roomId)
            .queryForList(entitySupplier);
    }

    public List<MoneyRequest> findByAssignerIdAndRoomId(Long assignerId, Long roomId) {
        return persistor.select(entityType)
            .where("assignerId = ?", assignerId)
            .where("roomId = ?", roomId)
            .queryForList(entitySupplier);
    }

    public List<MoneyRequest> findByRoomId(Long roomId) {
        return persistor.select(entityType)
            .where("roomId = ?", roomId)
            .queryForList(entitySupplier);
    }
}
