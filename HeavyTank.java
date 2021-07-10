package tankGame;

import java.awt.Color;

public class HeavyTank extends Player{
	
	public HeavyTank(){
		setHealth(50);
		SPEED = 5;
		DAMAGE = 15;
	}
	
	public HeavyTank(int a, int b, int h, String n, Color col){
		super(a, b, h, n, col);
		setHealth(50);
		SPEED = 5;
		DAMAGE = 15;
	}

	public int getSpeed() {
		return SPEED;
	}
	
	public int getDamage(){
		return DAMAGE;
	}
	
}