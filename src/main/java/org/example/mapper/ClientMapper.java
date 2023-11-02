package org.example.mapper;

import org.example.domain.Client;
import org.example.request.post.ClientPostRequestBody;
import org.example.request.put.ClientPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public abstract Client toClient(ClientPostRequestBody clientPostRequestBody);

    public abstract Client toClient(ClientPutRequestBody clientPutRequestBody);
}
