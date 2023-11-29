package ru.clevertec.patterns.task.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.patterns.task.database.postgresql.JDBCPostgreSQLConnection;
import ru.clevertec.patterns.task.entity.model.Client;
import ru.clevertec.patterns.task.repository.ClientSortingRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientSortingRepositoryImpl implements ClientSortingRepository {

	private static final Logger logger = LoggerFactory.getLogger(ClientSortingRepositoryImpl.class);

	@Override
	public List<Client> sortingClientsByFirstName() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Connection connection = null;
		List<Client> clients = new ArrayList<>();
		try {
			connection = JDBCPostgreSQLConnection.getConnection();
			String selectTableSQL = "select * from clients as c order by c.first_name";
			PreparedStatement pstmt = connection.prepareStatement(selectTableSQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Long clientId = Long.parseLong(rs.getString("id"));
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String birthdayString = rs.getString("birthday");
				LocalDate birthday = LocalDate.parse(birthdayString, formatterForLocalDate);
				String registrationTimeString = rs.getString("registration_date");
				LocalDateTime registrationDate = LocalDateTime.parse(registrationTimeString, formatter);
				Client client = new Client(clientId, firstName, lastName, email, telephone, birthday, registrationDate);
				clients.add(client);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return clients;
	}

	@Override
	public List<Client> sortingClientsByAgeDesc() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Connection connection = null;
		List<Client> clients = new ArrayList<>();
		try {
			connection = JDBCPostgreSQLConnection.getConnection();
			String selectTableSQL = "select * from clients as c order by c.birthday desc";
			PreparedStatement pstmt = connection.prepareStatement(selectTableSQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Long clientId = Long.parseLong(rs.getString("id"));
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String birthdayString = rs.getString("birthday");
				LocalDate birthday = LocalDate.parse(birthdayString, formatterForLocalDate);
				String registrationTimeString = rs.getString("registration_date");
				LocalDateTime registrationDate = LocalDateTime.parse(registrationTimeString, formatter);
				Client client = new Client(clientId, firstName, lastName, email, telephone, birthday, registrationDate);
				clients.add(client);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return clients;
	}

	@Override
	public List<Client> sortingClientsByRegistrationDateDesc() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Connection connection = null;
		List<Client> clients = new ArrayList<>();
		try {
			connection = JDBCPostgreSQLConnection.getConnection();
			String selectTableSQL = "select * from clients as c order by c.registration_date desc";
			PreparedStatement pstmt = connection.prepareStatement(selectTableSQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Long clientId = Long.parseLong(rs.getString("id"));
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String birthdayString = rs.getString("birthday");
				LocalDate birthday = LocalDate.parse(birthdayString, formatterForLocalDate);
				String registrationTimeString = rs.getString("registration_date");
				LocalDateTime registrationDate = LocalDateTime.parse(registrationTimeString, formatter);
				Client client = new Client(clientId, firstName, lastName, email, telephone, birthday, registrationDate);
				clients.add(client);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return clients;
	}

	@Override
	public List<Client> getClientsOlderThan(Integer age) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Connection connection = null;
		List<Client> clients = new ArrayList<>();
		try {
			connection = JDBCPostgreSQLConnection.getConnection();
			String selectTableSQL = "select * from clients as c " +
					"where (SELECT date_part('year', age(c.birthday))) > " + age + " " +
					"order by c.birthday desc";
			PreparedStatement pstmt = connection.prepareStatement(selectTableSQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Long clientId = Long.parseLong(rs.getString("id"));
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String birthdayString = rs.getString("birthday");
				LocalDate birthday = LocalDate.parse(birthdayString, formatterForLocalDate);
				String registrationTimeString = rs.getString("registration_date");
				LocalDateTime registrationDate = LocalDateTime.parse(registrationTimeString, formatter);
				Client client = new Client(clientId, firstName, lastName, email, telephone, birthday, registrationDate);
				clients.add(client);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return clients;
	}

	public void executeStatement(String sql) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = JDBCPostgreSQLConnection.getConnection();
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
