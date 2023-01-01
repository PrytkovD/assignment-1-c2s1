package ru.itis.prytkovd.webapp.services;

import jakarta.inject.Inject;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;
import ru.itis.prytkovd.webapp.persist.RoomRepository;

import java.util.List;
import java.util.Optional;

@ContextScoped
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserToRoomService userToRoomService;

    @Inject
    public RoomService(RoomRepository roomRepository, UserToRoomService userToRoomService) {
        this.roomRepository = roomRepository;
        this.userToRoomService = userToRoomService;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public Optional<Room> findByName(String name) {
        return roomRepository.findByName(name);
    }

    public Optional<Room> findByCode(String code) {
        return roomRepository.findByCode(code);
    }

    public void save(Room... rooms) {
        roomRepository.save(rooms);
    }

    public void delete(Room... rooms) {
        for (Room room : rooms) {
            userToRoomService.deleteByRoomId(room.getId());
        }
        roomRepository.delete(rooms);
    }

    public void deleteById(Long id) {
        userToRoomService.deleteByRoomId(id);
        roomRepository.deleteById(id);
    }
}
