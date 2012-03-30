package com.serli.tools.quicklydb.task;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.application.Task;

import com.serli.tools.quicklydb.DBMockupApplication;
import com.serli.tools.quicklydb.database.derby.DerbyUtil;

public class StartDBServerAction extends Task<Connection, Void> {

	private Logger logger = null;
	private String dbName = null;
	private int port = 0;
	private boolean create = true;
	private DerbyUtil dbu = null;
	private OutputStream out = null;

	public StartDBServerAction(DBMockupApplication app, String dbName, int port,
			boolean create, OutputStream out) {
		super(app);
		logger = app.getLogger();
		this.dbName = dbName;
		this.port = port;
		this.create = create;
		this.out = out;
	}

	@Override
	protected Connection doInBackground() throws Exception {
		dbu = new DerbyUtil("localhost", port, dbName, create, out);
		return dbu.getConnection();
	}

	@Override
	protected void succeeded(Connection cnx) {
		try {
			if ( cnx != null && cnx.isValid( 1000 ) ) {
				((DBMockupApplication) getApplication()).setDerbyUtil(dbu);
				logger.log(Level.INFO, "SUCCESS");
			}
		} catch (SQLException e) {
			failed(e);
		}
	}

	@Override
	protected void failed(Throwable ex) {
		logger.log(Level.SEVERE,
				"Problème lors du démarrage du serveur Derby.", ex);
		logger.log(Level.INFO, "ERROR");
	}
}
