/*
 * GenerateCringlePanel.java
 *
 * Created on November 5, 2005, 9:56 PM
 */

package net.bruncle.chrischringle;

import java.awt.event.*;
/**
 * GUI form to display the generated chris chringle list
 * @author  Jeremy
 */
public class GenerateCringlePanel extends javax.swing.JPanel implements ActionListener{
    
    /** Creates new form GenerateCringlePanel */
    public GenerateCringlePanel() {
        initComponents();
        setPreferredSize(new java.awt.Dimension(400, 430));
    }
    
    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        scrollChringle = new javax.swing.JScrollPane();
        tblChringle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        scrollLeftover = new javax.swing.JScrollPane();
        tblLeftover = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnRecalc = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setBackground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("Verdana", 0, 24));
        lblTitle.setText("Generate Chris Chringle:");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnBack.setText("Back");
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));
        btnBack.addActionListener(this);

        tblChringle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Giver", "Receiver"
            }
        ));
        scrollChringle.setViewportView(tblChringle);

        add(scrollChringle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 350, 200));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 14));
        jLabel1.setText("Left over people");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        tblLeftover.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
            },
            new String [] {
                "Name"
            }
        ));
        scrollLeftover.setViewportView(tblLeftover);

        add(scrollLeftover, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 350, 60));

        btnSave.setText("SAVE");
        add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));
        btnSave.addActionListener(this);
        
        btnRecalc.setText("Recalculate");
	add(btnRecalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));
	btnRecalc.addActionListener(this);
    }
    
    /**
    *  Generates the chris chringle list
    */
    public void pairUpPeople(){
    	java.util.List<Giver> copyOfGivers = (java.util.List<Giver>) ((java.util.ArrayList)Main.getGivers()).clone();
    	java.util.List<Receiver> copyOfReceivers = (java.util.List<Receiver>) ((java.util.ArrayList)Main.getReceivers()).clone();
    	tblLeftover.setModel(new javax.swing.table.DefaultTableModel(null, new String[] {"Name"}));
    	if (Main.getGivers().size() > Main.getReceivers().size()){
    		int surplus = (Main.getGivers().size() - Main.getReceivers().size());
    		Object[][] leftOvers = new Object[surplus][1];
    		for (int i = 0; i < surplus; i++){ 
    			Giver leftOver = copyOfGivers.remove(randomInt(0, copyOfGivers.size()));
    			leftOvers[i][0] = leftOver.getName();
    			generatedLeftovers.add(leftOver.getName() + "- giver");
    		}
    		tblLeftover.setModel(new javax.swing.table.DefaultTableModel(leftOvers, new String[] {"Name"}));
    	}
    	else if (Main.getGivers().size() < Main.getReceivers().size()){
    		int surplus = (Main.getReceivers().size() - Main.getGivers().size());
    		Object[][] leftOvers = new Object[surplus][1];
    		for (int i = 0; i < surplus; i++){
    			String leftOver = copyOfReceivers.remove(randomInt(0, copyOfReceivers.size())).getName();
    			leftOvers[i][0] = leftOver;
    			generatedLeftovers.add(leftOver + "- receiver");
    		}
    		tblLeftover.setModel(new javax.swing.table.DefaultTableModel(leftOvers, new String[] {"Name"}));
    	}
    	
    	while (true){
	    	//shuffle the people around
	    	java.util.Collections.shuffle(copyOfGivers);
	    	java.util.Collections.shuffle(copyOfReceivers);
	    	
	    	boolean done = true;
	    	for (int i = 0; i < copyOfGivers.size(); i++){
	    		if (copyOfGivers.get(i).getName().equalsIgnoreCase(copyOfReceivers.get(i).getMother()) ||
	    			copyOfGivers.get(i).getName().equalsIgnoreCase(copyOfReceivers.get(i).getFather())){
	    				done = false; //one of the kids was getting a gift from their parent, reshuffle
	    				break;
	    		}
	    		else if (copyOfGivers.get(i).getName().equalsIgnoreCase(copyOfReceivers.get(i).getName())){
	    			done = false;
	    			break;
	    		}
	    		else if (copyOfGivers.get(i).getPartner().equalsIgnoreCase(copyOfReceivers.get(i).getName())){
	    			done = false; //someone's buying a present for their partner
	    			break;
	    		}
	    		for (Receiver rec : copyOfReceivers){
	    			if (rec.getName().equalsIgnoreCase(copyOfGivers.get(i).getName())){
	    				if (rec.getFather().equalsIgnoreCase(copyOfReceivers.get(i).getName()) ||
	    					rec.getMother().equalsIgnoreCase(copyOfReceivers.get(i).getName())){ //one of the kids is giving a gift to their parents, reshuffle
	    					done = false;
	    					break;
	    				}
	    			}
	    		}
	    	}
	    	
	    	validate();
	    	repaint();
	    	
	    	if (done)
	    		break;
	}
    	
    	Object[][] chringleData = new Object[copyOfGivers.size()][2];
    	for (int i = 0; i < copyOfGivers.size(); i++){
    		chringleData[i][0] = copyOfGivers.get(i).getName();
    		chringleData[i][1] = copyOfReceivers.get(i).getName();
    	}
    	tblChringle.setModel(new javax.swing.table.DefaultTableModel(
    		chringleData, 
    		new String [] {
                "Giver", "Receiver"
            	}
        ));
        
        generatedGivers = copyOfGivers;
        generatedReceivers = copyOfReceivers;
        
        validate();
        repaint();
    }
    
    /**
    * Generates a random number between min and max
    * @param  min  The minimum value that the random number can be
    * @param  max  The maximum value that the random number can be
    */
    public int randomInt(int min, int max)
    {
	return min + new java.util.Random().nextInt(max - min);
    }
    
    /**
    * Handles GUI events 
    * @param  e  Details about the event
    */
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == btnBack)
    		Main.changeScreen(Main.FRONT_PANEL);
    	else if (e.getSource() == btnRecalc)
    		pairUpPeople();
    	else if (e.getSource() == btnSave)
    		saveFile();
    	//save - save to seperate xml file (filechooser), apply stylesheet
    }
    
    /**
    *  Saves the generated chris chringle list to a file
    */
    private String saveFile;
    private void saveFile(){
    	javax.swing.JFileChooser fc = new javax.swing.JFileChooser("./");
	fc.addChoosableFileFilter(new OnlyExt("xml", "XML files"));
    	if (fc.showOpenDialog(Main.getJFrame()) == javax.swing.JFileChooser.APPROVE_OPTION) {
    		saveFile = fc.getSelectedFile().toString();
    		if (!saveFile.endsWith(".xml"))
    			saveFile += ".xml";
    		System.out.println(saveFile);
    		try{
			new Thread(new Runnable() {
		                public void run() {
		                	try{
			                	    java.io.BufferedWriter out = new java.io.BufferedWriter(new java.io.FileWriter(saveFile,false));
						    out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
						    out.newLine();
						    out.write("<?xml-stylesheet href=\"stylesheet.xsl\" type=\"text/xsl\"?>");
						    out.newLine();
						    out.write("<chrisChringle>");
						    out.newLine();
						    for (int i = 0; i < generatedGivers.size(); i++){
						    	out.write("<pair>");
						    	out.write("<giver>");
						    	out.write(generatedGivers.get(i).getName());
						    	out.write("</giver>");
						    	out.write("<receiver>");
						    	out.write(generatedReceivers.get(i).getName());
						    	out.write("</receiver>");
						    	out.write("</pair>");
						    	out.newLine();
						    }
						    if (generatedLeftovers != null)
							    for (String leftOver : generatedLeftovers){
							    	out.write("<leftOver>");
							    	out.write(leftOver);
							    	out.write("</leftOver>");
							    	out.newLine();
							    }		  
						    out.write("</chrisChringle>");
						    out.flush();
						    out.close();
						    System.out.println("saved");	
					}
					catch (java.io.IOException e){
						e.printStackTrace();
					}
				}
			}).start();
		}
		catch (Exception e){
			e.printStackTrace();
		}
    	}
    }
    
    class OnlyExt extends javax.swing.filechooser.FileFilter
    {
  		private String ext;
  		private String desc;
  		public OnlyExt(String ext, String desc)
		{ 
			this.ext = "." + ext; 
			this.desc = desc;
		}
		public boolean accept(java.io.File dir)
		{ 
			if (dir.isDirectory())
				return true;
			String extension = "";
			try{
				extension = dir.toString().substring(dir.toString().indexOf("."));
			}
			catch (StringIndexOutOfBoundsException e){ return false; }
			return extension.endsWith(ext); 
		}
		public String getDescription() {
		        return desc;
		}
    }
    
    private java.util.List<Giver> generatedGivers;
    private java.util.List<Receiver> generatedReceivers;
    private java.util.List<String> generatedLeftovers = new java.util.ArrayList<String>();
    
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnRecalc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollChringle;
    private javax.swing.JScrollPane scrollLeftover;
    private javax.swing.JTable tblChringle;
    private javax.swing.JTable tblLeftover;
    
}
