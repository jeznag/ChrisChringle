/*
 * ManageGiversPanel.java
 *
 * Created on November 5, 2005, 9:14 PM
 */

package net.bruncle.chrischringle;

import java.awt.event.*;
/**
 * GUI form used to add, view or delete givers
 * @author  Jeremy
 */
public class ManageGiversPanel extends javax.swing.JPanel implements ActionListener{
    
    /** Creates new form ManageGiversPanel */
    public ManageGiversPanel() {
        initComponents();
        setPreferredSize(new java.awt.Dimension(198,340));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        scrollGivers = new javax.swing.JScrollPane();
        lstGivers = new javax.swing.JList(getData());
        btnBack = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        lblPartner = new javax.swing.JLabel();
        txtPartner = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setBackground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24));
        lblTitle.setText("Manage givers:");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        scrollGivers.setViewportView(lstGivers);

        add(scrollGivers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 140));

        btnBack.setText("Back");
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));
        btnBack.addActionListener(this);

        lblName.setFont(new java.awt.Font("Verdana", 0, 12));
        lblName.setText("Name:");
        add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 90, -1));
        
        lblPartner.setFont(new java.awt.Font("Verdana", 0, 12));
        lblPartner.setText("Partner:");
        add(lblPartner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));
        
        add(txtPartner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 90, -1));

        btnAdd.setText("Add");
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));
        btnAdd.addActionListener(this);
        
        btnDel.setText("Delete");
        add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));
	btnDel.addActionListener(this);
    }
    
    /**
    * Handles GUI events - ie. when a user clicks on a button
    */
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == btnBack)
    		Main.changeScreen(Main.FRONT_PANEL);
    	else if (e.getSource() == btnAdd){
    		if (txtName.getText().equals("") || txtPartner.getText().equals("")){
    			javax.swing.JOptionPane.showMessageDialog(Main.getJFrame(), "You must supply a name when adding a giver");
    			return;
    		}
    		Main.getGivers().add(new Giver(txtName.getText(), txtPartner.getText()));
    		Main.saveData();
    		lstGivers.setListData(getData());
    		txtName.setText("");
    		txtPartner.setText("");
    		validate();
    		repaint();
    	}
    	else if (e.getSource() == btnDel){
    		if (lstGivers.getSelectedIndex() == -1){
    			javax.swing.JOptionPane.showMessageDialog(Main.getJFrame(), "You must choose a giver to delete by clicking on their name in the list");
    			return;
    		}
    		Main.getGivers().remove(lstGivers.getSelectedIndex());
    		Main.saveData();
    		lstGivers.setListData(getData());
    		validate();
    		repaint();
    	}
    }
    
    /**
    * Gets the data to populate the list of givers (people who give gifts)
    */
    private String[] getData(){
    	String[] data = new String[Main.getGivers().size()];
    	for (int i = 0; i < data.length; i++)
    		data[i] = Main.getGivers().get(i).getName();
    	return data;
    }
    
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList lstGivers;
    private javax.swing.JScrollPane scrollGivers;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPartner;
    private javax.swing.JLabel lblPartner;
    
}
