package tankGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Starter extends JFrame implements KeyListener {

	Image buff;
	Graphics g;
	Timer t;
	Random r = new Random();
	String endString = "";
	boolean last = false;
	boolean powerupEnabled = true;
	ArrayList<Block> meteors = new ArrayList<Block>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	Player[] player = new Player[2];
	static FirstWindow fw;

	public static void main(String[] args) {
		fw = new FirstWindow();
	}

	public Starter(int t1, int t2, boolean mini, boolean powers, String name1, String name2, int shootKey1, int shootKey2) {
		super("TankShooter");
		setSize(Toolkit.getDefaultToolkit().getScreenSize().getSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);

		setVisible(true);

		addKeyListener(this);
		for (int i = 0; i < 20; i++) {
			meteors.add(new Block(r.nextInt(getWidth()),
					r.nextInt(getHeight()), Color.BLACK));
		}
		meteors.add(new Block(getWidth() / 2 - 5, 0, 5, getHeight(), true));

		buff = this.createImage(this.getWidth(), this.getHeight());
		g = buff.getGraphics();

		// ---------MODIFICATIONS---------
		if (t1 == 0) {
			player[0] = new SpeedTank(r.nextInt(getWidth()), r
					.nextInt(getHeight()), 10, (name1.isEmpty()? "Blue" : name1), Color.BLUE);
		} else if (t1 == 1) {
			player[0] = new StandardTank(r.nextInt(getWidth()), r
					.nextInt(getHeight()), 10, (name1.isEmpty()? "Blue" : name1), Color.BLUE);
		} else {
			player[0] = new HeavyTank(r.nextInt(getWidth()), r
					.nextInt(getHeight()), 10, (name1.isEmpty()? "Blue" : name1), Color.BLUE);
		}
		if (t2 == 0) {
			player[1] = new SpeedTank(r.nextInt(getWidth()), r
					.nextInt(getHeight()), 10, (name2.isEmpty()? "Red" : name2), Color.RED);
		} else if (t2 == 1) {
			player[1] = new StandardTank(r.nextInt(getWidth()), r
					.nextInt(getHeight()), 10, (name2.isEmpty()? "Red" : name2), Color.RED);
		} else {
			player[1] = new HeavyTank(r.nextInt(getWidth()), r
					.nextInt(getHeight()), 10, (name2.isEmpty()? "Red" : name2), Color.RED);
		}
		if (mini) {
			player[0].setSize(player[0].getSize() / 2);
			player[1].setSize(player[1].getSize() / 2);
		}
		powerupEnabled = powers;
		player[0].setKey(shootKey1);
		player[1].setKey(shootKey2);
		
		//player[0].setShield(true);
		//powerups.add(new Powerup(0, getWidth()/2 + 200, getHeight()/2-100));
		//powerups.add(new Powerup(3, getWidth()/2 + 200, getHeight()/2));
		
		t = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		t.start();

	}

	public void update() {
		ArrayList<Block> remove = new ArrayList<Block>();
		for (Block b : meteors) {
			b.update();
			if (b.getX() < 0 || b.getX() > getWidth() || b.getY() < 0
					|| b.getY() > getHeight()) {
				remove.add(b);
			}
			if (b.getHealth() < 10) {
				b.setColor(Color.RED);
			}
			// EXPLOSION
			if (b.getHealth() < 0 || (player[0].isInstantDeath() || player[1].isInstantDeath()) && !b.isSpecial()) {
				remove.add(b);
				if (b.getPowerup()!=null && powerupEnabled){
					powerups.add(b.getPowerup());
				}
				int base = 36;
				for (int i = 0; i < 50 + b.getWidth(); i++) {
					bullets.add(new Bullet(b.getX(), b.getY(),
							r.nextInt(base) - base/2, r.nextInt(base) - base/2));
				}
			}
		}
		player[0].setInstantDeath(false);
		player[1].setInstantDeath(false);
		for (Block b : remove) {
			meteors.remove(b);
			if (b.getHealth()>0){
				meteors.add(new Block(r.nextInt(getWidth()),
					r.nextInt(getHeight()), Color.BLACK, b.getHealth()));
			}else{
				meteors.add(new Block(r.nextInt(getWidth()),
						r.nextInt(getHeight()), Color.BLACK));
			}
		}
		
		for (Bullet b : bullets) {
			b.setX(b.getX() + b.getVelX());
			b.setY(b.getY() + b.getVelY());
		}

		for (int i = 0; i < 2; i++) {
			int baseSpeed = player[i].getSpeed();
			if (player[i].isFast()){
				baseSpeed = (baseSpeed * 2);
			}
			if ((player[i].isMovingDown() || player[i].isMovingUp())
					&& (player[i].isMovingLeft() || player[i].isMovingRight())) {
				if (player[i].isShooting()) {
					player[i].setShot(true);
					player[i].setShooting(false);
				}
				baseSpeed = (baseSpeed / 3) * 2;
			} else {
				if (player[i].wasShooting()) {
					player[i].setShooting(true);
				}
			}
			if (player[i].isSlowed()){
				baseSpeed /= 2;
				player[i].setSlowed(false);
			}
			player[i].setVelY(0);
			if (player[i].isMovingDown()) {
				player[i].setVelY(player[i].getVelY() + baseSpeed);
			}
			if (player[i].isMovingUp()) {
				player[i].setVelY(player[i].getVelY() -baseSpeed);
			}

			player[i].setVelX(0);
			if (player[i].isMovingLeft()) {
				player[i].setVelX(player[i].getVelX() -baseSpeed);
			}
			if (player[i].isMovingRight()) {
				player[i].setVelX(player[i].getVelX() + baseSpeed);
			}

			player[i].setX(player[i].getX() + player[i].getVelX());
			player[i].setY(player[i].getY() + player[i].getVelY());
			if (player[i].getX() < 0) {
				player[i].setX(getWidth());
			} else if (player[i].getX() > getWidth())
				player[i].setX(0);
			if (player[i].getY() > getHeight()) {
				player[i].setY(0);
			} else if (player[i].getY() < 0) {
				player[i].setY(getHeight());
			}
		}
		
		for (int i = 0; i < 2; i++) {
			//NOT PERFECT---------------------------------
			int modifier = 1;
			if (player[i].isFast()){
				modifier = 2;
			}
			if (player[i].isShooting()) {
				if (player[i].getDirection() == 0) {
					Bullet bull = new Bullet(player[i].getX() + 5
							+ player[i].getVelX(), player[i].getY()
							+ player[i].getVelY(), 0, -15*modifier);
					if (player[i].isTripleShot()){
						bullets.add(new Bullet(bull.getX()-bull.getSize()-5, bull.getY(), bull.getVelX(), bull.getVelY()));
						bullets.add(new Bullet(bull.getX()+bull.getSize()+5, bull.getY(), bull.getVelX(), bull.getVelY()));
					}
					bullets.add(bull);
				} else if (player[i].getDirection() == 1) {
					Bullet bull = new Bullet(player[i].getX() + 5
							+ player[i].getVelX(), player[i].getY() + 25
							+ player[i].getVelY(), 0, 15*modifier);
					if (player[i].isTripleShot()){
						bullets.add(new Bullet(bull.getX()-bull.getSize()-5, bull.getY(), bull.getVelX(), bull.getVelY()));
						bullets.add(new Bullet(bull.getX()+bull.getSize()+5, bull.getY(), bull.getVelX(), bull.getVelY()));
					}
					bullets.add(bull);
				} else if (player[i].getDirection() == 2) {
					Bullet bull = new Bullet(player[i].getX() + 25
							+ player[i].getVelX(), player[i].getY()
							+ player[i].getVelY(), 15*modifier, 0);
					if (player[i].isTripleShot()){
						bullets.add(new Bullet(bull.getX(), bull.getY()-bull.getSize()-5, bull.getVelX(), bull.getVelY()));
						bullets.add(new Bullet(bull.getX(), bull.getY()+bull.getSize()+5, bull.getVelX(), bull.getVelY()));
					}
					bullets.add(bull);
				} else if (player[i].getDirection() == 3) {
					Bullet bull = new Bullet(player[i].getX() - 5
							+ player[i].getVelX(), player[i].getY()
							+ player[i].getVelY(), -15*modifier, 0);
					if (player[i].isTripleShot()){
						bullets.add(new Bullet(bull.getX(), bull.getY()-bull.getSize()-5, bull.getVelX(), bull.getVelY()));
						bullets.add(new Bullet(bull.getX(), bull.getY()+bull.getSize()+5, bull.getVelX(), bull.getVelY()));
					}
					bullets.add(bull);
				}
			}
			if (player[i].isRingOfDeath()){
				player[i].setRingOfDeath(false); 
				double enhancer = 3;
				if (player[i].isFast()){
					enhancer = 4;
				}
				for (int j = 0; j<8; j++){
					bullets.add(new Bullet(player[i].getXCenter()+(j>4? player[i].getSize()/2+5 : j+5), player[i].getYCenter()+(j<5? player[i].getSize()/2+5 : j+5),
							(int)((8-j)*enhancer), (int)(j*enhancer)));
					bullets.add(new Bullet(player[i].getXCenter()-player[i].getSize()/2-5, player[i].getYCenter()-(j<6? player[i].getSize()/2+5 : j+5),
							(int)((8-j)*-enhancer), (int)(j*-enhancer)));
					bullets.add(new Bullet(player[i].getXCenter()-(j>4? player[i].getSize()/2+5 : j+5), player[i].getYCenter()+(j<5? player[i].getSize()/2+5 : j+5),
							(int)((8-j)*-enhancer), (int)(j*enhancer)));
					bullets.add(new Bullet(player[i].getXCenter()+player[i].getSize()/2+5, player[i].getYCenter()-(j<6? player[i].getSize()/2+5 : j+5),
							(int)((8-j)*enhancer), (int)(j*-enhancer)));
				}
			}
		}
		
		if (player[0].getHealth() < 1 && player[1].getHealth() < 1) {
			last = true;
			endString = "Tie - Both players died";
		} else if (player[0].getHealth() < 1) {
			last = true;
			endString = "Game Over - " + player[0].getName() + " lost";
		} else if (player[1].getHealth() < 1) {
			last = true;
			endString = "Game Over - " + player[1].getName() + " lost";
		}
	}

	@SuppressWarnings("static-access")
	public void paint(Graphics graph) {
		if (g != null) {
			// BLANK CANVAS
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			// METEORS
			for (Block b : meteors) {
				g.setColor(b.getColor());
				g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
				for(int i = 0; i<2; i++){
					if (b.getRect().intersects(new Rectangle(player[i].getX(), player[i].getY(), player[i].getSize(), player[i].getSize()))
							&& player[i].isMoving()){
						b.setVelX(player[i].getVelX());
						b.setVelY(player[i].getVelY());
						player[i].setSlowed(true);
					}
				}
			}
			//POWERUPS
			ArrayList<Object> remove = new ArrayList<Object>();
			for (Powerup p: powerups){
				g.drawImage(p.getImage(), p.getX(), p.getY(), p.getWidth(), p.getHeight(), null);
				for(int i = 0; i<2; i++){
					if (p.getRect().intersects(new Rectangle(player[i].getX(), player[i].getY(), player[i].getSize(), player[i].getSize()))){
						remove.add(p);
						switch (p.getType()){
						case 0: player[i].setFast(true); break;
						case 1: player[i].setShield(true); break;
						case 2: player[i].setTripleShot(true); break;
						case 3: player[i].setRingOfDeath(true); break;
						case 4: player[i].setInstantDeath(true); break;
						case 5: player[i].setIncreasedDamage(true); break;
						}
					}
				}
			}
			powerups.removeAll(remove);
			// BULLETS
			g.setColor(Color.BLACK);
			remove = new ArrayList<Object>();
			for (Bullet b : bullets) {
				g.fillPolygon(b.getPoly());
				for (Block block : meteors) {
					if (b.getPoly().intersects(block.getX(), block.getY(),
							block.getWidth(), block.getHeight())) {
						block.setHealth(block.getHealth() - 1);
						remove.add(b);
					}
				}
			}
			bullets.removeAll(remove);
			for (Bullet b : bullets) {
				for (int i = 0; i < 2; i++) {
					if (!player[i].isShielded() && b.getPoly().intersects(player[i].getX(),
							player[i].getY(), player[i].getSize(),
							player[i].getSize())) {
						int damage = player[Math.abs(i-1)].getDamage();
						if (player[Math.abs(i-1)].isIncreasedDamage()){
							damage = damage*2;
						}
						player[i].setHealth(player[i].getHealth() - damage);
						remove.add(b);
					}
				}

			}
			bullets.removeAll(remove);
			// PLAYERS
			for (int i = 0; i < 2; i++) {
				g.setColor(player[i].getColor());
				g.fillRect(player[i].getX(), player[i].getY(), player[i]
						.getSize(), player[i].getSize());
				int w = player[i].getSize()/4, h = 5;
				if (player[i].isIncreasedDamage()){
					g.setColor(Color.CYAN);
				}
				switch(player[i].getDirection()){
				default:
					g.fillRect(player[i].getX()+player[i].getSize()/2-(w/2), player[i].getY()-h, w, h);
					if (player[i].isFast()){
						g.setColor(Color.BLACK);
						g.drawLine(player[i].getXCenter() + 10, player[i].getYCenter()+player[i].getSize()/2+5,
								player[i].getXCenter() + 10, player[i].getYCenter()+player[i].getSize()/2+20);
					}
					break;
				case 1:
					g.fillRect(player[i].getX()+player[i].getSize()/2-(w/2), player[i].getY()+player[i].getSize(), w, h);
					if (player[i].isFast()){
						g.setColor(Color.BLACK);
						g.drawLine(player[i].getXCenter() + 10, player[i].getYCenter()-player[i].getSize()/2-5,
								player[i].getXCenter() + 10, player[i].getYCenter()-player[i].getSize()/2-20);
					}
					break;
				case 2:
					g.fillRect(player[i].getX()+player[i].getSize(), player[i].getY()+player[i].getSize()/2-(w/2), h, w);
					if (player[i].isFast()){
						g.setColor(Color.BLACK);
						g.drawLine(player[i].getXCenter()-player[i].getSize()/2 - 10, player[i].getY()+5,
								player[i].getXCenter()-player[i].getSize()/2 - 20, player[i].getY()+5);
					}
					break;
				case 3:
					g.fillRect(player[i].getX()-5, player[i].getY()+player[i].getSize()/2-(w/2), h, w);
					if (player[i].isFast()){
						g.setColor(Color.BLACK);
						g.drawLine(player[i].getXCenter()+player[i].getSize()/2 + 10, player[i].getY()+5,
								player[i].getXCenter()+player[i].getSize()/2 + 20, player[i].getY()+5);
					}
					break;
				}
				g.setColor(Color.GREEN);
				g.drawString("" + player[i].getHealth(), player[i].getX() + 5,
						player[i].getY() + 15);
				g.setColor(player[i].getColor());
				if (player[i].isShielded()){
					g.drawOval(player[i].getX()-5, player[i].getY()-5, player[i]
						.getSize()+10, player[i].getSize()+10);
				}
			}
			// END GAME
			if (last) {
				last = false;
				g.setColor(Color.GRAY);
				g.fillRoundRect(getWidth() / 2 - (150 / 2), getHeight() / 2 - (getHeight() / 4), 150, 30, 30, 30);
				g.setColor(Color.ORANGE);
				g.drawString(endString, getWidth() / 2 - (150 / 2) + 10, getHeight() / 2 - (getHeight() / 4) + 20);
				graph.drawImage(buff, 0, 0, this.getWidth(), this.getHeight(), this);
				t.stop();
				try {
					Thread.currentThread().sleep(1700);
				} catch (Exception e1) {};
				int playAgain = JOptionPane.showConfirmDialog(null,
						"Play Again?");
				if (playAgain == JOptionPane.YES_OPTION) {
					this.dispose();
				} else {
					this.dispose();
					System.exit(0);
				}
			}
			graph.drawImage(buff, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {

		}

		if (e.getKeyCode() == KeyEvent.VK_UP)
			player[0].setMovingUp(true);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player[0].setMovingDown(true);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player[0].setMovingLeft(true);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player[0].setMovingRight(true);

		if (e.getKeyCode() == KeyEvent.VK_W)
			player[1].setMovingUp(true);
		if (e.getKeyCode() == KeyEvent.VK_S)
			player[1].setMovingDown(true);
		if (e.getKeyCode() == KeyEvent.VK_A)
			player[1].setMovingLeft(true);
		if (e.getKeyCode() == KeyEvent.VK_D)
			player[1].setMovingRight(true);

		for (int i = 0; i < 2; i++) {
			if (e.getKeyCode() == player[i].getKey()) {
				player[i].setShooting(true);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player[0].setMovingUp(false);
			
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player[0].setMovingDown(false);
			
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player[0].setMovingLeft(false);
			
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player[0].setMovingRight(false);
			

		if (e.getKeyCode() == KeyEvent.VK_W)
			player[1].setMovingUp(false);
			
		if (e.getKeyCode() == KeyEvent.VK_S)
			player[1].setMovingDown(false);
			
		if (e.getKeyCode() == KeyEvent.VK_A)
			player[1].setMovingLeft(false);
			
		if (e.getKeyCode() == KeyEvent.VK_D)
			player[1].setMovingRight(false);

		for (int i = 0; i < 2; i++) {
			if (e.getKeyCode() == player[i].getKey()) {
				player[i].setShooting(false);
				player[i].setShot(false);
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
