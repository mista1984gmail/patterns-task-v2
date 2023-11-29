package ru.clevertec.patterns.task.repository;

import ru.clevertec.patterns.task.entity.dto.ClientDto;
import ru.clevertec.patterns.task.entity.model.Client;

import java.util.List;

public interface ClientRepository {

    Client save(Client client) throws Exception;

    List<Client> getAll() throws Exception;


    Client getById(Long id) throws Exception;


    void delete(Long id) throws Exception;


    void update(Long id, ClientDto client);

}
