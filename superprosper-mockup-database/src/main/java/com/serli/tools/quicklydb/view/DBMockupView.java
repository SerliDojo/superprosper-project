package com.serli.tools.quicklydb.view;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;

public class DBMockupView extends FrameView {
    
	private JButton btnStartDB;
	private JButton btnStopDB;
	private JButton btnExitApplication;
	private JTabbedPane tabbedPane;
	private ConfigurationPanel configurationPanel;
	private ScriptLauncherPanel scriptLauncherPanel;
	private RequestPanel requestPanel;
	private LoggingPanel loggingPanel;

	/**
	 * Create the frame.
	 */
	public DBMockupView(SingleFrameApplication app) {
		 super(app);
			
		initComponents();
		initActions();
		
		// "enable est affecté à true" par initActions !!!
		btnStopDB.setEnabled( !btnStartDB.isEnabled() );
	}
	
	private void initActions() {
		
		ActionMap actionMap = getApplication().getContext().getActionMap();
		
		btnStartDB.setAction(actionMap.get("startDBServer"));
		btnStopDB.setAction(actionMap.get("stopDBServer"));
		btnExitApplication.setAction(actionMap.get("exitApplication"));
	}
	
	private void initComponents() {		
		btnStartDB = new JButton();
		btnStopDB = new JButton();
		btnExitApplication = new JButton();
//		btnStopDB.setEnabled( !btnStartDB.isEnabled() );

		JToolBar toolBar = new JToolBar("DB Server");
		toolBar.add(btnStartDB);
		toolBar.add(btnStopDB);
		toolBar.add(btnExitApplication);
		setToolBar(toolBar);
		
		requestPanel = new RequestPanel();
		loggingPanel = new LoggingPanel();

		JSplitPane splitPane = new JSplitPane ();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(requestPanel);
		splitPane.setBottomComponent(loggingPanel);
		splitPane.setDividerLocation(400);
		setComponent(splitPane);
	}

	public JButton getBtnStartDB() {
		return btnStartDB;
	}

	public JButton getBtnStopDB() {
		return btnStopDB;
	}

	public ConfigurationPanel getConfigurationPanel() {
		return configurationPanel;
	}

	public ScriptLauncherPanel getScriptLauncherPanel() {
		return scriptLauncherPanel;
	}

	public RequestPanel getRequestPanel() {
		return requestPanel;
	}

	public LoggingPanel getLoggingPanel() {
		return loggingPanel;
	}
}
