package ru.clevertec.patterns.task.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.patterns.task.entity.dto.ClientDto;
import ru.clevertec.patterns.task.entity.model.Client;
import ru.clevertec.patterns.task.mapper.ClientMapper;
import ru.clevertec.patterns.task.repository.ClientSortingRepository;
import util.ClientsTestData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientSortingServiceImplTest {

	@Mock
	private ClientSortingRepository clientRepository;
	@Mock
	private ClientMapper clientMapper;
	@InjectMocks
	private ClientSortingServiceImpl clientService;

	@Test
	void shouldSortingClientsByFirstName() throws Exception {
		// given

		List<Client> clients = ClientsTestData.getListOfClients();
		List<ClientDto> expected = ClientsTestData.getListOfClientsDto();

		when(clientRepository.sortingClientsByFirstName())
				.thenReturn(clients);
		when(clientMapper.clientsToClientsDto(clients))
				.thenReturn(expected);

		//when
		List<ClientDto> actual = clientService.sortingClientsByFirstName();

		//then
		assertArrayEquals(expected.toArray(), actual.toArray());
		assertEquals(3, actual.size());
		verify(clientRepository).sortingClientsByFirstName();
		verify(clientMapper).clientsToClientsDto(any());
	}

	@Test
	void shouldSortingClientsByAgeDesc() throws Exception {
		// given

		List<Client> clients = ClientsTestData.getListOfClientsSortingByAge();
		List<ClientDto> expected = ClientsTestData.getListOfClientsDtoSortingByAge();

		when(clientRepository.sortingClientsByAgeDesc())
				.thenReturn(clients);
		when(clientMapper.clientsToClientsDto(clients))
				.thenReturn(expected);

		//when
		List<ClientDto> actual = clientService.sortingClientsByAgeDesc();

		//then
		assertArrayEquals(expected.toArray(), actual.toArray());
		assertEquals(3, actual.size());
		verify(clientRepository).sortingClientsByAgeDesc();
		verify(clientMapper).clientsToClientsDto(any());
	}

	@Test
	void shouldSortingClientsByRegistrationDateDesc() throws Exception {
		// given

		List<Client> clients = ClientsTestData.getListOfClientsSortingByRegistrationDate();
		List<ClientDto> expected = ClientsTestData.getListOfClientsDtoSortingByRegistrationDate();

		when(clientRepository.sortingClientsByRegistrationDateDesc())
				.thenReturn(clients);
		when(clientMapper.clientsToClientsDto(clients))
				.thenReturn(expected);

		//when
		List<ClientDto> actual = clientService.sortingClientsByRegistrationDateDesc();

		//then
		assertArrayEquals(expected.toArray(), actual.toArray());
		assertEquals(3, actual.size());
		verify(clientRepository).sortingClientsByRegistrationDateDesc();
		verify(clientMapper).clientsToClientsDto(any());
	}

	@Test
	void shouldGetClientsOlderThan() throws Exception {
		// given

		List<Client> clients = ClientsTestData.getListOfClientsWithAgeOlderThan25();
		List<ClientDto> expected = ClientsTestData.getListOfClientsDtoWithAgeOlderThan25();

		when(clientRepository.getClientsOlderThan(25))
				.thenReturn(clients);
		when(clientMapper.clientsToClientsDto(clients))
				.thenReturn(expected);

		//when
		List<ClientDto> actual = clientService.getClientsOlderThan(25);

		//then
		assertArrayEquals(expected.toArray(), actual.toArray());
		assertEquals(1, actual.size());
		verify(clientRepository).getClientsOlderThan(25);
		verify(clientMapper).clientsToClientsDto(any());
	}

}