package com.example.dbunit;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.dbunit.configuration.DbUnitConfiguration;
import com.example.dbunit.model.DbUnitDataSource;

public class BaseDbUnitTest {

	private IDatabaseConnection iDatabaseConnection;

	@Autowired
	private DbUnitConfiguration dbUnitConfiguration;

	@Before
	public void setup() throws Exception {
		setupDatabase();
	}

	private void createDatabase() throws Exception {
		byte[] encoded = Files.readAllBytes(Paths.get(dbUnitConfiguration.getSchema()));
		String sql = new String(encoded, StandardCharsets.UTF_8);

		iDatabaseConnection = getDatabaseConnection();
		PreparedStatement preparedStatement = iDatabaseConnection.getConnection().prepareStatement(sql);
		preparedStatement.executeUpdate();
	}

	private IDataSet getDataSet() throws Exception {
		InputStream fileInInputStream = getClass().getClassLoader().getResourceAsStream(dbUnitConfiguration.getDataset());
		IDataSet dataset = new FlatXmlDataSetBuilder().build(fileInInputStream);
		return dataset;
	}

	private IDatabaseConnection getDatabaseConnection() throws Exception {
		Connection jdbcConnection = dbUnitConfiguration.getDataSource().getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}

	private void setupDatabase() throws Exception {
		createDatabase();
		DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, getDataSet());
	}

	public void tearDown() throws Exception {

	}

	public DbUnitDataSource createDataSourceEntity() {
		DbUnitDataSource dbUnitDataSource = new DbUnitDataSource();
		dbUnitDataSource.setDataset(dbUnitConfiguration.getDataset());
		dbUnitDataSource.setDriver(dbUnitConfiguration.getDriver());
		dbUnitDataSource.setPassword(dbUnitConfiguration.getPassword());
		dbUnitDataSource.setSchema(dbUnitConfiguration.getSchema());
		dbUnitDataSource.setUrl(dbUnitConfiguration.getUrl());
		dbUnitDataSource.setUser(dbUnitConfiguration.getUser());
		return dbUnitDataSource;
	}
}