package tankGame;

import java.awt.Polygon;
import java.util.Random;

public class Bullet {

	private int x;
	private int y;
	private int velX;
	private int velY;
	private int damage;
	private int size;
	Polygon p;
	
	public Bullet(){
		damage = 5;
		size = 14;
	}
	
	public Bullet(int a, int b, int c, int d){
		x = a;
		y = b;
		velX = c;
		velY = d;
		while (velX==0 && velY==0){
			velX = new Random().nextInt(30)-15;
			velY = new Random().nextInt(30)-15;
		}
		damage = 5;
		size = 14;
	}
	
	public Bullet(int a, int b, int c, int d, int e){
		x = a;
		y = b;
		velX = c;
		velY = d;
		while (velX==0 && velY==0){
			velX = new Random().nextInt(30)-15;
			velY = new Random().nextInt(30)-15;
		}
		damage = e;
		size = 14;
	}
	
	public Bullet(int a, int b, int c, int d, int e, int f){
		x = a;
		y = b;
		velX = c;
		velY = d;
		while (velX==0 && velY==0){
			velX = new Random().nextInt(30)-15;
			velY = new Random().nextInt(30)-15;
		}
		damage = e;
		size = f;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Polygon getPoly(){
		p = new Polygon();
		if (getVelX()>0){
			p.addPoint(getX(), getY());
			p.addPoint(getX() + getSize(), getY() + (getSize()/2));
			p.addPoint(getX(), getY() + getSize());
		}else if (getVelX()<0){
			p.addPoint(getX(), getY());
			p.addPoint(getX() - getSize(), getY() + (getSize()/2));
			p.addPoint(getX(), getY() + getSize());
		}else if (getVelY()>0){
			p.addPoint(getX(), getY());
			p.addPoint(getX() + getSize(), getY());
			p.addPoint(getX() + (getSize()/2), getY() + getSize());
		}else{
			p.addPoint(getX(), getY());
			p.addPoint(getX() + getSize(), getY());
			p.addPoint(getX() + (getSize()/2), getY() - getSize());
		}
		return p;
	}
	
}
