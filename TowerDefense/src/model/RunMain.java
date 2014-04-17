package model;

import view.GUI;

public class RunMain {
		
	public static void main (String[] args) {
		try {
			GUI tower_defense_gui = new GUI();
			tower_defense_gui.start();
	
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
