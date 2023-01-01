package ru.itis.prytkovd.webapp.services;

import jakarta.inject.Inject;
import ru.itis.prytkovd.webapp.entities.MoneyRequest;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;
import ru.itis.prytkovd.webapp.persist.MoneyRequestRepository;

import java.util.List;

@ContextScoped
public class RequestService {
    private final MoneyRequestRepository moneyRequestRepository;

    @Inject
    public RequestService(MoneyRequestRepository moneyRequestRepository) {
        this.moneyRequestRepository = moneyRequestRepository;
    }

    public List<MoneyRequest> findByAssigneeIdAndRoomId(Long assigneeId, Long roomId) {
        return moneyRequestRepository.findByAssigneeIdAndRoomId(assigneeId, roomId);
    }

    public List<MoneyRequest> findByAssignerIdAndRoomId(Long assignerId, Long roomId) {
        return moneyRequestRepository.findByAssignerIdAndRoomId(assignerId, roomId);
    }

    public List<MoneyRequest> findByRoomId(Long roomId) {
        return moneyRequestRepository.findByRoomId(roomId);
    }

    public void save(MoneyRequest... moneyRequests) {
        moneyRequestRepository.save(moneyRequests);
    }

    public void delete(MoneyRequest... moneyRequests) {
        moneyRequestRepository.delete(moneyRequests);
    }
}
