package com.serli.dojo.superprosper.database;

import java.awt.Dimension;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;

import com.serli.dojo.superprosper.database.engine.DerbyEngine;
import com.serli.dojo.superprosper.database.task.RunScriptAction;
import com.serli.dojo.superprosper.database.task.RunSqlStatement;
import com.serli.dojo.superprosper.database.task.StartDBServerAction;
import com.serli.dojo.superprosper.database.task.StopDBServerAction;

/**
 * The main class of the application.
 */
public class DBMockupApplication extends SingleFrameApplication {

	private static Logger logger = Logger.getLogger("DBMockupApplication-logger");
	private DBMockupView mainView;
	private DerbyEngine engine;

	/**
	 * Main method launching the application.
	 */
	public static void main(String[] args) {
		launch(DBMockupApplication.class, args);
	}

	/**
	 * At startup create and show the main frame of the application.
	 */
	@Override
	protected void startup() {
		// Création de la fenêtre principale
		mainView = new DBMockupView(this);

		JFrame frame = mainView.getFrame();
		Dimension frameSize = new Dimension(750, 600);
		frame.setMinimumSize(frameSize);
		frame.setPreferredSize(frameSize);
		frame.setSize(frameSize);
		frame.setLocationRelativeTo(null);
		setMainFrame(frame);

		show(mainView);
	}

	public Logger getLogger() {
		return logger;
	}

	@Action
	public void startDatabase() {
		OutputStream outputStream = mainView.getLoggingPanel().getOutputStream();
		new StartDBServerAction(this, outputStream).execute();
	}

	@Action
	public void stopDatabase() {
		getLogger().log(Level.INFO, "STOPING Derby server...");
		new StopDBServerAction(this, engine).execute();
	}

	@Action
	public void exitApplication() {
		exit();
	}

	@Action
	public void clearLogs() {
		mainView.getLoggingPanel().clear();
	}

	@Action
	public void executeRequest() {
		String sqlRequest = mainView.getRequestPanel().getRequest();
		RunSqlStatement rss;
		try {
//			rss = new RunSqlStatement(this, this.engine.getConnection(), sqlRequest, mainView.getRequestPanel());
//			rss.execute();
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Erreur lors de la récupération de la connection sql.", e);
		}
	}

	@Action
	public void resetDataset() {
		getLogger().log(Level.INFO, "Demande de suppression des tables.");
		RunScriptAction rsad = new RunScriptAction(this, this.engine, "");
		rsad.execute();

		getLogger().log(Level.INFO, "Demande de création des tables.");
		RunScriptAction rsac = new RunScriptAction(this, this.engine, "");
		rsac.execute();

		getLogger().log(Level.INFO, "Demande de valorisation des tables.");
		RunScriptAction rsav = new RunScriptAction(this, this.engine, "");
		rsav.execute();
	}

	/**
	 * Définit l'instance DerbyEngine qui a démarré le serveur de base de
	 * donnée.
	 * 
	 * @param engine l'instance <code>DerbyEngine</code>
	 */
	public void setDerbyUtil(DerbyEngine engine) {
		this.engine = engine;

		mainView.getBtnStartDB().setEnabled(engine == null);
		mainView.getBtnStopDB().setEnabled(engine != null);
	}
}