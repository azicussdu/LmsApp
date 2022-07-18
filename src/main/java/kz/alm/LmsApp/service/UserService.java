package kz.alm.LmsApp.service;

import kz.alm.LmsApp.exception.ResourceAlreadyExistsException;
import kz.alm.LmsApp.exception.ResourceNotFoundException;
import kz.alm.LmsApp.model.Role;
import kz.alm.LmsApp.model.User;

import java.util.List;

public interface UserService {
    User getUser(String username) throws ResourceNotFoundException;
    List<User> getUsers();
    User saveUser(User user) throws ResourceAlreadyExistsException;
    Role saveRole(Role role) throws ResourceAlreadyExistsException;
    void addRoleToUser(String username, String roleName) throws ResourceNotFoundException;
}
