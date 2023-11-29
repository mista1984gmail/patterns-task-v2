package ru.clevertec.patterns.task.repository;

import ru.clevertec.patterns.task.entity.model.Client;

import java.util.List;

public interface ClientSortingRepository {

	List<Client> sortingClientsByFirstName() throws Exception;
	List<Client> sortingClientsByAgeDesc() throws Exception;
	List<Client> sortingClientsByRegistrationDateDesc() throws Exception;
	List<Client> getClientsOlderThan(Integer age) throws Exception;

}
