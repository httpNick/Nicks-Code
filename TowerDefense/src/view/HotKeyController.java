package view;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Enemy;

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
		} else if (the_key.getKeyCode() == KeyEvent.VK_Q) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 50, 1, 1,
						5, ImageIO.read(new File("images/creeps/Shade.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_W) {
			try {
				my_gui.my_board
						.sendUnit(new Enemy(new Point(0, 0), 65, 3, 2, 10,
								ImageIO.read(new File(
										"images/creeps/Peasant.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_E) {
			try {
				my_gui.my_board
						.sendUnit(new Enemy(new Point(0, 0), 75, 9, 4, 20,
								ImageIO.read(new File(
										"images/creeps/Archer.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_R) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 100, 16,
						16, 35, ImageIO
								.read(new File("images/creeps/Grunt.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_A) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 125, 23, 9,
						45, ImageIO.read(new File(
								"images/creeps/BlackDragon.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_S) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 340, 31,
						14, 70, ImageIO
								.read(new File("images/creeps/Ghoul.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_D) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 480, 48,
						22, 98, ImageIO.read(new File(
								"images/creeps/Murloc.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_F) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 680, 60,
						28, 135, ImageIO.read(new File(
								"images/creeps/DarkTrollShadowPriest.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_Z) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 800, 75,
						37, 160, ImageIO.read(new File(
								"images/creeps/Raider.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_X) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 980, 90,
						50, 195, ImageIO.read(new File(
								"images/creeps/FlyingMachine.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_C) {
			try {
				my_gui.my_board.sendUnit(new Enemy(new Point(0, 0), 1140, 105,
						69, 245, ImageIO.read(new File(
								"images/creeps/JunkGolem.jpg"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (the_key.getKeyCode() == KeyEvent.VK_V) {
			// upgrade to next building for spawning units.
		}
	}
}
