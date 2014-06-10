/*
 * Main.java
 *
 * Created on November 5, 2005, 8:36 PM
 */


package net.bruncle.chrischringle;

import javax.swing.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

/**
 * Main class for the application - initialises app and provides a control centre for the GUI and for saving data
 * @author Jeremy
 */
 
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        appFrame = new JFrame("Chris Chringle");
        appFrame.setVisible(true);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadSavedData();
        
        initScreens();
        changeScreen(FRONT_PANEL);
    }
    
    /**
    * Initialises all of the screens used in the application
    */
    private static void initScreens(){
    	FRONT_PANEL = new FrontPanel();
    	GIVERS_PANEL = new ManageGiversPanel();
    	RECEIVERS_PANEL = new ManageReceiversPanel();
    	GENERATE_PANEL = new GenerateCringlePanel();
    }
    
    /**
    * Changes the screen currently displayed on the app's GUI
    *  @param  screen  The screen that will be displayed
    */
    public static void changeScreen(JPanel screen){
    	getJFrame().setContentPane(screen);
        getJFrame().setSize(screen.getPreferredSize());
        getJFrame().validate();
    }
    
    /**
    * Loads all of the saved data about givers and receivers from a file
    */
    private static void loadSavedData(){
    	try{
    		SAXBuilder parser = new SAXBuilder();  	
    		String file = getSavedFile();
    		while (file == null)
    			file = getSavedFile();
		Document doc = parser.build(file);
		    	//root element: <chrisChringle>
		Element root = doc.getRootElement();
		for (Element elemGiver : ((List<Element>)root.getChildren("giver"))){
			String name = elemGiver.getChild("name").getText();
			String partner = elemGiver.getChild("partner").getText();
			givers.add(new Giver(name, partner));
		}
		for (Element elemReceiver : ((List<Element>)root.getChildren("receiver"))){
			String name = elemReceiver.getChild("name").getText();
			String mum = elemReceiver.getChild("mum").getText();
			String dad = elemReceiver.getChild("dad").getText();
			receivers.add(new Receiver(name, mum, dad));
		}
    	}
    	catch (JDOMException e){
    		e.printStackTrace();
    	}
    	catch (java.io.IOException e){
    		e.printStackTrace();	
    	}
    }
    
    /**
    * Allows the user to choose which data file they will load from
    */
    private static String getSavedFile(){
    	javax.swing.JFileChooser fc = new javax.swing.JFileChooser("./");
	fc.addChoosableFileFilter(new OnlyExt("xml", "XML files"));
    	if (fc.showOpenDialog(getJFrame()) == javax.swing.JFileChooser.APPROVE_OPTION)
    		return fc.getSelectedFile().toString();
    	return null;
    }
    
    static class OnlyExt extends javax.swing.filechooser.FileFilter
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
    
    /**
    * Saves the data about givers and receivers to disk
    */
    public static void saveData(){
    	Element chrisChringle = new Element("chrisChringle");
    	for (Giver giver : getGivers()){
    		Element giverEl = new Element("giver");
    		Element nameEl = new Element("name");
    		nameEl.setText(giver.getName());
    		Element partnerEl = new Element("partner");
    		partnerEl.setText(giver.getPartner());
    		giverEl.addContent(nameEl);
    		giverEl.addContent(partnerEl);
    		chrisChringle.addContent(giverEl);
    	}
    	for (Receiver receiver : getReceivers()){
    		Element receiverEl = new Element("receiver");
    		Element receiverNameEl = new Element("name");
    		receiverNameEl.setText(receiver.getName());
    		receiverEl.addContent(receiverNameEl);
    		Element receiverMumEl = new Element("mum");
    		receiverMumEl.setText(receiver.getMother());
    		receiverEl.addContent(receiverMumEl);
    		Element receiverDadEl = new Element("dad");
    		receiverDadEl.setText(receiver.getFather());
    		receiverEl.addContent(receiverDadEl);
    		chrisChringle.addContent(receiverEl);
    	}
    	Document output = new Document(chrisChringle); //exerciseLog becomes root element
	org.jdom.output.XMLOutputter outputter = new org.jdom.output.XMLOutputter(org.jdom.output.Format.getPrettyFormat());
	try {
		java.io.OutputStream out = new java.io.FileOutputStream(SAVED_FILE);
	        outputter.output(output, out);       
	}
	catch (java.io.IOException e) {
		System.err.println(e);
	}
    }
    
    
    /**
    * Returns the collection of people who give gifts
    */
    public static List<Giver> getGivers(){
    	return givers;
    }
    
    /**
    * Returns the collection of people who receive gifts
    */ 
    public static List<Receiver> getReceivers(){
    	return receivers;
    }
    
    /**
    * Provides access to app's JFrame, so that dialogs can have a parent
    */
    public static JFrame getJFrame(){
    	return appFrame;
    }
    
    private static List<Giver> givers = new ArrayList<Giver>();
    private static List<Receiver> receivers = new ArrayList<Receiver>();
    
    private static final String SAVED_FILE = "./data.xml";
    
    private static JFrame appFrame;
    
    public static FrontPanel FRONT_PANEL;
    public static ManageGiversPanel GIVERS_PANEL;
    public static ManageReceiversPanel RECEIVERS_PANEL;
    public static GenerateCringlePanel GENERATE_PANEL;
}
