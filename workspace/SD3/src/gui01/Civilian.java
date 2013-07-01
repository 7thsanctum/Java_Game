package gui01;

public class Civilian extends Ship {
	public Civilian()
	{
		super((int)(Math.random() * (250)));
		this.setType("civilian");

		NameFactory nfactory = new NameFactory();
		this.setName(nfactory.createName("civilian"));
		this.setHealth(1);
		this.setMoney((int)(Math.random() * (1000)));
	}
}
