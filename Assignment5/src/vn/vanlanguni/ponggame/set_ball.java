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


public class set_ball extends JDialog{
	boolean ball0 = false;
	boolean ball1 = false;
	boolean ball2 = false;
	boolean ball3 = false;
	// Xem khai bao MyDialogResult o cuoi class nay
	JButton btn0 = new JButton();
	JButton btn1 = new JButton();
	JButton btn2 = new JButton();
	JButton btn3 = new JButton();
	
	Color colorTrue =Color.LIGHT_GRAY;
	Color colorFalse= new Color(238, 238, 238);
	//icon ball
	
	public MyDialogResults dialogResult;

	public set_ball() {
		setPreferredSize(new Dimension(300, 280));
		setTitle("chon banh");
		getContentPane().setLayout(null);
		setModal(true);
		
		
		btn0.setBackground(colorFalse);
		btn1.setBackground(colorFalse);
		btn2.setBackground(colorFalse);
		btn3.setBackground(colorFalse);
		dialogResult = MyDialogResults.DEFAULT;
		getContentPane().add(btn0);
		getContentPane().add(btn1);
		getContentPane().add(btn2);
		getContentPane().add(btn3);
		btn0.setBounds(25, 120, 50, 50);
		btn1.setBounds(85, 120, 50, 50);
		btn2.setBounds(150, 120, 50, 50);
		btn3.setBounds(210, 120, 50, 50);
		
		btn0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball0 == false) {
					btn0.setBackground(colorTrue);
					ball0 = true;
					ball1 = false;
					ball2 = false;
					ball3 = false;
					btn1.setBackground(colorFalse);
					btn2.setBackground(colorFalse);
					btn3.setBackground(colorFalse);
				} else {
					btn0.setBackground(colorFalse);
					ball0 = false;
				}

			}
		});

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball1 == false) {
					btn1.setBackground(colorTrue);
					ball1 = true;
					ball0 = false;
					ball2 = false;
					ball3 = false;
					btn0.setBackground(colorFalse);
					btn2.setBackground(colorFalse);
					btn3.setBackground(colorFalse);
				} else {
					btn1.setBackground(colorFalse);
					ball1 = false;
				}

			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball2 == false) {
					btn2.setBackground(colorTrue);
					ball2 = true;
					ball0 = false;
					ball1 = false;
					ball3 = false;
					btn0.setBackground(colorFalse);
					btn1.setBackground(colorFalse);
					btn3.setBackground(colorFalse);
				} else {
					btn2.setBackground(colorFalse);
					ball2 = false;
				}

			}
		});

		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ball3 == false) {
					btn3.setBackground(colorTrue);
					ball3 = true;
					ball0 = false;
					ball2 = false;
					ball1 = false;
					btn0.setBackground(colorFalse);
					btn1.setBackground(colorFalse);
					btn2.setBackground(colorFalse);
				} else {
					btn3.setBackground(colorFalse);
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
		btnSave.setBounds(44, 200, 89, 23);
		getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogResult = MyDialogResults.CANCEL;
				setVisible(false);
			}
		});
		btnCancel.setBounds(154, 200, 89, 23);
		getContentPane().add(btnCancel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(set_ball.this, "Exit?");
				if (result == JOptionPane.YES_OPTION) {
					setVisible(false);
				}
			}
		});
	}

}