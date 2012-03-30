package com.serli.tools.quicklydb.task;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.application.Task;

import com.serli.tools.quicklydb.DBMockupApplication;
import com.serli.tools.quicklydb.database.derby.DerbyUtil;

public class StopDBServerAction extends Task<Void, Void> {

	private Logger logger = null;
	private DerbyUtil dbu = null;
	
	public StopDBServerAction(DBMockupApplication app, DerbyUtil dbu) {
		super(app);
		logger = app.getLogger();
		this.dbu = dbu;
	}

	@Override
	protected Void doInBackground() throws Exception {
		dbu.close();	
		return null;
	}
	
	@Override
	protected void succeeded(Void v) {
		dbu = null;
		((DBMockupApplication)getApplication()).setDerbyUtil(dbu);
		logger.log(Level.INFO, "SUCCESS");
	}
	
	@Override
	protected void failed(Throwable ex) {
		logger.log(Level.SEVERE, "Problème lors de l'arrêt du serveur Derby.", ex);
		logger.log(Level.INFO, "ERROR");
	}
}
