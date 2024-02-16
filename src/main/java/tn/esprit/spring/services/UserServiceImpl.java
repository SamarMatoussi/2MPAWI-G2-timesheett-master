package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users = null;
        try {
            if (l.isInfoEnabled()) {
                l.info("Retrieving all users...");
            }

            users = userRepository.findAll();

            if (l.isInfoEnabled()) {
                l.info("Retrieved {} users successfully.", users.size());
            }
        } catch (Exception e) {
            l.error("Error retrieving all users: {}", e.getMessage(), e);
        }
        return users;
    }


    @Override
    public User addUser(User u) {
        User utilisateur = null;
        try {
            l.info("Adding a new user...");
            utilisateur = userRepository.save(u);
            l.info("User added successfully: {}", utilisateur);
        } catch (Exception e) {
            l.error("Error adding user: {}", e.getMessage(), e);
        }
        return utilisateur;
    }

    @Override
    public User updateUser(User u) {
        User userUpdated = null;
        try {
            l.info("Updating user...");
            userUpdated = userRepository.save(u);
            l.info("User updated successfully: {}", userUpdated);
        } catch (Exception e) {
            l.error("Error updating user: {}", e.getMessage(), e);
        }
        return userUpdated;
    }

    @Override
    public void deleteUser(String id) {
        try {
            l.info("Deleting user with ID: {}", id);
            userRepository.deleteById(Long.parseLong(id));
            l.info("User deleted successfully with ID: {}", id);
        } catch (Exception e) {
            l.error("Error deleting user with ID {}: {}", id, e.getMessage(), e);
        }
    }


    @Override
    public User retrieveUser(String id) {
        User u = null;
        try {
            l.info("Retrieving user with ID: {}", id);
            Optional<User> optionalUser = userRepository.findById(Long.parseLong(id));
            u = optionalUser.orElse(null);
            l.info("User retrieved successfully with ID: {}", id);
        } catch (Exception e) {
            l.error("Error retrieving user with ID {}: {}", id, e.getMessage(), e);
        }
        return u;
    }


}
