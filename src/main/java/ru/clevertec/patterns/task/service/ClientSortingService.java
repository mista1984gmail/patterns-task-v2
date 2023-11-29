package ru.clevertec.patterns.task.service;

import ru.clevertec.patterns.task.entity.dto.ClientDto;

import java.util.List;

public interface ClientSortingService {

	List<ClientDto> sortingClientsByFirstName() throws Exception;
	List<ClientDto> sortingClientsByAgeDesc() throws Exception;
	List<ClientDto> sortingClientsByRegistrationDateDesc() throws Exception;
	List<ClientDto> getClientsOlderThan(Integer age) throws Exception;

}
