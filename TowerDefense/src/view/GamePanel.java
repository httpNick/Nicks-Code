package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Board;
import model.Enemy;
import model.Tower;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private final int FPS;
	
	private final Dimension my_panel_dimension;
	
	private final Board my_board;
	
	private int counter;
	
	private int x_scale;
	
	private int y_scale;
	
	private int x_increment;
	
	private int y_increment;

	public GamePanel(final int the_width, final int the_height, final Board the_board, final int the_FPS) {
		setBackground(Color.WHITE);
		FPS = the_FPS;
		my_panel_dimension = new Dimension(the_width, the_height);
		my_board = the_board;
		setup();
	}
	
	private void setup() {
		setPreferredSize(my_panel_dimension);
		x_scale = my_panel_dimension.width / 20;
		y_scale = my_panel_dimension.height / 10;
		x_increment = x_scale / FPS;
		y_increment = y_scale / FPS;
	}
	
	public void paintPanel(final int the_counter) {
		counter = the_counter;
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		final ArrayList<Enemy> enemy_list = my_board.getEnemyList();
		final ArrayList<Tower> tower_list = my_board.getTowers();
		
		g2d.setFont(new Font("Arial", Font.BOLD, y_scale));
		try {
		for (Enemy e: enemy_list) {
			int x_change = 0;
			int y_change = 0;
			if (e.getNextLocation().x > e.getLocation().x) {
				x_change = (x_increment * counter);
			} else if (e.getNextLocation().x < e.getLocation().x) {
				x_change = -(x_increment * counter);
			}
			if (e.getNextLocation().y > e.getLocation().y) {
				y_change = (y_increment * counter);
			} else if (e.getNextLocation().y < e.getLocation().y) {
				y_change = -(y_increment * counter);
			}
			g2d.drawOval(e.getX() * x_scale + x_change, e.getY() * y_scale + y_change, 
					x_scale, y_scale);
		}
		} catch (NullPointerException e) {
			repaint();
			
		}
		for (Tower t: tower_list) {
			g2d.drawString("T", 
					t.getLocation().x * x_scale, (t.getLocation().y + 1) * y_scale);
		}
		
		g2d.drawString("N", 
				my_board.getNodeLocation().x * x_scale, (my_board.getNodeLocation().y + 1) * y_scale);
		g2d.drawRect(my_board.getHouseLocation().x * x_scale, my_board.getHouseLocation().y * y_scale, 
				x_scale, y_scale);
		g2d.drawString("C", 
				my_board.getCursorLocation().x * x_scale, (my_board.getCursorLocation().y + 1) * y_scale);
	}
	
	
}
