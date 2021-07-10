package tankGame;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

	private BufferedImage image;
	
	public ImagePanel(String str){
		try{
			image = ImageIO.read(getClass().getResource(str));
		}catch(IOException ex){
			JOptionPane.showMessageDialog(null, "ISSUES");
		}
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
}
