package kz.alm.LmsApp.service.impl;

import kz.alm.LmsApp.exception.ResourceAlreadyExistsException;
import kz.alm.LmsApp.exception.ResourceNotFoundException;
import kz.alm.LmsApp.model.Role;
import kz.alm.LmsApp.model.User;
import kz.alm.LmsApp.repo.RoleRepo;
import kz.alm.LmsApp.repo.UserRepo;
import kz.alm.LmsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String username) throws ResourceNotFoundException {
        log.info("--- Fetching user by username: {}", username);
        return userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("No user with username: " + username));
    }

    @Override
    public List<User> getUsers() {
        log.info("--- Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) throws ResourceAlreadyExistsException {
        log.info("--- Saving user with username: {}", user.getUsername());
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent())
            throw new ResourceAlreadyExistsException("User with username: " + user.getUsername() + " already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) throws ResourceAlreadyExistsException {
        log.info("--- Saving role with role name: {}", role.getName());
        Optional<Role> roleBD = roleRepository.findByName(role.getName());
        if(roleBD.isPresent())
            throw new ResourceAlreadyExistsException("Role with name: " + role.getName() + " already exists");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws ResourceNotFoundException {
        log.info("--- Adding role: {} to user: {}", roleName, username);
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("No user with username: " + username));
        Role role = roleRepository.findByName(roleName).
                orElseThrow(() -> new ResourceNotFoundException("No role with name: " + roleName));

        user.getRoles().add(role);
    }
}
