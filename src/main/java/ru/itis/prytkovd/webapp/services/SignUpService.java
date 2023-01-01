package ru.itis.prytkovd.webapp.services;

import jakarta.inject.Inject;
import ru.itis.prytkovd.webapp.entities.User;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;
import ru.itis.prytkovd.webapp.persist.UserRepository;

import java.util.Optional;

@ContextScoped
public class SignUpService {
    private final UserRepository userRepository;

    @Inject
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        userRepository.save(user);

        return true;
    }
}
