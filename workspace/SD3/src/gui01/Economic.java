package gui01;

public class Economic extends Planet {

	public Economic() 
	{
		super();
		this.setCombatValue(0);
		this.setMoney(500);
		this.setTech(0);
		
		this.addItem("Oxymite", 10);
		this.addItem("Luxesium", 10);
		this.addItem("Non-conductive Solil", 5);
		this.addItem("Fibresium", 5);
	}
}
