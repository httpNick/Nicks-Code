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
public class ElipseToolAction extends AbstractTool
{
  /** Name of the Ellipse. */
  private static final String NAME = "Ellipse";

  /**
   * Construct Elipse action.
   * 
   * @param the_panel panel associated with this action.
   */
  public ElipseToolAction(final DrawingArea the_panel)
  {
    super(NAME, the_panel, KeyEvent.VK_E);
  }
}
