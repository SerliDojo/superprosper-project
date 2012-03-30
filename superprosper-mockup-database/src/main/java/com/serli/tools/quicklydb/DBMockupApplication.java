package com.serli.tools.quicklydb;

import java.awt.Dimension;
import java.io.File;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.myjavadev.utility.LoggingFormatter;
import org.myjavadev.utility.TextAreaLogHandler;
import org.myjavadev.utility.TextAreaOutputStream;

import com.serli.tools.quicklydb.database.derby.DerbyUtil;
import com.serli.tools.quicklydb.filter.SqlFileFilter;
import com.serli.tools.quicklydb.task.RunScriptAction;
import com.serli.tools.quicklydb.task.RunSqlStatement;
import com.serli.tools.quicklydb.task.StartDBServerAction;
import com.serli.tools.quicklydb.task.StopDBServerAction;
import com.serli.tools.quicklydb.view.ConfigurationPanel;
import com.serli.tools.quicklydb.view.DBMockupView;
import com.serli.tools.quicklydb.view.SqlFileView;

/**
 * The main class of the application.
 */
public class DBMockupApplication extends SingleFrameApplication {

	private static Logger logger = Logger.getLogger("DBMockupApplication-logger");
	private DBMockupView mainView = null;
	private DerbyUtil dbu = null;

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

		// Initialisation des chemins des scripts par défaut
		String FILE_SEPARATOR = System.getProperty("file.separator");
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getFile();
		if (path.endsWith(".jar")) {
			path = path.substring(0, path.lastIndexOf(FILE_SEPARATOR) + 1);
		}

//		mainView.getScriptLauncherPanel().setPathCreateTables(
//				path + "create-tables.sql");
//		mainView.getScriptLauncherPanel().setPathPopulateTables(
//				path + "populate-tables.sql");
//		mainView.getScriptLauncherPanel().setPathDeleteTables(
//				path + "delete-tables.sql");

