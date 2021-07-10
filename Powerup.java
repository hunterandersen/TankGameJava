package tankGame;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Powerup {

	private int x, y, width, height;
	private final int type;
	private BufferedImage image;
	
	public Powerup(int i){
		type = i;
		setImage(i);
		width = 40;
		height = 40;
	}
	
	public Powerup(int i, int a, int b){
		type = i;
		setImage(i);
		x = a;
		y = b;
		width = 40;
		height = 40;
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, x, y, null);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public void setImage(int i){
		String str;
		switch (i){
		case 0: str = "/speedBoost.png"; break;
		case 1: str = "/shield.png"; break;
		case 2: str = "/tripleShot.png"; break;
		case 3: str = "/ringOfDeath.png"; break;
		case 4: str = "/instantDeath.png"; break;
		//case 5: str = "/bulletDamage.png"; break;
		default: str = "/Tank Logo.jpg"; break;
		}
		try{
			image = ImageIO.read(getClass().getResource(str));
		}catch(IOException ex){
			JOptionPane.showMessageDialog(null, "powerup image issues");
		}
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int a){
		x = a;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int a){
		y = a;
	}
	
	public int getType(){
		return type;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int a){
		width = a;
	}
	
	public int getHeight(){
		return height;
	}
	
}
