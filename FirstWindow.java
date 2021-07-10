package tankGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FirstWindow extends JFrame implements ActionListener{

	JButton play, quit, info;
	ImagePanel picPan;
	JPanel top, bot;
	
	public FirstWindow(){
		super("TankShooter");
		setSize(500, 500);
		setFocusable(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		Font font = new Font("", Font.BOLD, 14);
		
		play = new JButton("Play Game");
		play.setPreferredSize(new Dimension(120, 30));
		play.addActionListener(this);
		play.setFont(font);
		quit = new JButton("Quit");
		quit.setPreferredSize(new Dimension(100, 30));
		quit.addActionListener(this);
		info = new JButton("About");
		info.setPreferredSize(new Dimension(100, 30));
		info.addActionListener(this);
		
		picPan = new ImagePanel("/Tank TitleLogo.jpg");
		picPan.setPreferredSize(new Dimension(500, 300));
		top = new JPanel();
		top.setPreferredSize(new Dimension(500, 50));
		bot = new JPanel();
		bot.setPreferredSize(new Dimension(500, 50));
		
		top.add(info);
		top.add(play);
		top.add(quit);
		
		add(top, BorderLayout.NORTH);
		add(picPan, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			OptionsWindow ow = new OptionsWindow();
			ow.setAlwaysOnTop(false);
			dispose();
		}else if (e.getSource() == quit){
			dispose();
		}else if (e.getSource() == info){
			JOptionPane.showMessageDialog(null, "TankShooter©\nMade by: Hunter Andersen\ntankshooter.servegame.com:8080\nVersion 3.5\n   -Added powerups\n     -Powerups fully functional");
		}
	}
	
	
	
}
