package com.serli.sample.superprosper;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.derby.drda.NetworkServerControl;
import org.apache.derby.tools.ij;


public class Test {

    public static void main(String[] args) {
    	
//    	EntityManager em =  PersistenceUtil.getEntityManager();
//    	Runner r = new Runner();
//    	r.setFirstName("Pascal");
//    	r.setLastName("MERON");
//    	
//    	RunnerService rs = new RunnerService(em);
//    	rs.persist(r);


        	String driver = "org.apache.derby.jdbc.ClientDriver"; //"org.apache.derby.jdbc.EmbeddedDriver";
        	String protocol = "jdbc:derby://localhost:1528/";//"jdbc:derby:"; 
        	
        	
        	try {
        		
        		/* D�marrage du serveur de base de donn�es Derby*/
        	    NetworkServerControl serverControl = new NetworkServerControl(InetAddress.getByName("localhost"),1528);
        	    serverControl.start(new PrintWriter(System.out));
        	   	
        	    /* Connexion sur la base de donn�es derbyDB*/
    			Class.forName(driver).newInstance();
    	    	Connection conn = DriverManager.getConnection(protocol + "derbyDB;create=true;shutdown=true");
    	    	
    	    	/* Cr�ation de la base de donn�es [dataRunner] par script */
    	    	ClassLoader cl = Thread.currentThread().getContextClassLoader();
    	    	InputStream streamCreateTables = cl.getResourceAsStream("script/create-tables-dataRunner.sql");
    	    	InputStream streamPopulousTables = cl.getResourceAsStream("script/insert-data-in-dataRunner.sql");
    	    	InputStream streamDeleteTables = cl.getResourceAsStream("script/delete-tables-dataRunner.sql");
    	    	
    	    	// Cr�ation des tables et contraintes
    	        ij.runScript(conn, streamCreateTables, "UTF-8", System.out, "UTF-8");
    	        // Valorisation des tables
    	        ij.runScript(conn, streamPopulousTables, "UTF-8", System.out, "UTF-8");
    	        
    	        // TODO : Cr�er des Tests
    	        
    	    	/* Suppresion des tables de la base de donn�e [dataRunner] */
    	        ij.runScript(conn, streamDeleteTables, "UTF-8", System.out, "UTF-8");
    	       
    	    	serverControl.shutdown();
    	    	
    		} catch (Exception e) {
    			e.printStackTrace();
    		} 

    }
}
