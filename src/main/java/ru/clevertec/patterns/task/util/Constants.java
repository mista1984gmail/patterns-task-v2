package ru.clevertec.patterns.task.util;

import ru.clevertec.patterns.task.cache.Cache;
import ru.clevertec.patterns.task.cache.factory.CacheFactory;
import ru.clevertec.patterns.task.config.LoadProperties;

import java.util.List;

public class Constants {

    public static final String CACHE_SIZE = "cache_size";
    public static final String CACHE_TYPE = "cache_type";
    public static final String CACHE_TYPE_LRU = "LRU";
    public static final String CACHE_TYPE_LFU = "LFU";
    public static final String PROPERTIES_FILE_NAME = "src/main/resources/application.yml";
    public static final String URL = "database_url";
    public static final String USERNAME = "database_username";
    public static final String PASSWORD = "database_password";

    public static final Cache CACHE = new CacheFactory().createCache(LoadProperties.getProperty()
                                                                                   .get(Constants.CACHE_TYPE));

    public static final String PATH_PDF_CLIENT = "pdf/reports/info_about_client";
    public static final String PATH_PDF_CLIENTS = "pdf/reports/info_about_clients";
    public static final String DATE_TIME_PATTERN_FOR_FILE_NAME = "dd-MM-yyyy-HH-mm-ss";
    public static final String DATE_TIME_PATTERN_FOR_PDF = "dd.MM.yyyy HH:mm:ss";
    public static final String DATE_PATTERN_FOR_PDF = "dd.MM.yyyy";
    public static final String DATE_TIME_PATTERN_FOR_PAGINATION = "dd.MM.yyyy HH:mm:ss";
    public static final String PATH_TO_BACKGROUND = "src/main/resources/img/Clevertec_Template.jpg";
    public static final String PATH_TO_TIMES_ROMAN_FONT = "src/main/resources/font/times-ro.ttf";
    public static final String TITLE_INFO_CLIENT = "Info about client: ";
    public static final String TITLE_INFO_CLIENTS = "List of clients";
    public static final String INFO_CLIENT_AGE = "Age: ";
    public static final String INFO_CLIENT_EMAIL = "Email: ";
    public static final String INFO_CLIENT_TELEPHONE = "Telephone: ";
    public static final String INFO_CLIENT_REGISTRATION_DATE = "Registration date: ";
    public static final List<String> FIELDS_CLIENT_DTO = List.of("#", "First name", "Last name", "Email", "Telephone", "Date of birth", "Registration date");
    public static final Integer DEFAULT_NUMBER_OF_CLIENTS_CREATED = 30;

}
