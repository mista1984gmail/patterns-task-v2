package ru.clevertec.patterns.task.service;

import ru.clevertec.patterns.task.entity.dto.ClientDto;

import java.util.List;

public interface ClientService {

    Long create(ClientDto client) throws Exception;

    List<ClientDto> getAll() throws Exception;

    ClientDto getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    void update(Long id, ClientDto client) throws Exception;

}
