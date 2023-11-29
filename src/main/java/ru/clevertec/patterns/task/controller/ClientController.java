package ru.clevertec.patterns.task.controller;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.json.parser.JSONParser;
import ru.clevertec.patterns.task.entity.dto.ClientDto;
import ru.clevertec.patterns.task.exception.ClientNotFoundException;
import ru.clevertec.patterns.task.exception.ClientValidateException;
import ru.clevertec.patterns.task.pdfcreator.PDFWriter;
import ru.clevertec.patterns.task.service.ClientService;
import ru.clevertec.patterns.task.service.ClientSortingService;
import ru.clevertec.patterns.task.util.Constants;
import ru.clevertec.patterns.task.validator.ClientValidator;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    public static final Consumer<String> LOG_ACTION = client ->
            logger.info("{}", client);

    private ClientService clientService;
    private ClientSortingService clientSortingService;
    private ClientValidator clientValidator;
    private PDFWriter pdfWriter;
    private XmlMapper xmlMapper;

    private JSONParser jsonParser;

    public ClientController(ClientService clientService, ClientSortingService clientSortingService, ClientValidator clientValidator, PDFWriter pdfWriter, XmlMapper xmlMapper, JSONParser jsonParser) {
        this.clientService = clientService;
        this.clientSortingService = clientSortingService;
        this.clientValidator = clientValidator;
        this.pdfWriter = pdfWriter;
        this.xmlMapper = xmlMapper;
        this.jsonParser = jsonParser;
    }

    /**
     * Принимает строку в формате Json, трансформирует ее в объект ClientDto и
     * валидирует его на корректность данных, если валидация не проходит -
     * выкидывает исключение ClientValidateException с ошибкой о том, какие данные
     * не правильные
     * <p>
     * Передает провалидированный объект на сервис, если валидация прошла успешно
     *
     * @throws ClientValidateException если Клиент не прошел валидацию
     */
    public void addClientFromJson() throws Exception {
        logger.info("Введите информацию о КЛИЕНТЕ в формате json:");
        Scanner scanner = new Scanner(System.in);
        String clientJson = scanner.nextLine();
        ClientDto client = jsonParser.convertJsonToObject(clientJson, ClientDto.class);
        String errorMessages = clientValidator.validateProduct(client);
        if (!errorMessages.isEmpty()) {
            throw new ClientValidateException(errorMessages);
        } else {
            clientService.create(client);
        }
    }

    /**
     * Отображает всех Клиентов
     * Сохраняет Клиентов в pdf
     */
    public void getAllClients() throws Exception {
        logger.info("Отобразить всех клиентов:");
        printClients(clientService.getAll());
    }

    /**
     * Принимает id Клиента для отображения
     * Отображает клиента в формате Json
     * Сохраняет Клиента в pdf
     *
     * @throws ClientNotFoundException если Клиент не найден
     */
    public String getClient() throws Exception {
        Long idForShow;
        logger.info("Введите id КЛИЕНТА для отображения:");
        Scanner scanner = new Scanner(System.in);
        idForShow = scanner.nextLong();
        logger.info("Получение КЛИЕНТА по id = '{}'", idForShow);
        ClientDto client = null;
        try {
            client = clientService.getById(idForShow);
        } catch (ClientNotFoundException cnfe) {
            logger.error(cnfe.getMessage());
        }
        if (client != null) {
            String fileName = "";
            logger.info("Введите имя файла (если будет пустым - имя файла сформируется по умолчанию): ");
            Scanner scan = new Scanner(System.in);
            fileName = scan.nextLine();
            pdfWriter.writeClientToPDF(client, Constants.PATH_PDF_CLIENT, fileName);
            String clientJson = jsonParser.convertObjectToJson(client);
            logger.info(clientJson);
            return clientJson;
        } else return "{null}";
    }

    /**
     * Принимает id Клиента для удаления
     * и передает ее на сервис
     *
     * @throws ClientNotFoundException если Клиент не найден
     */
    public void deleteClient() throws Exception {
        Long idForDelete;
        logger.info("Введите id КЛИЕНТА для удаления:");
        Scanner scanner = new Scanner(System.in);
        idForDelete = scanner.nextLong();
        try {
            clientService.delete(idForDelete);
        } catch (ClientNotFoundException cnfe) {
            logger.error(cnfe.getMessage());
        }
    }

    /**
     * Принимает id Клиента для обновления и строку в
     * формате json, трансформирует ее в объект ClientDto и
     * валидирует его на корректность данных, если валидация не проходит -
     * выкидывает исключение ClientValidateException с ошибкой о том, какие данные
     * не правильные
     * <p>
     * Передает провалидированный объект на сервис, если валидация прошла успешно
     * где происходит его обновление
     * Если Клиента не существует с таким id - выкидывается исключение ClientNotFoundException
     *
     * @throws ClientValidateException если Клиент не прошел валидацию
     * @throws ClientNotFoundException если Клиент не найден
     */
    public void updateClient() throws Exception {
        Long idForUpdate;
        logger.info("Введите id КЛИЕНТА для обновления");
        Scanner scanner = new Scanner(System.in);
        idForUpdate = scanner.nextLong();
        logger.info("Введите информацию о КЛИЕНТЕ в формате json:");
        Scanner scanner1 = new Scanner(System.in);
        String clientJson = scanner1.nextLine();
        ClientDto clientToUpdate = jsonParser.convertJsonToObject(clientJson, ClientDto.class);
        String errorMessages = clientValidator.validateProduct(clientToUpdate);
        if (!errorMessages.isEmpty()) {
            throw new ClientValidateException(errorMessages);
        }
        logger.info("Получение КЛИЕНТА по id = '{}'", idForUpdate);
        ClientDto clientForUpdate = null;
        try {
            clientForUpdate = clientService.getById(idForUpdate);
            logger.info("КЛИЕНТ с id= '{}', {} найден", idForUpdate, clientForUpdate);
            clientService.update(idForUpdate, clientToUpdate);
        } catch (ClientNotFoundException cnfe) {
            logger.error(cnfe.getMessage());
        }
    }

    /**
     * Принимает строку в формате xml, трансформирует ее в объект ClientDto и
     * валидирует его на корректность данных, если валидация не проходит -
     * выкидывает исключение ClientValidateException с ошибкой о том, какие данные
     * не правильные
     * <p>
     * Передает провалидированный объект на сервис, если валидация прошла успешно
     *
     * @throws ClientValidateException если Клиент не прошел валидацию
     */
    public void addClientFromXml() throws Exception {
        logger.info("Введите информацию о КЛИЕНТЕ в формате xml:");
        Scanner scanner = new Scanner(System.in);
        String clientXml = scanner.nextLine();
        ClientDto client = xmlMapper.readValue(clientXml, ClientDto.class);
        String errorMessages = clientValidator.validateProduct(client);
        if (!errorMessages.isEmpty()) {
            throw new ClientValidateException(errorMessages);
        } else {
            clientService.create(client);
        }
    }

    /**
     * Сортирует и отображает Клиентов
     * - по имени в алфавитном порядке,
     * - по возрасту,
     * - по дате регистарации,
     * - выводит Клиентов старше заданного возраста.
     * Сохраняет Клиентов в pdf.
     */
    public void sortingClients() throws Exception {

        logger.info("Виды сортировки:");
        logger.info("1. По имени в алфавитном порядке");
        logger.info("2. По возрасту");
        logger.info("3. По дате регистарации");
        logger.info("4. Вывести Клиентов старше заданного возраста");
        logger.info("Введите цифру пункта для сортировки и формирования pdf");
        int userInput;
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                printClients(clientSortingService.sortingClientsByFirstName());
                break;
            case 2:
                printClients(clientSortingService.sortingClientsByAgeDesc());
                break;
            case 3:
                printClients(clientSortingService.sortingClientsByRegistrationDateDesc());
                break;
            case 4:
                int age;
                logger.info("Введите возраст старше которого нужно отобразить Клиентов:");
                Scanner scan = new Scanner(System.in);
                age = scan.nextInt();
                printClients(clientSortingService.getClientsOlderThan(age));
                break;
            default:
                logger.info("Нет такой операции, пожалуйста, попробуйте другую операцию!");
        }
    }

    /**
     * Сохраняет Клиентов в pdf.
     *
     */
    public void printClients(List<ClientDto> clients) throws Exception {
        logger.info("Отобразить клиентов:");
        List<String> jsonClients = clients
                .stream()
                .map(clientDto -> jsonParser.convertObjectToJson(clientDto))
                .toList();
        jsonClients.forEach(LOG_ACTION);
        String fileName = "";
        logger.info("Введите имя файла (если оставить пустым - имя файла сформируется по умолчанию): ");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        pdfWriter.writeClientsToPDF(clients, Constants.PATH_PDF_CLIENTS, fileName);
    }
}