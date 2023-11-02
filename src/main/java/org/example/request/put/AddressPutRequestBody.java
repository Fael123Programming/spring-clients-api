package org.example.request.put;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class AddressPutRequestBody {
    private Long id;

    @NotEmpty(message = "Country cannot be empty")
    private String country;

    @NotEmpty(message = "State cannot be empty")
    private String state;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Neighbourhood cannot be empty")
    private String neighbourhood;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @NotEmpty(message = "Postal code cannot be empty")
    private String postalCode;

    @Nullable
    private Integer number;
}
