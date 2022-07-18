package kz.alm.LmsApp.api;

import kz.alm.LmsApp.dto.RoleToUserDTO;
import kz.alm.LmsApp.exception.ResourceAlreadyExistsException;
import kz.alm.LmsApp.exception.ResourceNotFoundException;
import kz.alm.LmsApp.model.Role;
import kz.alm.LmsApp.model.User;
import kz.alm.LmsApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws ResourceAlreadyExistsException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) throws ResourceAlreadyExistsException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addrole")
    public ResponseEntity<?> addRoleToUser(@Valid @RequestBody RoleToUserDTO roleToUserDTO,
                                           @RequestHeader(value = "accept-language", required = false) Locale locale) throws ResourceNotFoundException {
        userService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRolename());
        return ResponseEntity.ok().build();
    }
}
