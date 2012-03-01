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

import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.tools.ij;


/**
 * Permet de démarrer un serveur de base de données <b>Derby</b> de type MEMORY, FILE ou NETWORK.
 * Une fois le serveur créé, il est alors possible d'exécuter des scripts SQL afin de créer les tables,
 * valoriser les champs.
 * <div>
 * TODO : 
 * <ul>
 * <li> Rendre public les méthodes <code>createEmbedded</code> et <code>createNetwork</code> puis 
 * retourner la connexion <code>java.sql.Connection</code></li>
 * <li> Ajouter un paramètre de type <code>java.sql.Connection</code> à la méthode <code>executeScript(String resourcePath)</code></li>
 * <li> Ajouter les méthodes permettant de créer les <code>java.sql.Connection</code> avec les mêmes signatures que les méthodes 
 * <code>createEmbedded</code> et <code>createNetwork</code></li>
 * </ul>
 * </div>
 * @author Pascal MERON
 * @version 1.0
 */
public class DerbyUtil {
	
	public enum Embedded { MEMORY, FILE }
	
	protected Connection connSql = null;
	protected NetworkServerControl serverControl = null;
	
	/**
	 * Cr�ation d'un serveur de base de donn�e.
	 * @param host Nom de la machine serveur
	 * @param port Port de connexion cliente
	 * @param dbName Nom de la base de donn�e � cr�er
	 * @throws Exception
	 */
	public DerbyUtil(String host, int port, String dbName, boolean create) throws Exception {
		createNetwork(host, port, dbName,create);
	}
	
	/**
	 * Cr�ation d'une base donn�es embarqu�e.
	 * @param dbName Nom de la base de donn�e � cr�er
	 * @param typeEmbedded 
	 * @throws Exception
	 */
	public DerbyUtil(String dbName, Embedded typeEmbedded, boolean create) throws Exception {
		createEmbedded(dbName, typeEmbedded, create);
	}
	
	private void createNetwork(String host, int port, String dbName, boolean create) throws Exception {
		// D�marrage du serveur de base de donn�es Derby
	    serverControl = new NetworkServerControl(InetAddress.getByName(host),port);
	    serverControl.start(new PrintWriter(System.out));
	    
	    // Connexion cliente sur la base de donn�es derbyDB
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		StringBuilder sb = new StringBuilder("jdbc:derby://");
		sb.append(host);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(dbName);
		if (create == true) 
			sb.append(";create=true");
    
		connSql = DriverManager.getConnection(sb.toString());
	}
	
	private void createEmbedded(String dbName, Embedded typeEmbedded, boolean create) throws Exception {
		// D�marrage de la base de donn�es Derby embarqu�e 
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		StringBuilder sb = new StringBuilder("jdbc:derby:");
		if (typeEmbedded == Embedded.MEMORY) {
			sb.append("memory:");
		}
		sb.append(dbName);
		if (create == true) 
			sb.append(";create=true");
		// Connexion sur la base de donn�es derbyDB embarqu�e 
		connSql = DriverManager.getConnection(sb.toString());
	}
	
	/**
	 * Ex�cute un fichier de type script SQL sur la base de donn�es.
	 * @param resourcePath le chemin du fichier script SQL
	 * @throws UnsupportedEncodingException
	 */
	public void executeRessourceScript(String resourcePath) throws UnsupportedEncodingException {
    	ClassLoader cl = Thread.currentThread().getContextClassLoader();
    	InputStream is = cl.getResourceAsStream(resourcePath);	
    	// Ex�cute le scipt SQL
        ij.runScript(connSql, is, "UTF-8", System.out, "UTF-8");
	}
	
	/**
	 * Ex�cute un fichier de type script SQL sur la base de donn�es.
	 * @param resourcePath le chemin du fichier script SQL
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException, FileNotFoundException
	 */
	public void executeScript(String resourcePath) throws UnsupportedEncodingException, FileNotFoundException {
    	InputStream is =  new FileInputStream(resourcePath);
    	// Ex�cute le scipt SQL
        ij.runScript(connSql, is, "UTF-8", System.out, "UTF-8");
	}
	
	public Connection getConnection() {
		return connSql;
	}
	
	
	/**
	 * Ferme la connexion sql sur la base de donn�e puis le serveur network si il en existe un.
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
