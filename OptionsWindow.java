package tankGame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class OptionsWindow extends JFrame implements ActionListener, KeyListener, MouseListener{

	boolean mini, listen1, listen2, poweredUp;
	int tankType1, tankType2, shootKey1, shootKey2;
	JButton startB, rules, shoot1, shoot2, back;
	JTextField name1, name2;
	JCheckBox miniC, powerupC;
	JRadioButton[] tankTypes = new JRadioButton[6];
	ButtonGroup bg1, bg2;
	
	public OptionsWindow(){
		super("Options");
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
		setFocusable(true);
		setLayout(new BorderLayout());
		addKeyListener(this);
		
		shootKey1 = KeyEvent.VK_NUMPAD0;
		shootKey2 = KeyEvent.VK_SPACE;
		
		Font font = new Font("", Font.PLAIN, 16);
		
		name1 = new JTextField("Blue");
		name1.addMouseListener(this);
		name1.setPreferredSize(new Dimension(100, 20));
		name1.setFont(font);
		name2 = new JTextField("Red");
		name2.addMouseListener(this);
		name2.setPreferredSize(new Dimension(100, 20));
		name2.setFont(font);
		
		startB = new JButton("Start Game");
		startB.addActionListener(this);
		startB.setFont(font);
		rules = new JButton("How to Play");
		rules.addActionListener(this);
		rules.setFont(font);
		back = new JButton("Back");
		back.addActionListener(this);
		back.setFont(font);
		shoot1 = new JButton(KeyEvent.getKeyText(shootKey1) + "");
		shoot1.setPreferredSize(new Dimension(150, 30));
		shoot1.addActionListener(this);
		shoot1.addKeyListener(this);
		shoot1.setFont(font);
		shoot2 = new JButton(KeyEvent.getKeyText(shootKey2) + "");
		shoot2.addActionListener(this);
		shoot2.setPreferredSize(new Dimension(150, 30));
		shoot2.addKeyListener(this);
		shoot2.setFont(font);
		
		miniC = new JCheckBox("Mini Tanks");
		miniC.addActionListener(this);
		miniC.setFont(font);
		powerupC = new JCheckBox("Powerups Enabled");
		powerupC.addActionListener(this);
		powerupC.setFont(font);
		powerupC.setSelected(true);
		poweredUp = true;
		
		bg1 = new ButtonGroup();
		bg2 = new ButtonGroup();
		for(int i = 0; i<6; i++){
			tankTypes[i] = new JRadioButton();
			tankTypes[i].addActionListener(this);
			tankTypes[i].setFont(font);
		}
		tankTypes[0].setText("Speed Tank");
		tankTypes[1].setText("Normal Tank");
		tankTypes[2].setText("Heavy Tank");
		tankTypes[3].setText("Speed Tank");
		tankTypes[4].setText("Normal Tank");
		tankTypes[5].setText("Heavy Tank");
		for(int i =0; i<3; i++){
			bg1.add(tankTypes[i]);
		}
		for(int i =3; i<6; i++){
			bg2.add(tankTypes[i]);
		}
		
		JPanel midBot = new JPanel();
		midBot.add(startB);
		midBot.add(back);
		midBot.setPreferredSize(new Dimension(100, 100));
		midBot.setBorder(BorderFactory.createTitledBorder("Begin"));
		JPanel midTop = new JPanel();
		midTop.add(rules);
		JPanel midCenter = new JPanel();
		midCenter.setPreferredSize(new Dimension(100, 100));
		midCenter.setBorder(BorderFactory.createTitledBorder("Specifics"));
		midCenter.add(miniC);
		midCenter.add(powerupC);
		JPanel mid = new JPanel();
		mid.setPreferredSize(new Dimension(150, 400));
		mid.setBorder(BorderFactory.createTitledBorder("General"));
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(150, 400));
		left.add(name2);
		
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(150, 400));
		right.add(name1);
		for(int i =0; i<3; i++){
			right.add(tankTypes[i]);
			left.add(tankTypes[i+3]);
		}
		left.add(shoot2);
		right.add(shoot1);
		
		mid.setLayout(new BorderLayout());
		mid.add(midBot, BorderLayout.SOUTH);
		mid.add(midTop, BorderLayout.NORTH);
		mid.add(midCenter, BorderLayout.CENTER);
		
		left.addKeyListener(this);
		mid.addKeyListener(this);
		right.addKeyListener(this);
		
		left.setBackground(Color.WHITE);
		mid.setBackground(Color.WHITE);
		midBot.setBackground(Color.WHITE);
		midTop.setBackground(Color.WHITE);
		midCenter.setBackground(Color.WHITE);
		right.setBackground(Color.WHITE);
		
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		add(mid, BorderLayout.CENTER);
		
		repaint();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startB){
			Starter start = new Starter(tankType1, tankType2, mini, poweredUp, name1.getText(), name2.getText(), shootKey1, shootKey2);
			start.setAlwaysOnTop(false);
		}else if (e.getSource() == back){
			FirstWindow fw = new FirstWindow();
			fw.setAlwaysOnTop(false);
			this.dispose();
		}else if (e.getSource() == rules){
			JOptionPane.showMessageDialog(null, "1. Move with WASD keys and arrow keys\n" +
					"2. Shoot with "+ KeyEvent.getKeyText(shootKey2) + " and " + KeyEvent.getKeyText(shootKey1) + "\n" +
					"3. Shoot meteors to blow them up\n" +
					"4. Shoot the enemy to lower his/her health\n" +
					"5. Lower the opponent's health to zero to win", "How To Play", 1);
		}else if (e.getSource() == shoot1){
			shoot1.setText("Press desired key");
			listen1 = true;
			listen2 = false;
		}else if (e.getSource() == shoot2){
			shoot2.setText("Press desired key");
			listen2 = true;
			listen1 = false;
		}else if (e.getSource() == miniC){
			mini = miniC.isSelected();
		}else if (e.getSource()== powerupC){
			poweredUp = powerupC.isSelected();
		}
		else{
			for(int i = 0; i<6; i++){
				if (e.getSource()==tankTypes[i]){
					if (i<3){
						tankType1 = i;
					}else{
						tankType2 = i-3;
					}
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (listen1){
			shootKey1 = e.getKeyCode();
			shoot1.setText(KeyEvent.getKeyText(shootKey1) + "");
			listen1 = false;
		}else if (listen2){
			shootKey2 = e.getKeyCode();
			shoot2.setText(KeyEvent.getKeyText(shootKey2) + "");
			listen2 = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == name1 && name1.getText().equals("Blue")){
			 name1.setText("");
		}else if (e.getSource() == name2 && name2.getText().equals("Red")){
			name2.setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
