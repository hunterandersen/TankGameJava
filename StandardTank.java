package tankGame;

import java.awt.Color;

public class StandardTank extends Player{
	
	public StandardTank(){
		setHealth(30);
		SPEED = 10;
		DAMAGE = 10;
	}
	
	public StandardTank(int a, int b, int h, String n, Color col){
		super(a, b, h, n, col);
		setHealth(30);
		SPEED = 10;
		DAMAGE = 10;
	}

	public int getSpeed() {
		return SPEED;
	}
	
	public int getDamage(){
		return DAMAGE;
	}
	
}
