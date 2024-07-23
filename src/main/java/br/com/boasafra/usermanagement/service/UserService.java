package br.com.boasafra.usermanagement.service;
import br.com.boasafra.usermanagement.model.User;
import br.com.boasafra.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setRole(userDetails.getRole());
            user.setCnpj(userDetails.getCnpj());
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(userDetails.getPassword());
            }
            return userRepository.save(user);
        }
        throw new RuntimeException("Usuário não encontrado");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
