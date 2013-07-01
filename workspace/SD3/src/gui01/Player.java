package gui01;

public class Player extends Ship {

	public Player() {
		super(10);
		this.setType("player");
		this.setHealth(2);
		this.setMoney(500);
		this.setTech(0);
	}

}
