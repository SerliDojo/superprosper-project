/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.dojo.superprosper.database.engine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.tools.ij;

/**
 * Permet de démarrer un serveur de base de données <b>Derby</b> de type
 * network. Une fois le serveur créé, il est alors possible d'exécuter des
 * requêtes SQL.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
public class DerbyEngine {

	private static final String UNABLE_TO_START_MESSAGE = "Impossible de démarrer le serveur Derby.";
	private static final int PORT = 1527;
	private static final String HOST_NAME = "localhost";
	private static final String DATABASE_NAME = "CodingDojoDB";
	private static final String CONNECTION_URL = String.format("jdbc:derby://%s:%d/%s;create=true", HOST_NAME, PORT,
			DATABASE_NAME);

	protected NetworkServerControl serverControl = null;
	protected OutputStream out = System.out;

	/**
	 * Créé un serveur de base de donnée de type network.
	 * 
	 * @throws Exception
	 */
	public void start(OutputStream outputStream) {
		OutputStream traceStream = System.out;
		if (outputStream != null) {
			traceStream = outputStream;
		}

		// Démarrage du serveur de base de données Derby
		try {
			serverControl = new NetworkServerControl(InetAddress.getByName(HOST_NAME), PORT);
			serverControl.start(new PrintWriter(new OutputStreamWriter(traceStream, "UTF-8")));
		} catch (Exception e) {
			throw new RuntimeException(UNABLE_TO_START_MESSAGE, e);
		}

		// On attend le démarrage du serveur qui a lieu dans un autre thread.
		boolean pingOK = false;
		short count = 0;
		do {
			try {
				serverControl.ping();
				pingOK = true;
			} catch (Exception ex) {
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		} while (!pingOK && count < 3);

		if (!pingOK) {
			throw new RuntimeException(UNABLE_TO_START_MESSAGE);
		}
	}

	/**
	 * Arrête le serveur Derby si il en existe un.
	 * 
	 * @throws Exception
	 */
	public void stop() throws Exception {
		if (serverControl != null) {
			serverControl.shutdown();
			serverControl = null;
		}
	}

	/**
	 * Exécute unerequête SQL issue d'un flux sur la base de données.
	 * 
	 * @throws ClassNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public void executeSql(InputStream inputStream, OutputStream outputStream) throws ClassNotFoundException,
			UnsupportedEncodingException, SQLException {

		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection sqlConnection = null;
		try {
			sqlConnection = DriverManager.getConnection(CONNECTION_URL);
			// ClassLoader cl = Thread.currentThread().getContextClassLoader();
			// InputStream is = cl.getResourceAsStream(resourcePath);
			// Exécute le scipt SQL
			ij.runScript(sqlConnection, inputStream, "UTF-8", outputStream, "UTF-8");
		} finally {
			if (sqlConnection != null) {
				try {
					sqlConnection.close();
				} catch (SQLException e) {
					sqlConnection = null;
				}
			}
		}
	}

}
