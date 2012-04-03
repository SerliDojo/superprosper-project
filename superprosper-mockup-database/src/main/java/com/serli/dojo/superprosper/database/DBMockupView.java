package com.serli.dojo.superprosper.database;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

import com.serli.dojo.superprosper.database.logging.LoggingPanel;
import com.serli.dojo.superprosper.database.view.RequestPanel;

public class DBMockupView extends FrameView {

	private JButton btnExitApplication;
	private JButton btnStartDatabase;
	private JButton btnResetDataset;
	private JButton btnStopDatabase;
	private JButton btnExecuteRequest;
	private JButton btnClearLogs;

	private RequestPanel requestPanel;
	private LoggingPanel loggingPanel;

	/**
	 * Create the frame.
	 */
	public DBMockupView(SingleFrameApplication app) {
		super(app);

		ActionMap actionMap = getApplication().getContext().getActionMap();

		btnExitApplication = new JButton(actionMap.get("exitApplication"));
		btnStartDatabase = new JButton(actionMap.get("startDatabase"));
		btnResetDataset = new JButton(actionMap.get("resetDataset"));
		btnStopDatabase = new JButton(actionMap.get("stopDatabase"));
		btnExecuteRequest = new JButton(actionMap.get("executeRequest"));
		btnClearLogs = new JButton(actionMap.get("clearLogs"));

		JToolBar toolBar = new JToolBar("Commandes");
		toolBar.add(btnExitApplication);
		toolBar.addSeparator();
		toolBar.add(btnStartDatabase);
		toolBar.add(btnResetDataset);
		toolBar.add(btnStopDatabase);
		toolBar.addSeparator();
		toolBar.add(btnExecuteRequest);
		toolBar.addSeparator();
		toolBar.add(btnClearLogs);
		setToolBar(toolBar);

		requestPanel = new RequestPanel();
		loggingPanel = new LoggingPanel();

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(requestPanel);
		splitPane.setBottomComponent(loggingPanel);
		splitPane.setDividerLocation(400);
		setComponent(splitPane);

		// TODO: move this
		btnResetDataset.setEnabled(!btnStartDatabase.isEnabled());
		btnStopDatabase.setEnabled(!btnStartDatabase.isEnabled());
		btnExecuteRequest.setEnabled(!btnStartDatabase.isEnabled());
	}

	public JButton getBtnStartDB() {
		return btnStartDatabase;
	}

	public JButton getBtnStopDB() {
		return btnStopDatabase;
	}

	public RequestPanel getRequestPanel() {
		return requestPanel;
	}

	public LoggingPanel getLoggingPanel() {
		return loggingPanel;
	}
}
