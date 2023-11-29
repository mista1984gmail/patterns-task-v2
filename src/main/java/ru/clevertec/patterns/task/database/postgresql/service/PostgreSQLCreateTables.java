package ru.clevertec.patterns.task.database.postgresql.service;

import ru.clevertec.patterns.task.database.postgresql.JDBCPostgreSQLCreateTable;
import ru.clevertec.patterns.task.database.tables.JDBCCreateTableClient;

import java.util.HashMap;
import java.util.Map;

public class PostgreSQLCreateTables {

    private Map<String, String> createTables = new HashMap<>();

    public void createTablesInDataBase() {
        createTables.put(JDBCCreateTableClient.ACCOUNT_TABLE_NAME, JDBCCreateTableClient.TABLE_ACCOUNT_SQL_CREATE);
        JDBCPostgreSQLCreateTable jdbcPostgreSQLCreateTable = new JDBCPostgreSQLCreateTable();
        jdbcPostgreSQLCreateTable.createTables(createTables);
    }

}
