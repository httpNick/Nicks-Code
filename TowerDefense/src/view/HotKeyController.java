package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HotKeyController extends KeyAdapter {

	private GUI my_gui;

	public HotKeyController(GUI the_gui) {
		my_gui = the_gui;
	}

	@Override
	public void keyPressed(KeyEvent the_key) {
		if (the_key.getKeyCode() == KeyEvent.VK_P) {
			my_gui.pause();
		} else if (the_key.getKeyCode() == KeyEvent.VK_1) {
			try {
				my_gui.my_board.setTowerImage(ImageIO.read(new File(
						"images/BTNChaosBlademaster.jpg")));
			} catch (IOException z) {
				z.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_2) {
			try {
				my_gui.my_board.setTowerImage(ImageIO.read(new File(
						"images/BTNChaosWarlord.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_3) {
			try {
				my_gui.my_board.setTowerImage(ImageIO.read(new File(
						"images/BTNCloakOfFlames.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_4) {
			try {
				my_gui.my_board.setTowerImage(ImageIO.read(new File(
						"images/BTNCorruptedAncientOfWar.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_5) {
			try {
				my_gui.my_board.setTowerImage(ImageIO.read(new File(
						"images/BTNCritterChicken.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_6) {
			try {
				my_gui.my_board.setTowerImage(ImageIO.read(new File(
						"images/BTNDarkTroll.jpg")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