		show(mainView);
	}

	/**
	 * Initialisation du système de log
	 */
	public void initLogger(JTextArea txtLog) {

		try {
			// String FILE_SEPARATOR = System.getProperty("file.separator");
			LoggingFormatter lf = new LoggingFormatter("HH:mm:ss");
			TextAreaLogHandler vh = new TextAreaLogHandler(txtLog);
			vh.setFormatter(lf);
			logger.addHandler(vh);
			logger.setLevel(Level.ALL);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void shutdown() {
		System.out.println("shutdown");
	}

	/**
	 * This method is to initialize the specified window by injecting resources.
	 * Windows shown in our application come fully initialized from the GUI
	 * builder, so this additional configuration is not needed.
	 */
	@Override
	protected void configureWindow(java.awt.Window root) {
		System.out.println(root);
	}

	/**
	 * A convenient static getter for the application instance.
	 * 
	 * @return the instance of appQuiklydb
	 */
	public static DBMockupApplication getApplication() {
		return Application.getInstance(DBMockupApplication.class);
	}

	/**
	 * Main method launching the application.
	 */
	public static void main(String[] args) {
		launch(DBMockupApplication.class, args);
	}

	@Action
	public void stopDBServer() {
		StringBuilder sb = new StringBuilder("STOPING Derby server...");
		getLogger().log(Level.INFO, sb.toString());
		StopDBServerAction srv = new StopDBServerAction(this, dbu);
		srv.execute();
	}

	@Action
	public void startDBServer() {
		ConfigurationPanel cfg = mainView.getConfigurationPanel();
		String dbName = cfg.getDBName();
		int port = cfg.getDBPort();
		boolean create = cfg.getDBCreate();

		StringBuilder sb = new StringBuilder("STARTING Derby server...");
		sb.append("\r\n\tDBName = ").append(dbName);
		sb.append("\r\n\tPort = ").append(port);
		sb.append("\r\n\tcreate = ").append(create);
		getLogger().log(Level.INFO, sb.toString());

		TextAreaOutputStream taos = new  TextAreaOutputStream(mainView.getLoggingPanel().getTextArea());
		StartDBServerAction srv = new StartDBServerAction(this, dbName, port,
				create, taos);
		srv.execute();
	}

	@Action
	public void exitApplication() {
		DBMockupApplication.getApplication().exit();
	}

	@Action
	public void clearLog() {
		mainView.getLoggingPanel().getTextArea().setText("");
	}

	@Action
	public void executeRequest() {
		String sqlRequest = mainView.getRequestPanel().getRequest();
		RunSqlStatement rss;
		try {
			rss = new RunSqlStatement(this, this.dbu.getConnection(), sqlRequest, mainView.getRequestPanel());
			rss.execute();
		} catch (Exception e) {
			getLogger().log(Level.SEVERE,
					"Erreur lors de la récupération de la connection sql.", e);
		}
	}

	@Action
	public void clearRequest() {
		mainView.getRequestPanel().clearRequest();
	}

	@Action
	public void createTables() {
		getLogger().log(Level.INFO, "Demande de création des tables.");
		String scriptPath = mainView.getScriptLauncherPanel()
				.getPathCreateTables();
		RunScriptAction rsa = new RunScriptAction(this, this.dbu, scriptPath);
		rsa.execute();
	}

	@Action
	public void populateTables() {
		getLogger().log(Level.INFO, "Demande de valorisation des tables.");
		String scriptPath = mainView.getScriptLauncherPanel()
				.getPathPopulateTables();
		RunScriptAction rsa = new RunScriptAction(this, this.dbu, scriptPath);
		rsa.execute();
	}

	@Action
	public void deleteTables() {
		getLogger().log(Level.INFO, "Demande de suppression des tables.");
		String scriptPath = mainView.getScriptLauncherPanel()
				.getPathDeleteTables();
		RunScriptAction rsa = new RunScriptAction(this, this.dbu, scriptPath);
		rsa.execute();
	}

	@Action
	public void chooseScriptCreateTables() {
		String path = mainView.getScriptLauncherPanel().getPathCreateTables();
		path = selectSrciptFile(path);
		mainView.getScriptLauncherPanel().setPathCreateTables(path);
	}

	@Action
	public void chooseScriptPopulateTables() {
		String path = mainView.getScriptLauncherPanel().getPathPopulateTables();
		path = selectSrciptFile(path);
		mainView.getScriptLauncherPanel().setPathPopulateTables(path);
	}

	@Action
	public void chooseScriptDeleteTables() {
		String path = mainView.getScriptLauncherPanel().getPathDeleteTables();
		path = selectSrciptFile(path);
		mainView.getScriptLauncherPanel().setPathDeleteTables(path);
	}

	/**
	 * Permet de sélectionner un fichier de type <i>.sql</i> et de retourner son
	 * chemin.
	 * 
	 * @param path
	 *            le chemin de départ de la selection
	 * @return le path du fichier sql selectionné.
	 */
	private String selectSrciptFile(String path) {
		File file = new File(path);
		JFileChooser fc = new JFileChooser(file);

		fc.setFileFilter(new SqlFileFilter());
		fc.setFileView(new SqlFileView());

		int returnVal = fc.showOpenDialog(getMainFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
		return file.getPath();
	}

	/**
	 * Définit l'instance DerbyUtil qui a démarré le serveur de base de donnée.
	 * 
	 * @param dbu
	 *            l'instance <code>DerbyUtil</code>
	 */
	public void setDerbyUtil(DerbyUtil dbu) {

		this.dbu = dbu;

		if (dbu != null) {
			try {
				if (dbu.getConnection().isValid(1500)) {
					mainView.getBtnStartDB().setEnabled(false);
					mainView.getBtnStopDB().setEnabled(true);
				}
			} catch (Exception ex) {
				getLogger().log(Level.SEVERE,
						"La connexion sur la base est invalide.", ex);
				StopDBServerAction srv = new StopDBServerAction(this, dbu);
				srv.execute();
			}
		} else {
			mainView.getBtnStopDB().setEnabled(false);
			mainView.getBtnStartDB().setEnabled(true);
		}
	}

	/**
	 * Obtient la connexion SQL sur la base de donnée Derby activée.
	 * 
	 * @return une connexion SQL
	 */
	public Connection getDBConnection() {
		Connection conn = null;
		if (dbu != null) {
			try {
				conn = dbu.getConnection();
			} catch (Exception e) {
				getLogger()
						.log(Level.SEVERE,
								"Erreur lors de la récupération de la connexion sur la base de donnée.",
								e);
			}
		}
		return conn;
	}
}