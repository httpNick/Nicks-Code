package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.Timer;

import model.Board;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Observer {

	private static final int FPS = 30;

	private static final Dimension window_size = new Dimension(600, 300);

	private int counter = 0;

	private Board my_board;

	private final GamePanel my_panel;

	private final InfoPanel my_info_panel;

	private final Timer my_timer;

	private boolean is_paused = false;

	public GUI() {
		setup();
		my_board = new Board();
		my_board.addObserver(this);
		my_panel = new GamePanel(window_size.width, window_size.height,
				my_board, FPS);
		my_info_panel = new InfoPanel(window_size.width, window_size.height,
				my_board);

		my_timer = new Timer(1000 / FPS, new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent the_event) {
				step();
			}

		});
		// addKeyListener(new Controller(my_board));
		addKeyListener(new HotKeyController(this));
		MouseController controller = new MouseController(my_board, FPS);
		addMouseListener(controller);
		addMouseMotionListener(controller);
		add(my_panel, BorderLayout.CENTER);
		add(my_info_panel, BorderLayout.EAST);
		pack();
		setResizable(false);
	}

	private void setup() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void step() {
		if (counter % FPS == 0) {
			my_board.step();
			counter = 0;
		}
		counter++;
		my_panel.paintPanel(counter);
	}

	public void start() {
		my_timer.start();
	}

	public void pause() {
		if (is_paused == false) {
			my_timer.stop();
			is_paused = true;
		} else {
			my_timer.start();
			is_paused = false;
		}
	}

	public void update(Observable arg0, Object arg1) {
		my_panel.paintPanel(counter);
		my_info_panel.changeLabelText((int) my_board.getMoney(),
				(int) my_board.getLevel(), (int) my_board.getLives(),
				(int) my_board.getEnemiesLeft());
	}
}
