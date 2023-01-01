package ru.itis.prytkovd.webapp.services;

import jakarta.inject.Inject;
import ru.itis.prytkovd.webapp.entities.Room;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.entities.UserToRoom;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;
import ru.itis.prytkovd.webapp.persist.RoomRepository;
import ru.itis.prytkovd.webapp.persist.UserRepository;
import ru.itis.prytkovd.webapp.persist.UserToRoomRepository;

import java.util.List;
import java.util.Optional;

@ContextScoped
public class UserToRoomService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserToRoomRepository userToRoomRepository;

    @Inject
    public UserToRoomService(UserRepository userRepository,
                             RoomRepository roomRepository,
                             UserToRoomRepository userToRoomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.userToRoomRepository = userToRoomRepository;
    }

    public List<Room> findRoomsByUserId(Long id) {
        System.out.println(id);
        return userToRoomRepository.findByUserId(id).stream()
            .map(userToRoom -> {
                System.out.println(userToRoom);
                return roomRepository.findById(userToRoom.getRoomId());
            })
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

    public List<User> findUsersByRoomId(Long id) {
        return userToRoomRepository.findByRoomId(id).stream()
            .map(userToRoom -> userRepository.findById(userToRoom.getUserId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

    public void deleteByUserId(Long userId) {
        userToRoomRepository.deleteByUserId(userId);
    }

    public void deleteByRoomId(Long roomId) {
        userToRoomRepository.deleteByRoomId(roomId);
    }

    public boolean addUserToRoom(User user, Room room) {
        Long userId = user.getId();
        Long roomId = room.getId();

        if (userId == null || roomId == null) {
            throw new IllegalArgumentException("id must not be null");
        }

        if (userRepository.findById(userId).isEmpty() ||
            roomRepository.findById(roomId).isEmpty()) {
            return false;
        }

        if (userToRoomRepository.findByUserAndRoomId(userId, roomId).isPresent()) {
            return true;
        }

        UserToRoom userToRoom = UserToRoom.builder()
            .userId(userId)
            .roomId(roomId)
            .build();

        userToRoomRepository.save(userToRoom);

        return true;
    }

    public void removeUserFromRoom(User user, Room room) {
        userToRoomRepository.deleteByByUserAndRoomId(user.getId(), room.getId());
    }
}
