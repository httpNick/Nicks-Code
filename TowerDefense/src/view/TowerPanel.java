package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Board;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class TowerPanel extends JPanel {

	private final Board my_board;

	public TowerPanel(int the_width, int the_height, Board the_board) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		my_board = the_board;
		setup();

	}

	private void setup() {
		buttons();
		setPreferredSize(new Dimension(291, 103));
		setFocusable(false);
		for (Component c : this.getComponents()) {
			c.setFocusable(false);
		}
	}

	private void buttons() {
		final JButton btn1 = new JButton();
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					my_board.setTowerImage(ImageIO.read(new File(
							"images/BTNChaosBlademaster.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		ImageIcon button_icon = new ImageIcon(getClass().getResource(
				"/images/BTNChaosBlademaster.jpg"));
		btn1.setIcon(button_icon);
		btn1.setText("1");
		add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setFocusable(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					my_board.setTowerImage(ImageIO.read(new File(
							"images/BTNChaosWarlord.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn2.setIcon(new ImageIcon(getClass().getResource(
				"/images/BTNChaosWarlord.jpg")));
		add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					my_board.setTowerImage(ImageIO.read(new File(
							"images/BTNCloakOfFlames.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn3.setIcon(new ImageIcon(getClass().getResource(
				"/images/BTNCloakOfFlames.jpg")));
		add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					my_board.setTowerImage(ImageIO.read(new File(
							"images/BTNCorruptedAncientOfWar.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn4.setIcon(new ImageIcon(getClass().getResource(
				"/images/BTNCorruptedAncientOfWar.jpg")));
		add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					my_board.setTowerImage(ImageIO.read(new File(
							"images/BTNCritterChicken.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn5.setIcon(new ImageIcon(getClass().getResource(
				"/images/BTNCritterChicken.jpg")));
		add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					my_board.setTowerImage(ImageIO.read(new File(
							"images/BTNDarkTroll.jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btn6.setIcon(new ImageIcon(getClass().getResource(
				"/images/BTNDarkTroll.jpg")));
		add(btn6);
	}
}
