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

public class QuiklydbMainView extends FrameView {
    
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
	public QuiklydbMainView(SingleFrameApplication app) {
		 super(app);
			
		initComponents();
		initActions();
		
		// "enable est affecté à true" par initActions !!!
		btnStopDB.setEnabled( !btnStartDB.isEnabled() );
	}
	
	private void initActions() {
		
		ActionMap actionMap = Application.getInstance().getContext().getActionMap();
		
		btnStartDB.setAction(actionMap.get("startDBServer"));
		btnStopDB.setAction(actionMap.get("stopDBServer"));
		btnExitApplication.setAction(actionMap.get("exitApplication"));
	}
	
	private void initComponents() {		
		JToolBar toolBar = new JToolBar("DB Server");
		btnStartDB = new JButton();
		btnStopDB = new JButton();
		btnExitApplication = new JButton();
//		btnStopDB.setEnabled( !btnStartDB.isEnabled() );
		toolBar.add(btnStartDB);
		toolBar.add(btnStopDB);
		toolBar.add(btnExitApplication);
		setToolBar(toolBar);
		
		ResourceMap resourceMap = Application.getInstance().getContext().getResourceMap(QuiklydbMainView.class);
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		configurationPanel = new ConfigurationPanel();
		scriptLauncherPanel = new ScriptLauncherPanel();
		requestPanel = new RequestPanel();
		loggingPanel = new LoggingPanel();
		tabbedPane.addTab("", resourceMap.getIcon("ConfigurationPanel.icon"),
				configurationPanel, resourceMap.getString("TabbedPanel.configuration.tooltip", ""));
		tabbedPane.addTab("", resourceMap.getIcon("ScriptLauncherPanel.icon"),
				scriptLauncherPanel, resourceMap.getString("TabbedPanel.usfuls.tooltip", ""));
		tabbedPane.addTab("", resourceMap.getIcon("RequestPanel.icon"),
				requestPanel, resourceMap.getString("TabbedPanel.request.tooltip", ""));
//		tabbedPane.addTab("", resourceMap.getIcon("LoggingPanel.icon"),
//				loggingPanel, resourceMap.getString("TabbedPanel.logging.tooltip", ""));

		JSplitPane splitPane = new JSplitPane ();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(tabbedPane);
		splitPane.setBottomComponent(loggingPanel);
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
