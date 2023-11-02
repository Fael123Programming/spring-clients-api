package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.domain.Client;
import org.example.request.post.ClientPostRequestBody;
import org.example.request.put.ClientPutRequestBody;
import org.example.service.ClientService;
import org.example.util.ConditionMethodRunner;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("client")
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/paginated")
    @Operation(
            summary = "List all clients paginated",
            description = "The default size is 20, use the parameter size to change the default value",
            tags = {"client"}
    )
    public ResponseEntity<Page<Client>> listAllPaginated(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(clientService.listAllPaginated(pageable));
    }

    @GetMapping(path = "/unpaginated")
    public ResponseEntity<List<Client>> listAllUnpaginated() {
        return ResponseEntity.ok(clientService.listAllUnpaginated());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable long id) {
        return ResponseEntity.ok(clientService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find-by")
    public ResponseEntity<List<Client>> findBy(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email
    ) {
        Set<Client> foundClients = new HashSet<>();
        ConditionMethodRunner.runIfStringIsNotNullAndNotEmpty(firstName, (str) -> foundClients.addAll(clientService.findByFirstName(str)));
        ConditionMethodRunner.runIfStringIsNotNullAndNotEmpty(lastName, (str) -> foundClients.addAll(clientService.findByLastName(str)));
        ConditionMethodRunner.runIfStringIsNotNullAndNotEmpty(email, (str) -> foundClients.addAll(clientService.findByEmail(str)));
        List<Client> result = new ArrayList<>(foundClients);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/admin/{id}")
    public ResponseEntity<Client> save(@RequestBody @Valid ClientPostRequestBody clientPostRequestBody) {
        return new ResponseEntity<>(clientService.save(clientPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/admin/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Client not found")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/admin/{id}")
    public ResponseEntity<Void> replace(@RequestBody ClientPutRequestBody clientPutRequestBody) {
        clientService.replace(clientPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
