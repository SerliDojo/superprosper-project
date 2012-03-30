/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serli.tools.quicklydb.view;
import java.awt.Dimension;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jdesktop.application.Application;

import com.serli.tools.quicklydb.DBMockupApplication;

/**
 *
 * @author serli
 */
public class ScriptLauncherPanel extends javax.swing.JPanel {
	
	private static final long serialVersionUID = 1L;
	
    private JButton btnCreateTables;
    private JButton btnDeleteTables;
    private JButton btnPopulateTables;
    private JButton btnChooseScriptCreateTables;
    private JButton btnChooseScriptDeleteTables;
    private JButton btnChooseScriptPopulateTables;
    private JTextField txtScriptCreateTables;
    private JTextField txtScriptDeleteTables;
    private JTextField txtScriptPopulateTables;

    /**
     * Creates new form ScriptLauncherPanel
     */
    public ScriptLauncherPanel() {

    	
        initComponents();
        initActions();
    }
    
	private void initActions() {
		ActionMap actionMap = Application.getInstance().getContext().getActionMap(DBMockupApplication.getApplication());
		
		btnCreateTables.setAction(actionMap.get("createTables"));
		btnPopulateTables.setAction(actionMap.get("populateTables"));
		btnDeleteTables.setAction(actionMap.get("deleteTables"));
		btnChooseScriptCreateTables.setAction(actionMap.get("chooseScriptCreateTables"));
		btnChooseScriptPopulateTables.setAction(actionMap.get("chooseScriptPopulateTables"));
		btnChooseScriptDeleteTables.setAction(actionMap.get("chooseScriptDeleteTables"));
	}

    private void initComponents() {
    	//ResourceMap resourceMap = Application.getInstance().getContext().getResourceMap(ScriptLauncherPanel.class);
    	
    	setBorder(new TitledBorder("SQL Scripts"));
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints;
        int columnsTextField = 300;
        Dimension minSizeTextField = new Dimension(400, 28);

        btnCreateTables = new javax.swing.JButton();
        btnCreateTables.setIconTextGap(4);
        btnCreateTables.setHorizontalAlignment(SwingConstants.LEFT);
        txtScriptCreateTables = new javax.swing.JTextField();
        btnChooseScriptCreateTables = new javax.swing.JButton();
        btnPopulateTables = new javax.swing.JButton();
        btnPopulateTables.setIconTextGap(4);
        btnPopulateTables.setHorizontalAlignment(SwingConstants.LEFT);
        txtScriptPopulateTables = new javax.swing.JTextField();
        btnChooseScriptPopulateTables = new javax.swing.JButton();
        btnDeleteTables = new javax.swing.JButton();
        btnDeleteTables.setIconTextGap(4);
        btnDeleteTables.setHorizontalAlignment(SwingConstants.LEFT);
        txtScriptDeleteTables = new javax.swing.JTextField();
        btnChooseScriptDeleteTables = new javax.swing.JButton();

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(btnCreateTables, gridBagConstraints);

        txtScriptCreateTables.setColumns(columnsTextField);
        txtScriptCreateTables.setMinimumSize(minSizeTextField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(txtScriptCreateTables, gridBagConstraints);

        btnChooseScriptCreateTables.setText("...");
        add(btnChooseScriptCreateTables, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(btnPopulateTables, gridBagConstraints);

        txtScriptPopulateTables.setColumns(columnsTextField);
        txtScriptPopulateTables.setMinimumSize(minSizeTextField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(txtScriptPopulateTables, gridBagConstraints);

        btnChooseScriptPopulateTables.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        add(btnChooseScriptPopulateTables, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(btnDeleteTables, gridBagConstraints);

        txtScriptDeleteTables.setColumns(columnsTextField);
        txtScriptDeleteTables.setMinimumSize(minSizeTextField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(txtScriptDeleteTables, gridBagConstraints);

        btnChooseScriptDeleteTables.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        add(btnChooseScriptDeleteTables, gridBagConstraints);
    }
    

	public void setPathCreateTables(String path) {
		txtScriptCreateTables.setText(path);
	}
	
	public void setPathPopulateTables(String path) {
		txtScriptPopulateTables.setText(path);
	}
	
	public void setPathDeleteTables(String path) {
		txtScriptDeleteTables.setText(path);
	}
	
	public String getPathCreateTables() {
		return txtScriptCreateTables.getText();
	}
	
	public String getPathPopulateTables() {
		return txtScriptPopulateTables.getText();
	}
	
	public String getPathDeleteTables() {
		return txtScriptDeleteTables.getText();
	}
}
