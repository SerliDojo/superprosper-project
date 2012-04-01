/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.dojo.superprosper.database.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author serli
 */
public class RequestPanel extends JSplitPane {

	private static final long serialVersionUID = 1L;

	private JTextPane txtRequest;
	private JScrollPane resultPane;

	public RequestPanel() {
		txtRequest = new JTextPane();

		resultPane = new JScrollPane();

		JPanel editorPane = new JPanel();
		editorPane.setBorder(new TitledBorder("Requête SQL"));
		editorPane.setLayout(new BorderLayout());
		editorPane.add(new JScrollPane(txtRequest), BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Résultats"));
		panel.setLayout(new BorderLayout());
		panel.add(resultPane, BorderLayout.CENTER);

		setTopComponent(editorPane);
		setBottomComponent(panel);
		setOrientation(JSplitPane.VERTICAL_SPLIT);
	}

	/**
	 * Ajoute un tableau dans la zone de resultat
	 * 
	 * @param table un JTable result de la requête
	 */
	public void setResultTable(JTable table) {
		resultPane.setViewportView(table);
		resultPane.validate();
	}

	public String getRequest() {
		return txtRequest.getText();
	}

}
