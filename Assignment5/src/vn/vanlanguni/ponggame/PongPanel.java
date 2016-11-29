package vn.vanlanguni.ponggame;

/*
 * PONG GAME REQUIREMENTS
 * This simple "tennis like" game features two paddles and a ball, 
 * the goal is to defeat your opponent by being the first one to gain 3 point,
 *  a player gets a point once the opponent misses a ball. 
 *  The game can be played with two human players, one on the left and one on 
 *  the right. They use keyboard to start/restart game and control the paddles. 
 *  The ball and two paddles should be red and separating lines should be green. 
 *  Players score should be blue and background should be black.
 *  Keyboard requirements:
 *  + P key: start
 *  + Space key: restart
 *  + W/S key: move paddle up/down
 *  + Up/Down key: move paddle up/down
 *  
 *  Version: 0.5
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * 
 * @author Invisible Man
 *
 */
public class PongPanel extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener {
	private static final long serialVersionUID = -1097341635155021546L;
	set_name Name = new set_name();
	set_ball Ball = new set_ball();
	String username1;
	String username2;
	private boolean showTitleScreen = true;
	private boolean playing;
	private boolean gameOver;
	private boolean checkrect = false, checksetting = false;
	Rectangle rectPlay;
	Rectangle rectSetting;
	/** ImageIcon. */
	ImageIcon imgSettings = new ImageIcon("image/settings.png");
	ImageIcon imgWelcomeBg = new ImageIcon("image/background/nature.gif");
	ImageIcon imgWinnerBg = new ImageIcon("image/background/winner.gif");
	ImageIcon imgPaddleSpace = new ImageIcon("image/paddles/Spaceship.png");
	ImageIcon imgPaddleSoccerPlayerLeft = new ImageIcon("image/paddles/player1.png");
	ImageIcon imgPaddleSoccerPlayerRight = new ImageIcon("image/paddles/player2.png");
	ImageIcon imgPaddleBeach = new ImageIcon("image/paddles/surfboard.png");
	ImageIcon imgPaddleChristmas = new ImageIcon("image/paddles/Christmas-Tree.png");
	/** Background. */
	private Color backgroundColor = Color.BLACK;

	/** State on the control keys. */
	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;

	/** The ball: position, diameter */
	private int ballX = 300;
	private int ballY = 300;
	private int diameter = 30;
	private int ballDeltaX = -1;
	private int ballDeltaY = 3;

	/** Player 1's paddle: position and size */
	private int playerOneX = 0;
	private int playerOneY = 200;
	private int playerOneWidth = 30;
	private int playerOneHeight = 75;

	/** Player 2's paddle: position and size */
	private int playerTwoX = 565;
	private int playerTwoY = 200;
	private int playerTwoWidth = 30;
	private int playerTwoHeight = 75;

	/** Speed of the paddle - How fast the paddle move. */
	private int paddleSpeed = 5;

	/** Player score, show on upper left and right . */
	private int playerOneScore;
	private int playerTwoScore;

	/** Construct a PongPanel. */
	public PongPanel() {
		setBackground(backgroundColor);

		// listen to key presses
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		addKeyListener(this);
		// call step() 60 fps
		Timer timer = new Timer(500 / 60, this);
		timer.start();
	}

	/** Implement actionPerformed */
	public void actionPerformed(ActionEvent e) {
		step();
	}

