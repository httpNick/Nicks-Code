import java.awt.event.KeyEvent;

/*
 * TCSS 305. Spring 2013.
 */

/**
 * Action for the pencil tool.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 9, 2013
 */
@SuppressWarnings("serial")
public class PencilToolAction extends AbstractTool
{
  /** The name of the pencil tool. */
  private static final String NAME = "Pencil";

  /**
   * Construct pencil action.
   * 
   * @param the_panel panel associated with this action.
   */
  public PencilToolAction(final DrawingArea the_panel)
  {
    super(NAME, the_panel, KeyEvent.VK_P);
  }
}
