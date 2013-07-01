package gui01;

import java.io.Serializable;

/**
 * @author  10006179
 */
public class Items implements Serializable {
	private String name;
	private int amount;
	private int value;
	
	public Items(int amount)
	{
		this.amount = amount;
	}
	

	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	public String getName() { return name; }
	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String setName) { this.name = setName; }
	
	/**
	 * @return  the amount
	 * @uml.property  name="amount"
	 */
	public int getAmount() { return amount; }
	/**
	 * @param amount  the amount to set
	 * @uml.property  name="amount"
	 */
	public void setAmount(int setAmount) { this.amount = setAmount; }
	
	/**
	 * @return  the value
	 * @uml.property  name="value"
	 */
	public int getValue() { return value; }
	/**
	 * @param value  the value to set
	 * @uml.property  name="value"
	 */
	public void setValue(int value) { this.value = value; }
}
