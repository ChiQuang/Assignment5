package vn.vanlanguni.ponggame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MainScreen extends JFrame{
	private static final int _HEIGHT = 600;
	private static final int _WIDTH = 600;
	private PongPanel pongPanel;
	
	public MainScreen(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
		setLayout(new BorderLayout());
		setTitle("Pong Game - K21T Ltd.");
		pongPanel = new PongPanel();
		getContentPane().add(pongPanel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(this);
		setResizable(false);
	}

    public static void main(String[] args) {
       MainScreen mainFrame = new MainScreen();
       mainFrame.setVisible(true);
    }
}	