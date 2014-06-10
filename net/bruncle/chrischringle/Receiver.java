package net.bruncle.chrischringle;

/**
* A storage class for receivers 
*/
public class Receiver{

	private String name;
	private String mum;
	private String dad;
	
	public Receiver(String name, String mum, String dad){
		this.name = name;
		this.mum = mum;
		this.dad = dad;
	}
	
	/**
	* Returns the name of this receiver
	*/ 
	public String getName(){
		return name;
	}
	
	/**
	* Returns the mother of this receiver
	*/ 
	public String getMother(){
		return mum;
	}
	
	/**
	* Returns the father of this receiver
	*/
	public String getFather(){
		return dad;
	}
}	
		