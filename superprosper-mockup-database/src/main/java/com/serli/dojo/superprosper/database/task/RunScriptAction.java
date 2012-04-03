package com.serli.dojo.superprosper.database.task;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.application.Task;

import com.serli.dojo.superprosper.database.DBMockupApplication;
import com.serli.dojo.superprosper.database.engine.DerbyEngine;

public class RunScriptAction extends Task<Void, Void> {
	
	private Logger logger = null;
	private DerbyEngine dbu = null;
	private String scriptPath = null;
	
	public RunScriptAction(DBMockupApplication app, DerbyEngine dbu, String scriptPath) {
		super(app);
		this.logger = app.getLogger();
		this.dbu = dbu;
		this.scriptPath = scriptPath;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		logger.log(Level.INFO, "Exécution du script : " + scriptPath);
		if (dbu != null) {
			try {
//				dbu.executeScript(scriptPath);	
			} catch (Exception ex) {
				logger.log(Level.SEVERE, "Erreur lors de la demande d'exécution du script.", ex);
			}
		} 
		else {
			logger.log(Level.WARNING, "Opération annulée : La base de donnée n'est pas démarrée!");
		}
		return null;
	}
	
	@Override
	protected void succeeded(Void v) {
		logger.log(Level.INFO, "SUCCESS");
	}
	
	@Override
	protected void failed(Throwable ex) {
		logger.log(Level.SEVERE, "Problème(s) lors de l'exécution du script.", ex);
		logger.log(Level.INFO, "ERROR");
	}
}
