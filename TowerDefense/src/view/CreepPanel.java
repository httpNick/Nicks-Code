package view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

public class CreepPanel extends JPanel {

	public CreepPanel() {
		setup();
	}

	private void setup() {
		buttons();
		setPreferredSize(new Dimension(329, 139));
		setFocusable(false);

		for (Component c : this.getComponents()) {
			c.setFocusable(false);
		}
	}

	private void buttons() {
		JButton btn1 = new JButton("Q");
		btn1.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Shade.jpg")));
		add(btn1);

		JButton btn2 = new JButton("W");
		btn2.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Peasant.jpg")));
		add(btn2);

		JButton btn3 = new JButton("E");
		btn3.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Archer.jpg")));
		add(btn3);

		JButton btn4 = new JButton("R");
		btn4.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Grunt.jpg")));
		add(btn4);

		JButton btn5 = new JButton("A");
		btn5.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/BlackDragon.jpg")));
		add(btn5);

		JButton btn6 = new JButton("S");
		btn6.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Ghoul.jpg")));
		add(btn6);

		JButton btn7 = new JButton("D");
		btn7.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Murloc.jpg")));
		add(btn7);

		JButton btn8 = new JButton("F");
		btn8.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/DarkTrollShadowPriest.jpg")));
		add(btn8);

		JButton btn9 = new JButton("Z");
		btn9.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/Raider.jpg")));
		add(btn9);

		JButton btn10 = new JButton("X");
		btn10.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/FlyingMachine.jpg")));
		add(btn10);

		JButton btn11 = new JButton("C");
		btn11.setIcon(new ImageIcon(getClass().getResource(
				"/images/creeps/JunkGolem.jpg")));
		add(btn11);

		JButton btn12 = new JButton("V");
		btn12.setIcon(new ImageIcon(getClass().getResource(
				"/images/upgrades/Castle.jpg")));
		add(btn12);

	}
}
