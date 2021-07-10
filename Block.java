package tankGame;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;


public class Block {

	private int x;
	private int y;
	private int velX;
	private int velY;
	private int health;
	private int width;
	private int height;
	private int startX, startY;
	private Color color;
	private boolean special = false;
	private Powerup p;
	Random r = new Random();
	
	public Block(){
		setHealth(20);
		setWidth(r.nextInt(30)+15);
		setHeight(r.nextInt(10)+15);
	}
	
	public Block(int a, int b){
		x = a;
		y = b;
		startX = a;
		startY = b;
		setWidth(r.nextInt(10)+15);
		setHeight(r.nextInt(10)+15);
		setHealth(20);
		if (r.nextInt(10) < 5){
			p = new Powerup(r.nextInt(6), x, y);
		}
	}
	
	public Block(int a, int b, Color col){
		x = a;
		y = b;
		startX = a;
		startY = b;
		color = col;
		setWidth(r.nextInt(10)+15);
		setHeight(r.nextInt(10)+15);
		setHealth(20);
		if (r.nextInt(10) < 5){
			p = new Powerup(r.nextInt(6), x, y);
		}
	}
	
	public Block(int a, int b, Color col, int hp){
		x = a;
		y = b;
		startX = a;
		startY = b;
		color = col;
		setWidth(r.nextInt(10)+15);
		setHeight(r.nextInt(10)+15);
		setHealth(hp);
		if (r.nextInt(10) < 5){
			p = new Powerup(r.nextInt(6), x, y);
		}
	}
	
	public Block(int a, int b, int c, int d){
		x = a;
		y = b;
		startX = a;
		startY = b;
		velX = c;
		velY = d;
		setWidth(r.nextInt(10)+15);
		setHeight(r.nextInt(10)+15);
		setHealth(20);
		if (r.nextInt(10) < 5){
			p = new Powerup(r.nextInt(6), x, y);
		}
	}

	public Block(int a, int b, int c, int d, boolean bool){
		x = a;
		y = b;
		startX = a;
		startY = b;
		velX = 0;
		velY = 0;
		setColor(Color.BLACK);
		setSpecial(bool);
		setWidth(c);
		setHeight(d);
		setHealth(9001);
		if (r.nextInt(10) < 5){
			p = new Powerup(r.nextInt(6), x, y);
		}
	}
	
	public Block(int a, int b, int c, int d, Color col){
		x = a;
		y = b;
		startX = a;
		startY = b;
		velX = c;
		velY = d;
		color = col;
		setWidth(r.nextInt(10)+15);
		setHeight(r.nextInt(10)+15);
		setHealth(20);
		if (r.nextInt(10) < 5){
			p = new Powerup(r.nextInt(6), x, y);
		}
	}
	
	public void update(){
		if (!isSpecial()){
			if ((getVelX()==0 && getVelY()==0) || (Point.distance(startX, startY, x, y)>70)){
				startX = getX();
				startY = getY();
				setVelX(0);
				setVelY(0);
				if (r.nextInt(2)==1){
					setVelX(r.nextInt(6)-3);
				}else{
					setVelY(r.nextInt(6)-3);
				}
			}
			setX(x + getVelX());
			setY(y + getVelY());
			if (getPowerup()!=null){
				getPowerup().setX(getX());
				getPowerup().setY(getY());
			}
		}else{
			setHealth(9001);
		}
	}
	
	public Powerup getPowerup(){
		return p;
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
	
	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle getRect(){
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void setP(Powerup p) {
		this.p = p;
	}

	public Powerup getP() {
		return p;
	}
	
}
