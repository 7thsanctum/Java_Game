package gui01;

public class Military extends Planet {
	public Military(int modifier)
	{
		super();
		this.setCombatValue(2 + modifier);
		this.setMoney(10);
		this.setTech(5);	
		
		this.addItem("Oxymite", 10);
		this.addItem("Luxesium", 10);
		this.addItem("Non-conductive Solil", 75);
		this.addItem("Fibresium", 5);
	}
}
