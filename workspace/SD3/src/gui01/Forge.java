package gui01;

public class Forge extends Planet {
	public Forge()
	{
		super();
		this.setCombatValue(1);
		this.setMoney(100);
		this.setTech(100);
		
		this.addItem("Oxymite", 20);
		this.addItem("Luxesium", 10);
		this.addItem("Non-conductive Solil", 0);
		this.addItem("Fibresium", 100);
	}

}
