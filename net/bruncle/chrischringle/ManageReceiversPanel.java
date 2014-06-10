/*
 * ManageReceiversPanel.java
 *
 * Created on November 5, 2005, 9:14 PM
 */

package net.bruncle.chrischringle;

import java.awt.event.*;
/**
 * GUI form used to add, view or delete receivers
 * @author  Jeremy
 */
public class ManageReceiversPanel extends javax.swing.JPanel implements ActionListener{
    
    /** Creates new form ManageReceiversPanel */
    public ManageReceiversPanel() {
        initComponents();
        setPreferredSize(new java.awt.Dimension(198,390));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        lblMum = new javax.swing.JLabel();
        lblDad = new javax.swing.JLabel();
        scrollReceivers = new javax.swing.JScrollPane();
        lstReceivers = new javax.swing.JList(getData());
        btnBack = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtMum = new javax.swing.JTextField();
        txtDad = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setBackground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24));
        lblTitle.setText("Manage Receivers:");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        scrollReceivers.setViewportView(lstReceivers);

        add(scrollReceivers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 140));

        btnBack.setText("Back");
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));
        btnBack.addActionListener(this);

        lblName.setFont(new java.awt.Font("Verdana", 0, 12));
        lblName.setText("Name:");
        add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 90, -1));
        
        lblMum.setFont(new java.awt.Font("Verdana", 0, 12));
        lblMum.setText("Mother:");
        add(lblMum, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));
        
        add(txtMum, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 90, -1));
        
        lblDad.setFont(new java.awt.Font("Verdana", 0, 12));
        lblDad.setText("Father:");
        add(lblDad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        
        add(txtDad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 90, -1));

        btnAdd.setText("Add");
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));
        btnAdd.addActionListener(this);
        
        btnDel.setText("Delete");
        add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, -1));
	btnDel.addActionListener(this);
    }
    
    /**
    * Handles GUI events - ie. when a user clicks on a button
    */
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == btnBack)
    		Main.changeScreen(Main.FRONT_PANEL);
    	else if (e.getSource() == btnAdd){
    		if (txtName.getText().equals("")){
    			javax.swing.JOptionPane.showMessageDialog(Main.getJFrame(), "You must supply a name when adding a Receiver");
    			return;
    		}
    		Receiver toAdd = new Receiver(txtName.getText(), txtMum.getText(), txtDad.getText());
    		Main.getReceivers().add(toAdd);
    		Main.saveData();
    		lstReceivers.setListData(getData());
    		txtName.setText("");
    		validate();
    		repaint();
    	}
    	else if (e.getSource() == btnDel){
    		if (lstReceivers.getSelectedIndex() == -1){
    			javax.swing.JOptionPane.showMessageDialog(Main.getJFrame(), "You must choose a Receiver to delete by clicking on their name in the list");
    			return;
    		}
    		Main.getReceivers().remove(lstReceivers.getSelectedIndex());
    		Main.saveData();
    		lstReceivers.setListData(getData());
    		validate();
    		repaint();
    	}
    }
    
    /**
    * Gets the data to populate the list of Receivers (people who give gifts)
    */
    private String[] getData(){
    	String[] data = new String[Main.getReceivers().size()];
    	for (int i = 0; i < data.length; i++)
    		data[i] = Main.getReceivers().get(i).getName();
    	return data;
    }
    
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblMum;
    private javax.swing.JLabel lblDad;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList lstReceivers;
    private javax.swing.JScrollPane scrollReceivers;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtMum;
    private javax.swing.JTextField txtDad;
}
