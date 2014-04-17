import java.awt.Color;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/*
 * TCSS 305. Spring 2013.
 */

/**
 * The JToolBar for PowerPaint.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 9, 2013
 */
@SuppressWarnings("serial")
public class ToolBar extends JToolBar
{
  /** A button group for the tool bar buttons. */
  private final ButtonGroup my_bgroup;

  /**
   * Constructs the toolbar.
   */
  public ToolBar()
  {
    super();
    my_bgroup = new ButtonGroup();
  }

  /**
   * Create a JToggleButton for the ToolBar.
   * 
   * @param an_action to associate with the created JToggleButton
   */
  public void createToggleButton(final Action an_action)
  {
    final JToggleButton toggle_button = new JToggleButton(an_action);
    if ("Pencil".equals(toggle_button.getName()))
    {
      toggle_button.setSelected(true);
    }
    my_bgroup.add(toggle_button);
    add(toggle_button);
  }

  /**
   * The color button on the tool bar.
   * 
   * @param an_action associated with the button.
   */
  public void createColorButton(final Action an_action)
  {
    final JButton color_button = new JButton(an_action);
    color_button.setBackground(Color.black);
    my_bgroup.add(color_button);
    add(color_button);
  }
}
