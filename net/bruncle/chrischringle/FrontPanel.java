/*
 * FrontPanel.java
 *
 * Created on November 5, 2005, 8:38 PM
 */

package net.bruncle.chrischringle;

import java.awt.event.*;

/**
 * GUI form, which allows navigation to other screens
 * @author  Jeremy
 */
public class FrontPanel extends javax.swing.JPanel implements ActionListener{
    
    /** Creates new form FrontPanel */
    public FrontPanel() {
        initComponents();
        setPreferredSize(new java.awt.Dimension(350,200));
    }
    
    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnManageGivers = new javax.swing.JButton();
        btnManageReceivers = new javax.swing.JButton();
        btnGenerate = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setBackground(java.awt.Color.WHITE);
        
        lblTitle.setForeground(java.awt.Color.RED);
        lblTitle.setFont(new java.awt.Font("Verdana", 0, 24));
        lblTitle.setText("Chris Chringle generator:");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        btnManageGivers.setBackground(java.awt.Color.GREEN);
        btnManageGivers.setFont(new java.awt.Font("Verdana", 0, 12));
        btnManageGivers.setContentAreaFilled(false);
        btnManageGivers.setOpaque(true);
        btnManageGivers.setText("Manage gift givers");
        btnManageGivers.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnManageGivers.addActionListener(this);
        add(btnManageGivers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
	
	btnManageReceivers.setBackground(java.awt.Color.GREEN);
        btnManageReceivers.setFont(new java.awt.Font("Verdana", 0, 12));
        btnManageReceivers.setContentAreaFilled(false);
        btnManageReceivers.setOpaque(true);
        btnManageReceivers.setText("Manage gift receivers");
        btnManageReceivers.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnManageReceivers.addActionListener(this);
        add(btnManageReceivers, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));
	
	btnGenerate.setBackground(java.awt.Color.GREEN);
        btnGenerate.setFont(new java.awt.Font("Verdana", 0, 12));
        btnGenerate.setText("Generate Chris Chringle");
        btnGenerate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGenerate.setContentAreaFilled(false);
        btnGenerate.setOpaque(true);
        btnGenerate.addActionListener(this);
        add(btnGenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

    }
    
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == btnManageGivers)
    		Main.changeScreen(Main.GIVERS_PANEL);
    	else if (e.getSource() == btnManageReceivers)
    		Main.changeScreen(Main.RECEIVERS_PANEL);
    	else if (e.getSource() == btnGenerate){
    		Main.GENERATE_PANEL.pairUpPeople();
    		Main.changeScreen(Main.GENERATE_PANEL);
    	}
    }
    
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnManageGivers;
    private javax.swing.JButton btnManageReceivers;
    private javax.swing.JLabel lblTitle;
    
}
