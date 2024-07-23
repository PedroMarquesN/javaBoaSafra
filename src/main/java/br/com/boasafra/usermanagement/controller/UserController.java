package br.com.boasafra.usermanagement.controller;

import br.com.boasafra.usermanagement.model.User;
import br.com.boasafra.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .findFirst().orElseThrow(() -> new RuntimeException("User role not found")).getAuthority();

        if (!role.equals("Admin")) {
            return ResponseEntity.status(403).build();
        }

        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}