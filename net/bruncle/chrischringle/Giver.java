package net.bruncle.chrischringle;

/**
* @author  Jeremy Nagel
* A storage class for receivers 
*/
public class Giver{

	private String name;
	private String partner;
	
	public Giver(String name, String partner){
		this.name = name;
		this.partner = partner;
	}
	
	/**
	* Returns the name of this giver
	*/ 
	public String getName(){
		return name;
	}
	
	/**
	* Returns the partner of this giver
	*/ 
	public String getPartner(){
		return partner;
	}
}	
		