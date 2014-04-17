import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.JFrame;

/*
 * TCSS 305. Spring 2013
 */

/**
 * The Graphical User Interface for PowerPaint.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 9, 2013
 */

public class GUI
{
  /**
   * Construct the GUI.
   */
  public GUI()
  {
    setupGUI();
  }

  /**
   * Sets up the GUI.
   */
  private void setupGUI()
  {
    final JFrame gui_frame = new JFrame("TCSS 305 PowerPaint, Spring 2013");
    final MenuBar menu_bar = new MenuBar(gui_frame);
    final ToolBar tool_bar = new ToolBar();
    final DrawingArea panel = new DrawingArea(tool_bar);
    final ColorChooserAction color_action = new ColorChooserAction(tool_bar, menu_bar, panel);
    menu_bar.createColorMenuButton(color_action);
    tool_bar.createColorButton(color_action);
    menu_bar.createThicknessButtons(panel);
    final Action[] actions =
    {new PencilToolAction(panel), new LineToolAction(panel),
     new RectangleToolAction(panel), new ElipseToolAction(panel)};

    for (Action a : actions)
    {
      menu_bar.createToolMenuButton(a);
      tool_bar.createToggleButton(a);
    }
    menu_bar.setUpClearButton(panel);
    menu_bar.setUpGridButton(panel);
    gui_frame.setJMenuBar(menu_bar);
    gui_frame.add(tool_bar, BorderLayout.SOUTH);
    gui_frame.add(panel, BorderLayout.CENTER);
    gui_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui_frame.pack();
    gui_frame.setLocationRelativeTo(null);
    gui_frame.setVisible(true);

  }
}
