package br.com.servidores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.servidores.config.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
