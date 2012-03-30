/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.tools.quicklydb.view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jdesktop.application.Application;

import com.serli.tools.quicklydb.DBMockupApplication;

/**
 *
 * @author serli
 */
public class RequestPanel extends JSplitPane {

	private static final long serialVersionUID = 1L;

	private JButton btnExecute;
	private JButton btnClear;
	private JTextPane txtRequest;
	private JScrollPane resultPane;

    public RequestPanel() {
        initComponents();
        initActions();
    }
    
	private void initActions() {
		ActionMap actionMap = Application.getInstance().getContext().getActionMap(DBMockupApplication.getApplication());
		
		btnExecute.setAction(actionMap.get("executeRequest"));
		btnClear.setAction(actionMap.get("clearRequest"));
	}
	
	/**
	 * Ajoute un tableau dans la zone de resultat
	 * @param table un JTable result de la requête
	 */
	public void setResultTable(JTable table) {
		resultPane.setViewportView(table);
		resultPane.validate();
	}
	
	public void clearRequest() {
		txtRequest.setText("");
	}
	
	public String getRequest() {
		return txtRequest.getText();
	}

    private void initComponents() {
    	setOrientation(JSplitPane.VERTICAL_SPLIT);

		//ResourceMap resourceMap = Application.getInstance().getContext().getResourceMap(DBMockupApplication.class);
		
    	// Editeur SQL
    	JPanel editorPane = new JPanel();	
    	editorPane.setBorder(new TitledBorder("Edit SQL request"));
    	editorPane.setLayout(new BorderLayout()); 	
    	txtRequest = new JTextPane();
    	editorPane.add(txtRequest, BorderLayout.CENTER);  	
    	JPanel panel = new JPanel();
    	btnExecute = new JButton();
    	btnExecute.setIconTextGap(4);
    	btnExecute.setHorizontalAlignment(SwingConstants.LEFT);
    	panel.add(btnExecute);
    	btnClear = new JButton();
    	btnClear.setIconTextGap(4);
    	btnClear.setHorizontalAlignment(SwingConstants.LEFT);
    	panel.add(btnClear);
    	panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	editorPane.add(panel, BorderLayout.SOUTH);
    	setTopComponent(editorPane);

    	// Resultat de la requette dans un tableau
    	resultPane = new JScrollPane();
    	setBottomComponent(resultPane);
    }

}
