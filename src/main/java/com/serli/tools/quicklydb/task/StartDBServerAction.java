package com.serli.tools.quicklydb.task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.application.Task;

import com.serli.tools.quicklydb.AppQuiklydb;
import com.serli.tools.quicklydb.database.derby.DerbyUtil;

public class StartDBServerAction extends Task<Connection, Void> {

	private Logger logger = null;
	private String dbName = null;
	private int port = 0;
	private boolean create = true;
	private DerbyUtil dbu = null;

	public StartDBServerAction(AppQuiklydb app, String dbName, int port,
			boolean create) {
		super(app);
		logger = app.getLogger();
		this.dbName = dbName;
		this.port = port;
		this.create = create;
	}

	@Override
	protected Connection doInBackground() throws Exception {
		dbu = new DerbyUtil("localhost", port, dbName, create);
		return dbu.getConnection();
	}

	@Override
	protected void succeeded(Connection cnx) {
		try {
			if ( cnx != null && cnx.isValid( 1000 ) ) {
				((AppQuiklydb) getApplication()).setDerbyUtil(dbu);
				logger.log(Level.INFO, "SUCCESS");
			}
		} catch (SQLException e) {
			failed(e);
		}
	}

	@Override
	protected void failed(Throwable ex) {
		logger.log(Level.INFO, "ERROR");
		logger.log(Level.SEVERE,
				"Problème lors du démarrage du serveur Derby.", ex);
	}
}
