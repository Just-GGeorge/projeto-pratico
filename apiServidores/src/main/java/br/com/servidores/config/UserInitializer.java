package br.com.servidores.config;

import br.com.servidores.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class UserInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.default.username}")
    private String defaultUsername;

    @Value("${user.default.email}")
    private String defaultEmail;

    @Value("${user.default.password}")
    private String defaultPassword;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        boolean usuarioExiste = userRepository.findByUsername(defaultUsername).isPresent();

        if (!usuarioExiste) {
            User user = new User();
            user.setUsername(defaultUsername);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode(defaultPassword));
            userRepository.save(user);
            System.out.println("✅ Usuário de teste criado automaticamente.");
        } else {
            System.out.println("ℹ️ Usuário de teste já existe.");
        }
    }
}
