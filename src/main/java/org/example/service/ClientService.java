package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Client;
import org.example.exception.BadRequestException;
import org.example.repository.ClientRepository;
import org.example.request.post.ClientPostRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Page<Client> listAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public List<Client> findByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    public List<Client> findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    public List<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Client findByIdOrThrowBadRequestException(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("Client with id '%d' not found", id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public Client save(ClientPostRequestBody clientPostRequestBody) {
        return clientRepository.save(ClientMapper.INSTANCE.toClient(clientPostRequestBody));
    }
}
