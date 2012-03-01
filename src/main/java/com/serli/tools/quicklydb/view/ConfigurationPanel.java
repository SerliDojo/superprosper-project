package com.serli.tools.quicklydb.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ConfigurationPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private javax.swing.JCheckBox chkDBCreate;
    private javax.swing.JLabel lblDBCreate;
    private javax.swing.JLabel lblDBName;
    private javax.swing.JLabel lblDBPort;
    private javax.swing.JTextField txtDBName;
    private javax.swing.JTextField txtDBPort;

	/**
	 * Create the panel.
	 */
	public ConfigurationPanel() {
		
		initComponents();
	}

	private void initComponents() {
		setBorder(new TitledBorder("Derby network"));
		
        GridBagConstraints gridBagConstraints = null;

        lblDBName = new javax.swing.JLabel();
        txtDBName = new javax.swing.JTextField();
        lblDBPort = new javax.swing.JLabel();
        txtDBPort = new javax.swing.JTextField();
        chkDBCreate = new javax.swing.JCheckBox();
        lblDBCreate = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        lblDBName.setText("DB Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 4);
        add(lblDBName, gridBagConstraints);

        txtDBName.setColumns(25);
        txtDBName.setText("DBCodingDojo");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(txtDBName, gridBagConstraints);

        lblDBPort.setText("Port");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 4);
        add(lblDBPort, gridBagConstraints);

        txtDBPort.setColumns(5);
        txtDBPort.setText("1529");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(txtDBPort, gridBagConstraints);

        chkDBCreate.setActionCommand("chkCreate");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(chkDBCreate, gridBagConstraints);
        chkDBCreate.getAccessibleContext().setAccessibleName("chkCreate");
        chkDBCreate.setSelected(true);

        lblDBCreate.setText("Create");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 4);
        add(lblDBCreate, gridBagConstraints);
	}
	
	public String getDBName() {
		return txtDBName.getText();
	}
	
	public int getDBPort() {
		int port = 0;
		try {
			port = Integer.parseInt(txtDBPort.getText());
		} catch(Exception ex) {
			// TOTDO Logger.
		}
		return port;
	}
	
	public boolean getDBCreate() {
		return chkDBCreate.isSelected();
	}
}
