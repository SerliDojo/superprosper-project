package com.serli.dojo.superprosper.database;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.RunScript;

public class H2Database {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String filename = "~/CodingDojoDB";
		if (args.length > 0)
			filename = args[0];
		Class.forName("org.h2.Driver");
		Connection sqlConnection = DriverManager.getConnection("jdbc:h2:" + filename);

		RunScript.execute(sqlConnection, new StringReader("DROP ALL OBJECTS;"));
		for (String file : new String[] { "schema/schema.sql", "dataset/data-textes.sql", "dataset/data-agents.sql",
				"dataset/data-clients.sql", "dataset/data-prospections.sql", "dataset/data-contrats.sql", "dataset/data-contrats-produits.sql" }) {
			RunScript.execute(sqlConnection, new InputStreamReader(H2Database.class.getClassLoader().getResourceAsStream(file)));
		}
	}
}
