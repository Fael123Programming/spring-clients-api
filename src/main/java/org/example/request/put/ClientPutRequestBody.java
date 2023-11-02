package org.example.request.put;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientPutRequestBody {
    private Long id;

    @NotEmpty(message = "First name cannot be empty")
    @Schema(description = "This is the client's first name", example = "John")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Schema(description = "This is the client's last name", example = "Doe")
    private String lastName;

    @NotEmpty(message = "E-mail cannot be empty")
    @Schema(description = "This is the client's e-mail", example = "John.doe@example.com")
    private String email;

    @NotNull(message = "Addresses cannot be null")
    @Schema(description = "These are the client's addresses")
    private List<AddressPutRequestBody> addresses;

    @NotNull(message = "Phone numbers cannot be null")
    @Schema(description = "These are the client's phone numbers")
    private List<String> phoneNumbers;
}
