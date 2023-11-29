package ru.clevertec.patterns.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.patterns.task.entity.dto.ClientDto;
import ru.clevertec.patterns.task.mapper.ClientMapper;
import ru.clevertec.patterns.task.repository.ClientSortingRepository;
import ru.clevertec.patterns.task.service.ClientSortingService;

import java.util.List;

public class ClientSortingServiceImpl implements ClientSortingService {

	private static final Logger logger = LoggerFactory.getLogger(ClientSortingServiceImpl.class);

	private ClientSortingRepository clientRepository;
	private ClientMapper clientMapper;

	public ClientSortingServiceImpl(ClientSortingRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	/**
	 * Возвращает всех существующих Клиентов
	 * с сортировкой по имени в алфавитном порядке
	 *
	 * @return лист с информацией о Клиентах
	 */
	@Override
	public List<ClientDto> sortingClientsByFirstName() throws Exception {
		return clientMapper.clientsToClientsDto(clientRepository.sortingClientsByFirstName());
	}

	/**
	 * Возвращает всех существующих Клиентов
	 * с сортировкой по возрасту
	 *
	 * @return лист с информацией о Клиентах
	 */
	@Override
	public List<ClientDto> sortingClientsByAgeDesc() throws Exception {
		return clientMapper.clientsToClientsDto(clientRepository.sortingClientsByAgeDesc());
	}

	/**
	 * Возвращает всех существующих Клиентов
	 * с сортировкой по дате регистрации
	 *
	 * @return лист с информацией о Клиентах
	 */
	@Override
	public List<ClientDto> sortingClientsByRegistrationDateDesc() throws Exception {
		return clientMapper.clientsToClientsDto(clientRepository.sortingClientsByRegistrationDateDesc());
	}

	/**
	 * Возвращает Клиентов
	 * старше заданного возраста
	 *
	 * @param age возраст Клиента старше которого надо отобразить
	 * @return лист с информацией о Клиентах
	 */
	@Override
	public List<ClientDto> getClientsOlderThan(Integer age) throws Exception {
		return clientMapper.clientsToClientsDto(clientRepository.getClientsOlderThan(age));
	}
}
