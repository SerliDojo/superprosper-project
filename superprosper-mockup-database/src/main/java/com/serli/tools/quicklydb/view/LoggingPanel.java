package com.serli.tools.quicklydb.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.myjavadev.utility.TextAreaOutputStream;

import com.serli.tools.quicklydb.DBMockupApplication;

/**
 * Panneau permettant l'affichage de traces.
 * 
 * @author Pascal MERON
 * @author Laurent RUAUD
 */
public class LoggingPanel extends JPanel {

	/** Numéro de série. */
	private static final long serialVersionUID = 1L;

	private TextAreaOutputStream outputStream;

	private JTextArea txtLog;

	/**
	 * Constructeur.
	 */
	public LoggingPanel() {
		txtLog = new JTextArea();
		txtLog.setEditable(false);
		txtLog.setBackground(Color.BLACK);
		txtLog.setForeground(Color.WHITE);

		outputStream = new TextAreaOutputStream(txtLog);

		add(new JScrollPane(txtLog), BorderLayout.CENTER);
		setBorder(new TitledBorder("Traces"));
		setLayout(new BorderLayout(0, 0));

		// initialisation du logger
		DBMockupApplication.getApplication().initLogger(txtLog);
	}

	/**
	 * Renvoie la valeur de {@linkplain #outputStream outputStream}.
	 * 
	 * @return la valeur de outputStream
	 */
	public TextAreaOutputStream getOutputStream() {
		return outputStream;
	}

	public void clear() {
		txtLog.setText("");
	}

}
