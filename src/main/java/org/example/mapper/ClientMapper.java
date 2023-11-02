package org.example.mapper;

import org.example.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public class ClientMapper {
    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public abstract Client toClient(ClientPostRequestBody clientPostRequestBody);
}
