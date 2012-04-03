package com.serli.dojo.superprosper.database.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import org.jdesktop.application.Task;

import com.serli.dojo.superprosper.database.DBMockupApplication;
import com.serli.dojo.superprosper.database.engine.DerbyEngine;
import com.serli.dojo.superprosper.database.view.RequestPanel;

public class RunSqlStatement extends Task<JTable, Void> {
	
	private Logger logger = null;
	private Connection conn = null;
	private String sqlRequest = null;
	private RequestPanel requestPane = null;
	
	/**
	 * Exécute une requête sql sur la base et affiche le resultat sous forme de table dans le <code>RequestPanel</code>.
	 * @param app l'application 
	 * @param conn la connexion sql
	 * @param sqlRequest la requête de type select
	 * @param requestPane le panneau ou afficher le resultat <code>JTable</code>
	 */
	public RunSqlStatement(DBMockupApplication app, Connection conn, String sqlRequest, RequestPanel requestPane) {
		super(app);
		this.logger = app.getLogger();
		this.conn = conn;
		this.sqlRequest = sqlRequest;
		this.requestPane = requestPane;
	}
	
	@Override
	protected JTable doInBackground() throws Exception {
		logger.log(Level.INFO, "Exécution de la requête : " + sqlRequest);
		JTable table = null;
		Vector<String> columnNames = null;
		Vector<Object> rowData = null;
		
		Statement statement = null;
		boolean result = false;
		try {
			statement = conn.createStatement();
			try {
				result = statement.execute(sqlRequest);
				if (result == true) {

					ResultSet rs = statement.getResultSet();
					ResultSetMetaData rsmd = rs.getMetaData();

					// Construction du nom des colonnes
					columnNames = new Vector<String>(rsmd.getColumnCount()); 
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						columnNames.add(rsmd.getColumnName(i));
					}

					// Construction des lignes
					rowData = new Vector<Object>();
					while (rs.next()) {
						Vector<Object> data = new Vector<Object>(columnNames.capacity());
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							data.add(rs.getObject(i));
						}
						rowData.add(data);
					}
					logger.log(Level.INFO, "Création et affichage de la table de résultat.");
					table = new JTable(rowData, columnNames);
					requestPane.setResultTable(table);
					
				} else {
					logger.log(Level.INFO,
							"La requête a retourné aucun résultat.");
				}
			} catch (SQLException e) {
				throw new Exception("Erreur lors de l'exécution de la requête.", e);
			}
		} catch (SQLException e) {
			throw new Exception("Erreur lors de la préparation de la requête.", e);
		}
		
		return table;
	}
	
	@Override
	protected void succeeded(JTable table) {
		logger.log(Level.INFO, "SUCCESS");	
	}
	
	@Override
	protected void failed(Throwable ex) {
		logger.log(Level.SEVERE, "Problème(s) lors de l'exécution de la requête.", ex);
		logger.log(Level.INFO, "ERROR");
	}
}
