import java.awt.event.KeyEvent;

/*
 * TCSS 305. Spring 2013.
 */

/**
 * Action for the line tool.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 9, 2013
 */
@SuppressWarnings("serial")
public class LineToolAction extends AbstractTool
{
  /**
   * Line's name in a string.
   */
  private static final String NAME = "Line";

  /**
   * Construct line action.
   * 
   * @param the_panel panel associated with this action.
   */
  public LineToolAction(final DrawingArea the_panel)
  {
    super(NAME, the_panel, KeyEvent.VK_L);
  }
}
