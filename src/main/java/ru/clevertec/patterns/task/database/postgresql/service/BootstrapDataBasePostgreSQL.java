package ru.clevertec.patterns.task.database.postgresql.service;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.patterns.task.entity.dto.ClientDto;
import ru.clevertec.patterns.task.entity.model.Client;
import ru.clevertec.patterns.task.mapper.ClientMapper;
import ru.clevertec.patterns.task.mapper.ClientMapperImpl;
import ru.clevertec.patterns.task.repository.ClientRepository;
import ru.clevertec.patterns.task.repository.impl.ClientRepositoryImpl;
import ru.clevertec.patterns.task.util.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class BootstrapDataBasePostgreSQL {
    private static final Logger logger = LoggerFactory.getLogger(BootstrapDataBasePostgreSQL.class);

    private ClientRepository clientRepository = new ClientRepositoryImpl();
    private ClientMapper clientMapper = new ClientMapperImpl();

    public void fillDataBase() throws Exception {
        List<Client> clients = clientRepository.getAll();
        Faker faker = new Faker();
        if (clients.size() == 0) {
            for (int i = 0; i < Constants.DEFAULT_NUMBER_OF_CLIENTS_CREATED; i++) {
                ClientDto clientDto = new ClientDto();
                String firstName = faker.name()
                                        .firstName()
                                        .replaceAll("\'", "");
                String lastName = faker.name()
                                       .lastName()
                                       .replaceAll("\'", "");
                clientDto.setFirstName(firstName);
                clientDto.setLastName(lastName);
                clientDto.setEmail(firstName.toLowerCase() + "_" + lastName.toLowerCase() + "_" + i + "@gmail.com");
                clientDto.setTelephone(createTelephoneNumber(faker.number()
                                                                  .numberBetween(1, 9999999)));
                clientDto.setBirthday(LocalDate.of(1960, Month.JANUARY, 1)
                                               .plusDays(faker.number()
                                                              .numberBetween(1, 16000)));
                clientDto.setRegistrationDate(LocalDateTime.of(2015, 1, 1, 15, 00)
                                                           .plusMinutes(faker.number()
                                                                             .numberBetween(1, 3153600)));
                clientRepository.save(clientMapper.clientDtoToClient(clientDto));
            }
            logger.info("Table with clients is full");
        }
    }

    private String createTelephoneNumber(int i) {
        String str = String.valueOf(i);
        String phoneNumber = "+375290000000";
        return phoneNumber.substring(0, phoneNumber.length() - str.length()) + i;
    }

}
