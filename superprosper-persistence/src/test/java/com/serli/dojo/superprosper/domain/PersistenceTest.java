package com.serli.dojo.superprosper.domain;

import static org.junit.Assert.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Resources;

public abstract class PersistenceTest {

	public static final String PERSISTENCE_UNIT_NAME = "superprosper-test-pu";

	private static JdbcDatabaseTester dbTester;

	private static EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	@BeforeClass
	public static void setUpClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		String jdbcDriver = entityManagerFactory.getProperties().get("javax.persistence.jdbc.driver").toString();
		String jdbcUrl = entityManagerFactory.getProperties().get("javax.persistence.jdbc.url").toString();
		String jdbcUser = entityManagerFactory.getProperties().get("javax.persistence.jdbc.user").toString();
		String jdbcPassword = entityManagerFactory.getProperties().get("javax.persistence.jdbc.password").toString();
		dbTester = new JdbcDatabaseTester(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);

		String schemaSql = Resources.toString(Resources.getResource("schema/schema.sql"), Charsets.UTF_8);
		Iterable<String> schemaQueries = Splitter.on(';').trimResults().omitEmptyStrings().split(schemaSql);
		for (String schemaQuery : schemaQueries) {
			dbTester.getConnection().getConnection().createStatement().executeUpdate(schemaQuery);
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		String jdbcUrl = entityManagerFactory.getProperties().get("javax.persistence.jdbc.url").toString();
		jdbcUrl = jdbcUrl.replace("create=true", "drop=true");
		try {
			DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			assertEquals("08006", e.getSQLState());
		}
	}

	@Before
	public void setUp() throws Exception {
		entityManager = entityManagerFactory.createEntityManager();
		// entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
//		entityManager.getTransaction().commit();
	}

	protected void insertDataSet(String dataSetName) throws Exception {
		IDataSet dataSet = new FlatXmlDataFileLoader().load("/" + dataSetName + ".xml");
		DatabaseOperation.CLEAN_INSERT.execute(dbTester.getConnection(), dataSet);
	}
}
