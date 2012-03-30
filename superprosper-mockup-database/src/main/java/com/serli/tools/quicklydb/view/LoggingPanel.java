package com.serli.tools.quicklydb.view;

import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.jdesktop.application.Application;

import com.serli.tools.quicklydb.DBMockupApplication;

public class LoggingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextArea txtLog = null;
	private JButton btnClearLog = null;
	
	/**
	 * Create the panel.
	 */
	public LoggingPanel() {
		initComponents();
		initActions();
		// initialisation du logger
		DBMockupApplication.getApplication().initLogger(txtLog);
	}
	
	private void initActions() {
		ActionMap actionMap = Application.getInstance().getContext().getActionMap(DBMockupApplication.getApplication());
		btnClearLog.setAction(actionMap.get("clearLog"));
	}

	private void initComponents() {
		setBorder( new TitledBorder("Application logs"));
		setLayout(new BorderLayout(0, 0));
		txtLog = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txtLog);
		txtLog.setEditable(false);
		add(scrollPane, BorderLayout.CENTER);
		btnClearLog = new JButton();
		add(btnClearLog, BorderLayout.SOUTH);
	}

	public JTextArea getTextArea() {
		return txtLog;
	}
	


}
