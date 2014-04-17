package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HotKeyController extends KeyAdapter {

	private GUI my_gui;
	
	public HotKeyController(GUI the_gui) {
		my_gui = the_gui;
	}
	@Override
	public void keyPressed (KeyEvent the_key) {
		if (the_key.getKeyCode() == KeyEvent.VK_P) {
			my_gui.pause();
		}
	}
}
