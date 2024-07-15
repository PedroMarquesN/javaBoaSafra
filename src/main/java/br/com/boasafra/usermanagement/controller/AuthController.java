package br.com.boasafra.usermanagement.controller;

import br.com.boasafra.usermanagement.model.User;
import br.com.boasafra.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        Optional<User> existingUsers = userRepository.findByUsername(user.getUsername());

        if (existingUsers.isPresent()) {
            User existingUser = existingUsers.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                // Construir a resposta com token e role
                Map<String, String> response = new HashMap<>();
                response.put("token", "dummy-token");
                response.put("role", existingUser.getRole());
                response.put("username", existingUser.getUsername());
                return response;
            } else {
                throw new RuntimeException("Credenciais inválidas");
            }
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}
