package ru.itis.prytkovd.webapp.services;

import jakarta.inject.Inject;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;
import ru.itis.prytkovd.webapp.persist.UserRepository;

import java.util.List;
import java.util.Optional;

@ContextScoped
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
