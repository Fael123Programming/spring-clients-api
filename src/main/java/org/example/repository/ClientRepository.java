package org.example.repository;

import org.example.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    List<Client> findByEmail(String email);
}
