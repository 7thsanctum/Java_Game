package gui01;

public class Enemy extends Ship {
	public Enemy(int modifier)
	{
		super((int)(Math.random() * (10)) + modifier * 2);
		this.setType("enemy");
		
		NameFactory nfactory = new NameFactory();
		this.setName(nfactory.createName("enemy"));		
		this.setHealth((int)(Math.random() * (3)) + modifier);
		this.setMoney((int)(Math.random() * (10)) + modifier * 10);
	}
}
