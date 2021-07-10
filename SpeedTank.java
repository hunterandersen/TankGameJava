package tankGame;

import java.awt.Color;

public class SpeedTank extends Player{

	public SpeedTank(){
		setHealth(15);
		SPEED = 15;
		DAMAGE = 5;
	}
	
	public SpeedTank(int a, int b, int h, String n, Color col){
		super(a, b, h, n, col);
		setHealth(15);
		SPEED = 15;
		DAMAGE = 5;
	}

	public int getSpeed() {
		return SPEED;
	}
	
	public int getDamage(){
		return DAMAGE;
	}
	
}
