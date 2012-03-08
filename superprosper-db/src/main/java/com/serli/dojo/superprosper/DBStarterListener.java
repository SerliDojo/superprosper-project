package com.serli.dojo.superprosper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.common.io.Resources;

/**
 * Application Lifecycle Listener implementation that starts an instance of DB
 * server.
 */
public class DBStarterListener implements ServletContextListener {

	private static final String EMBEDDED_DB_LOCATION_PROPERTY = "derby.system.home";
	public static final String EMBEDDED_DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String EMBEDDED_DB_URL = "jdbc:derby:EmbeddedDB";

	private static final Logger LOGGER = Logger
			.getLogger(DBStarterListener.class.getName());

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOGGER.info("Stopping embedded DB server...");
		try {
			DriverManager.getConnection(EMBEDDED_DB_URL + ";shutdown=true");
			LOGGER.info("Embedded DB server stopped");
		} catch (SQLException e) {
			LOGGER.severe("Unable to stop DB server: " + e.getClass() + ": "
					+ e.getMessage());
			LOGGER.throwing("DBStarterListener", "contextDestroyed", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOGGER.info("Starting embedded DB server...");

		String tempDBPath = event.getServletContext().getRealPath("/db");
		File tempDir = null;
		if (tempDBPath != null) {
			tempDir = new File(tempDBPath);
		} else {
			tempDir = Files.createTempDir();
		}

		Connection connection = null;
		try {
			System.setProperty(EMBEDDED_DB_LOCATION_PROPERTY, tempDir.getPath());
			LOGGER.info("Locating embedded DB server at "
					+ System.getProperty(EMBEDDED_DB_LOCATION_PROPERTY));
			Class.forName(EMBEDDED_DB_DRIVER).newInstance();
			connection = DriverManager.getConnection(EMBEDDED_DB_URL
					+ ";create=true");
			LOGGER.info("Embedded DB server started");
		} catch (Exception e) {
			LOGGER.severe("Unable to start DB server: " + e.getClass() + ": "
					+ e.getMessage());
			LOGGER.throwing("DBStarterListener", "contextInitialized", e);
		}

		File populateMarker = new File(tempDir.getPath(), ".populated");
		if (!populateMarker.exists()) {
			if (connection != null) {
				try {
					LOGGER.info("Populating embedded DB server...");
					populateMarker.createNewFile();
					String sql = Resources
							.toString(Resources.getResource("schema.sql"),
									Charsets.UTF_8);
					String[] statements = sql.split(";");
					for (String statement : statements) {
						if (!Strings.isNullOrEmpty(statement.trim())) {
							try {
								LOGGER.info("Executing: \n" + statement);
								connection.createStatement().executeUpdate(
										statement);
							} catch (SQLException e) {
								LOGGER.severe("Unable to write data to populate DB server: "
										+ e.getClass() + ": " + e.getMessage());
								LOGGER.throwing("DBStarterListener",
										"contextInitialized", e);
							}
						}
					}
				} catch (IOException e) {
					LOGGER.severe("Unable to read data to populate DB server: "
							+ e.getClass() + ": " + e.getMessage());
					LOGGER.throwing("DBStarterListener", "contextInitialized",
							e);
				}
			}
		}
	}

}
