package edu.cit.veloso.miguelray.campusequipmentloan.repository;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