	/** Repeated task */
	public void step() {

		if (playing) {

			/* Playing mode */

			// move player 1
			// Move up if after moving, paddle is not outside the screen
			if (wPressed && playerOneY - paddleSpeed > 0) {
				playerOneY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (sPressed && playerOneY + playerOneHeight + paddleSpeed < getHeight()) {
				playerOneY += paddleSpeed;
			}

			// move player 2
			// Move up if after moving paddle is not outside the screen
			if (upPressed && playerTwoY - paddleSpeed > 0) {
				playerTwoY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (downPressed && playerTwoY + playerTwoHeight + paddleSpeed < getHeight()) {
				playerTwoY += paddleSpeed;
			}

			/*
			 * where will the ball be after it moves? calculate 4 corners: Left,
			 * Right, Top, Bottom of the ball used to determine whether the ball
			 * was out yet
			 */
			int nextBallLeft = ballX + ballDeltaX;
			int nextBallRight = ballX + diameter + ballDeltaX;
			// FIXME Something not quite right here
			int nextBallTop = ballY + ballDeltaY;
			int nextBallBottom = ballY + diameter + ballDeltaY;

			// Player 1's paddle position
			int playerOneRight = playerOneX + playerOneWidth;
			int playerOneTop = playerOneY;
			int playerOneBottom = playerOneY + playerOneHeight;

			// Player 2's paddle position
			float playerTwoLeft = playerTwoX;
			float playerTwoTop = playerTwoY;
			float playerTwoBottom = playerTwoY + playerTwoHeight;

			// ball bounces off top and bottom of screen
			if (nextBallTop < 0 || nextBallBottom > getHeight()) {
				ballDeltaY *= -1;
			}

			// will the ball go off the left side?
			if (nextBallLeft < playerOneRight) {
				// is it going to miss the paddle?
				if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

					playerTwoScore++;

					// Player 2 Win, restart the game
					if (playerTwoScore == 3) {
						playing = false;
						gameOver = true;
					}
					ballX = 280;
					ballY = 300;
				} else {
					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					ballDeltaX *= -1;
				}
			}

			// will the ball go off the right side?
			if (nextBallRight > playerTwoLeft) {
				// is it going to miss the paddle?
				if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {

					playerOneScore++;

					// Player 1 Win, restart the game
					if (playerOneScore == 3) {
						playing = false;
						gameOver = true;
					}
					ballX = 300;
					ballY = 300;
				} else {

					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					ballDeltaX *= -1;
				}
			}

			// move the ball
			ballX += ballDeltaX;
			ballY += ballDeltaY;
		}

		// stuff has moved, tell this JPanel to repaint itself
		repaint();
	}

	/** Paint the game screen. */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		username1 = Name.txtUsername1.getText();
		username2 = Name.txtUsername2.getText();
		if (showTitleScreen) {
			g.drawImage(imgWelcomeBg.getImage(), 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.MAGENTA);
			/* Show welcome screen */
			
			// Draw game title and start message
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
			g.drawString("Pong Game", 180, 100);
			g.setColor(Color.white);
			rectPlay = new Rectangle(249, 380, 57, 25);
			rectSetting = new Rectangle(550, 525, 30, 30);
			// FIXME Welcome message below show smaller than game title
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			if (checkrect == false) {
				g.drawString("Click ", 195, 400);
				g.drawString("to play.", 315, 400);
				g.setColor(Color.lightGray);
				g.drawString("'HERE'", 245, 400);
			}
			if (checkrect == true) {
				g.setColor(Color.MAGENTA);
				g.drawString("START ", 245, 400);
			}
			if (checksetting == false) {
				g.drawImage(imgSettings.getImage(), 550, 525, 30, 30, null);
			}
			if (checksetting == true) {
				g.drawImage(imgSettings.getImage(), 540, 515, 40, 40, null);
			}
		} else if (playing) {

			/* Game is playing */

			// set the coordinate limit
			int playerOneRight = playerOneX + playerOneWidth;
			int playerTwoLeft = playerTwoX;
			// draw dashed line down center

			// draw the ball and paddles
			if (Ball.ball0 == true) {
				
				g.setColor(Color.WHITE);
				g.fillOval(ballX, ballY, diameter, diameter);
				
				g.drawImage(imgPaddleBeach.getImage(), playerOneX, playerOneY, playerOneWidth, playerOneHeight, null);
				g.drawImage(imgPaddleBeach.getImage(), playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight, null);
			} else if (Ball.ball1 == true) {
				
				g.setColor(Color.WHITE);
				g.fillOval(ballX, ballY, diameter, diameter);
				
				g.drawImage(imgPaddleSoccerPlayerLeft.getImage(), playerOneX, playerOneY, playerOneWidth,
						playerOneHeight, null);
				g.drawImage(imgPaddleSoccerPlayerRight.getImage(), playerTwoX, playerTwoY, playerTwoWidth,
						playerTwoHeight, null);
			} else if (Ball.ball2 == true) {
				
				g.setColor(Color.WHITE);
				g.fillOval(ballX, ballY, diameter, diameter);
				
				g.drawImage(imgPaddleSpace.getImage(), playerOneX, playerOneY, playerOneWidth, playerOneHeight, null);
				g.drawImage(imgPaddleSpace.getImage(), playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight, null);
			} else if (Ball.ball3 == true) {
				
				g.setColor(Color.WHITE);
				g.fillOval(ballX, ballY, diameter, diameter);
				
				g.drawImage(imgPaddleChristmas.getImage(), playerOneX, playerOneY, playerOneWidth, playerOneHeight,
						null);
				g.drawImage(imgPaddleChristmas.getImage(), playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight,
						null);
			} else {
				g.setColor(Color.WHITE);
				g.fillOval(ballX, ballY, diameter, diameter);
				g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
				g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
			}
			
			//draw line
			g.setColor(Color.GREEN);
			for (int lineY = 0; lineY < getHeight(); lineY += 50) {
				g.drawLine(300, lineY, 300, lineY + 25);
			}

			// draw "goal lines" on each side
			g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
			g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());

			// draw the scores
			g.setColor(Color.BLUE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString(String.valueOf(playerOneScore), 150, 100); // Player 1
																	// score
			g.drawString(String.valueOf(playerTwoScore), 430, 100); // Player 2
																	// score

			// draw username
			g.setColor(Color.MAGENTA);
			g.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
			g.drawString(username1, 105, 60);
			g.drawString(username2, 385, 60);

		} else if (gameOver) {

			/* Show End game screen with winner name and score */
			g.drawImage(imgWinnerBg.getImage(), 0, 0, getWidth(), getHeight(), null);
			// Draw scores
			// TODO Set Blue color
			g.setColor(Color.BLUE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString(String.valueOf(playerOneScore), 150, 100);
			g.drawString(String.valueOf(playerTwoScore), 430, 100);

			// Draw the winner name
			// g.drawImage(bgw.getImage(), 0, 0, 600, 600, null);
			g.setColor(Color.ORANGE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			if (playerOneScore > playerTwoScore) {
				g.drawString(username1 + " Wins!", 145, 200);
			} else {
				g.drawString(username2 + " Wins!", 145, 200);
			}

			// Draw Restart message
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			// TODO Draw a restart message
			g.drawString("Press 'SpaceBar' to restart.", 175, 500);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
			}
		} else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
			gameOver = false;
			showTitleScreen = true;
			playerOneY = 200;
			playerTwoY = 200;
			ballX = 300;
			ballY = 300;
			playerOneScore = 0;
			playerTwoScore = 0;
			Name.txtUsername1.setText("");
			Name.txtUsername2.setText("");
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent g) {
		// TODO Auto-generated method stub
		if (rectPlay.contains(g.getPoint())) {
			Name.setLocationRelativeTo(this);
			Name.setVisible(true);
			if (Name.dialogResult == MyDialogResults.YES) {
				showTitleScreen = false;
				playing = true;
			} else {
				showTitleScreen = true;
			}
		} else if (rectSetting.contains(g.getPoint())) {
			Ball.setLocationRelativeTo(this);
			Ball.setVisible(true);
			showTitleScreen = true;

		}
	}

	@Override
	public void mouseMoved(MouseEvent g) {
		// TODO Auto-generated method stub
		if (rectPlay.contains(g.getX(), g.getY())) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			checkrect = true;
			checksetting = false;
		} else if (rectSetting.contains(g.getX(), g.getY())) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			checkrect = false;
			checksetting = true;
		} else {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			checkrect = false;
			checksetting = false;
		}

		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
