/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.tools.quicklydb.database.derby;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.tools.ij;

/**
 * Permet de démarrer un serveur de base de données <b>Derby</b> de type embarqué mémoire ou
 * fichier ou de type network. Une fois le serveur créé, il est alors possible d'exécuter
 * des scripts SQL afin de créer les tables et de valoriser les champs.<br>
 * 
 * @author Pascal MERON
 * @version 1.1
 */
public class DerbyUtil {

	public enum Embedded {
		MEMORY, FILE
	}

	protected Connection connSql = null;
	protected StringBuilder strConn = null;
	protected NetworkServerControl serverControl = null;

	/**
	 * Création d'un serveur de base de donnée.
	 * 
	 * @param host
	 *            Nom de la machine serveur
	 * @param port
	 *            Port de connexion cliente
	 * @param dbName
	 *            Nom de la base de donnée à créer
	 * @throws Exception
	 */
	public DerbyUtil(String host, int port, String dbName, boolean create)
			throws Exception {
		createNetwork(host, port, dbName, create);
	}

	/**
	 * Création d'une base données embarquée.
	 * 
	 * @param dbName
	 *            Nom de la base de donnéeà créer
	 * @param typeEmbedded le type de base de donnée embarqué
	 * @throws Exception
	 * @see  {@link Embedded}
	 */
	public DerbyUtil(String dbName, Embedded typeEmbedded, boolean create)
			throws Exception {
		createEmbedded(dbName, typeEmbedded, create);
	}

	/**
	 * Créé un serveur de base de donnée sur une machine.
	 * @param host Nom de la machine serveur 
	 * @param port port du serveur
	 * @param dbName nom de la base de donnée
	 * @param create <code>true</code> indique que la base de donnée doit être créée
	 * @throws Exception
	 */
	private void createNetwork(String host, int port, String dbName, boolean create) throws Exception {
		// Démarrage du serveur de base de données Derby
	    serverControl = new NetworkServerControl(InetAddress.getByName(host),port);
	    serverControl.start(new PrintWriter(System.out));
	    
	    // On attend le démarrage du serveur qui est réalisé dans un autre thread. 
	    boolean pingOK = false;
	    short count = 0;
	    do {
	    	try {
	    		serverControl.ping();
	    		pingOK = true;
	    	} catch(Exception ex) {
	    		count++;
	    		Thread.sleep(250);
	    	}
	    
	    } while (!pingOK && count < 3);
	    	
	    // Connexion cliente sur la base de données derbyDB
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		strConn = new StringBuilder("jdbc:derby://");
		strConn.append(host);
		strConn.append(":");
		strConn.append(port);
		strConn.append("/");
		strConn.append(dbName);
		if (create == true) 
			strConn.append(";create=true");
		connSql = DriverManager.getConnection(strConn.toString());
	}

	private void createEmbedded(String dbName, Embedded typeEmbedded,
			boolean create) throws Exception {
		// Démarrage de la base de données Derby embarquée
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		strConn = new StringBuilder("jdbc:derby:");
		if (typeEmbedded == Embedded.MEMORY) {
			strConn.append("memory:");
		}
		strConn.append(dbName);
		if (create == true)
			strConn.append(";create=true");
		// Connexion sur la base de données derbyDB embarquée
		connSql = DriverManager.getConnection(strConn.toString());
	}

	/**
	 * Exécute un fichier de type script SQL sur la base de données. 
	 * @param resourcePath
	 *            le chemin du fichier script SQL
	 * @throws UnsupportedEncodingException
	 */
	public void executeRessourceScript(String resourcePath)
			throws UnsupportedEncodingException {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream is = cl.getResourceAsStream(resourcePath);
		// Exécute le scipt SQL
		ij.runScript(connSql, is, "UTF-8", System.out, "UTF-8");
	}

	/**
	 * Exécute un fichier de type script SQL sur la base de données.
	 * @param resourcePath
	 *            le chemin du fichier script SQL
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 *             , FileNotFoundException
	 */
	public void executeScript(String resourcePath)
			throws UnsupportedEncodingException, FileNotFoundException {
		InputStream is = new FileInputStream(resourcePath);
		// Exécute le scipt SQL
		ij.runScript(connSql, is, "UTF-8", System.out, "UTF-8");
	}

	public Connection getConnection() throws SQLException {
		if (connSql != null) {
			if (connSql.isValid(250) == false)	{		
				connSql = DriverManager.getConnection(strConn.toString());
			}
		}
		return connSql;
	}

	/**
	 * Ferme la connexion sql sur la base de donnée puis le serveur network si
	 * il en existe un.
	 * @throws Exception
	 */
	public void close() throws Exception {

		connSql.close();

		if (serverControl != null) {
			serverControl.shutdown();
		}

		connSql = null;
		serverControl = null;
	}

}
