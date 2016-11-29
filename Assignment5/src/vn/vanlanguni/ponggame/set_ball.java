package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class set_ball extends JDialog {
	boolean ball0 = false;
	boolean ball1 = false;
	boolean ball2 = false;
	boolean ball3 = false;
	ImageIcon img;
	// Xem khai bao MyDialogResult o cuoi class nay
	JButton btnbeach_ball = new JButton();
	JButton btnSoccer_ball_1 = new JButton();
	JButton btnmagic_ball = new JButton();
	JButton btncrystall_ball = new JButton();

	Color colorTrue = Color.LIGHT_GRAY;
	Color colorFalse = new Color(238, 238, 238);
	// icon ball
	ImageIcon imgbeach_ball = new ImageIcon("image/icon_ball/beach_ball.png");
	ImageIcon imgSoccer_ball_1 = new ImageIcon("image/icon_ball/Soccer_ball_1.png");
	ImageIcon imgmagic_ball = new ImageIcon("image/icon_ball/magic_ball.png");
	ImageIcon imgcrystal_ball = new ImageIcon("image/icon_ball/crystal_ball.png");
	public MyDialogResults dialogResult;

	public set_ball() {
		setPreferredSize(new Dimension(290, 210));
		setTitle("choose ball");
		getContentPane().setLayout(null);
		setModal(true);
		// set icon image
		btnbeach_ball.setIcon(new ImageIcon(imgbeach_ball.getImage()));
		btnSoccer_ball_1.setIcon(new ImageIcon(imgSoccer_ball_1.getImage()));
		btnmagic_ball.setIcon(new ImageIcon(imgmagic_ball.getImage()));
		btncrystall_ball.setIcon(new ImageIcon(imgcrystal_ball.getImage()));

		btnbeach_ball.setBackground(colorFalse);
		btnSoccer_ball_1.setBackground(colorFalse);
		btnmagic_ball.setBackground(colorFalse);
		btncrystall_ball.setBackground(colorFalse);
		dialogResult = MyDialogResults.DEFAULT;
		getContentPane().add(btnbeach_ball);
		getContentPane().add(btnSoccer_ball_1);
		getContentPane().add(btnmagic_ball);
		getContentPane().add(btncrystall_ball);

		// add location
		btnbeach_ball.setBounds(25, 30, 50, 50);
		btnSoccer_ball_1.setBounds(85, 30, 50, 50);
		btnmagic_ball.setBounds(150, 30, 50, 50);
		btncrystall_ball.setBounds(210, 30, 50, 50);

		btnbeach_ball.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball0 == false) {
					btnbeach_ball.setBackground(colorTrue);
					ball0 = true;
					ball1 = false;
					ball2 = false;
					ball3 = false;
					btnSoccer_ball_1.setBackground(colorFalse);
					btnmagic_ball.setBackground(colorFalse);
					btncrystall_ball.setBackground(colorFalse);
				} else {
					btnbeach_ball.setBackground(colorFalse);
					ball0 = false;
				}

			}
		});

		btnSoccer_ball_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball1 == false) {
					btnSoccer_ball_1.setBackground(colorTrue);
					ball1 = true;
					ball0 = false;
					ball2 = false;
					ball3 = false;
					btnbeach_ball.setBackground(colorFalse);
					btnmagic_ball.setBackground(colorFalse);
					btncrystall_ball.setBackground(colorFalse);
				} else {
					btnSoccer_ball_1.setBackground(colorFalse);
					ball1 = false;
				}

			}
		});

		btnmagic_ball.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball2 == false) {
					btnmagic_ball.setBackground(colorTrue);
					ball2 = true;
					ball0 = false;
					ball1 = false;
					ball3 = false;
					btnbeach_ball.setBackground(colorFalse);
					btnSoccer_ball_1.setBackground(colorFalse);
					btncrystall_ball.setBackground(colorFalse);
				} else {
					btnmagic_ball.setBackground(colorFalse);
					ball2 = false;
				}

			}
		});

		btncrystall_ball.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball3 == false) {
					btncrystall_ball.setBackground(colorTrue);
					ball3 = true;
					ball0 = false;
					ball2 = false;
					ball1 = false;
					btnbeach_ball.setBackground(colorFalse);
					btnSoccer_ball_1.setBackground(colorFalse);
					btnmagic_ball.setBackground(colorFalse);
				} else {
					btncrystall_ball.setBackground(colorFalse);
					ball3 = false;
				}

			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogResult = MyDialogResults.YES;
				setVisible(false);
			}
		});
		btnSave.setBounds(44, 120, 89, 23);
		getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogResult = MyDialogResults.CANCEL;
				setVisible(false);
			}
		});
		btnCancel.setBounds(154, 120, 89, 23);
		getContentPane().add(btnCancel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(set_ball.this, "Are you sure ?");
				if (result == JOptionPane.YES_OPTION) {
					setVisible(false);
				}
			}
		});
	}

}