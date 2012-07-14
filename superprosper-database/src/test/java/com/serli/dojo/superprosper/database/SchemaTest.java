package com.serli.dojo.superprosper.database;

import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.junit.Test;

public class SchemaTest {

	@Test
	public void testExecute() {
		try {
			Class.forName("org.h2.Driver");
			Connection sqlConnection = DriverManager.getConnection("jdbc:h2:mem:TestDB", "dojo", "dojo");

			RunScript.execute(sqlConnection, new StringReader("DROP ALL OBJECTS;"));
			RunScript.execute(sqlConnection, new InputStreamReader(SchemaTest.class.getClassLoader()
					.getResourceAsStream("schema/schema.sql")));
		} catch (ClassNotFoundException e) {
			fail("Impossible de trouver le driver");
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Erreur dans l'exécution du schéma : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
