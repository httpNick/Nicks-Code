import java.awt.event.KeyEvent;

/*
 * TCSS 305. Spring 2013.
 */

/**
 * Action for the Rectangle tool.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 9, 2013
 */
@SuppressWarnings("serial")
public class RectangleToolAction extends AbstractTool
{
  /** Name of the Rectangle. */
  private static final String NAME = "Rectangle";

  /**
   * Construct Rectangle action.
   * 
   * @param the_panel panel associated with this action.
   */
  public RectangleToolAction(final DrawingArea the_panel)
  {
    super(NAME, the_panel, KeyEvent.VK_R);
  }
}
