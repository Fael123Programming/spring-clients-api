package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
