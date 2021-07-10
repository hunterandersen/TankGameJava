package tankGame;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player {

	protected int SPEED, DAMAGE;
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int size = 25;
	private boolean movingUp, movingDown, movingLeft, movingRight, shooting, shot, slowed,
		fast, shield, tripleShot, ringOfDeath, instantDeath, increasedDamage;
	private int health;
	private Color color;
	private String name;
	private int key;
	private int direction;
	private long timer1, timer2;
	private final int TIME = 10000;
	
	public Player(){
		name = "Noname Loser";
		key = KeyEvent.VK_SPACE;
	}
	
	public Player(int a, int b){
		x = a;
		y = b;
		key = KeyEvent.VK_SPACE;
	}
	
	public Player(int a, int b, String n){
		x = a;
		y = b;
		name = n;
		key = KeyEvent.VK_SPACE;
	}
	
	public Player(int a, int b, int h, String n){
		x = a;
		y = b;
		health = h;
		name = n;
		key = KeyEvent.VK_SPACE;
		size = 10;
	}
	
	public Player(int a, int b, int h, String n, Color col){
		x = a;
		y = b;
		health = h;
		name = n;
		color = col;
		key = KeyEvent.VK_SPACE;
	}

	public int getSpeed(){
		return SPEED;
	}
	
	public int getDamage(){
		return DAMAGE;
	}
	
	//----------------------------START POWERUPS--------------------------------
	public void setFast(boolean fast) {
		this.fast = fast;
		if (timer1==0 && fast==true){
			timer1 = System.currentTimeMillis();
		}
	}

	public boolean isFast() {
		if (fast == true && System.currentTimeMillis() - timer1 > TIME){
			setFast(false);
			timer1 = 0;
		}
		return fast;
	}

	public boolean isShielded(){
		if (shield == true && System.currentTimeMillis() - timer1 > TIME){
			setShield(false);
			timer1 = 0;
		}
		return shield;
	}
	
	public void setShield(boolean b) {
		shield = b;
		if (timer1==0 && shield==true){
			timer1 = System.currentTimeMillis();
		}
	}

	public void setTripleShot(boolean tripleShot) {
		this.tripleShot = tripleShot;
		if (timer2==0 && tripleShot==true){
			timer2 = System.currentTimeMillis();
		}
	}

	public boolean isTripleShot() {
		if (tripleShot == true && System.currentTimeMillis() - timer2 > TIME){
			setTripleShot(false);
			timer2 = 0;
		}
		return tripleShot;
	}

	public void setIncreasedDamage(boolean increasedDamage) {
		this.increasedDamage = increasedDamage;
		if (timer2==0 && increasedDamage==true){
			timer2 = System.currentTimeMillis();
		}
	}

	public boolean isIncreasedDamage() {
		if (increasedDamage == true && System.currentTimeMillis() - timer2 > TIME){
			setIncreasedDamage(false);
			timer2 = 0;
		}
		return increasedDamage;
	}
	
	public boolean isRingOfDeath(){
		return ringOfDeath;
	}
	
	public void setRingOfDeath(boolean b) {
		ringOfDeath = b;
	}

	public boolean isInstantDeath() {
		return instantDeath;
	}
	
	public void setInstantDeath(boolean b){
		instantDeath = b;
	}
	//-------------------------END POWERUPS--------------------------
	
	
	public int getX() {
		return x;
	}

	public int getXCenter(){
		return x+(getSize()/2);
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getYCenter(){
		return y+(getSize()/2);
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
		if (velX>0){
			setDirection(2);
		}else if(velX<0){
			setDirection(3);
		}
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
		if (velY>0){
			setDirection(1);
		}else if(velY<0){
			setDirection(0);
		}
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isMovingUp() {
		return movingUp;
	}
	
	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public boolean isShooting() {
		return shooting;
	}

	public Color getColor() {
		return color;
	}

	public void setKey(int i) {
		key = i;
	}

	public int getKey() {
		return key;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public boolean wasShooting() {
		return shot;
	}
	
	public boolean isMoving(){
		return (isMovingLeft() || isMovingRight() || isMovingUp() || isMovingDown());
	}

	public void setSlowed(boolean slowed) {
		this.slowed = slowed;
	}

	public boolean isSlowed() {
		return slowed;
	}

}
