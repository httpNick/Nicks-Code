import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * Abstract class of tool objects.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 16, 2013
 */
@SuppressWarnings("serial")
public abstract class AbstractTool extends AbstractAction
{

  /** The JPanel associated with the Action. */
  private final DrawingArea my_panel;

  /** The Mnemonic Key. */
  private final Integer my_mnemonic;

  /**
   * Constructs an AbstractTool.
   * 
   * @param the_name the name for the tool.
   * @param the_drawing_area the drawing area associated with the tool.
   * @param the_mnemonic_key the key for setting mnemonic.
   */
  protected AbstractTool(final String the_name, final DrawingArea the_drawing_area,
                         final Integer the_mnemonic_key)
  {
    super(the_name);
    my_panel = the_drawing_area;
    my_mnemonic = the_mnemonic_key;
    setUpAction(the_name);

  }

  /**
   * Sets up the action for constructor.
   * 
   * @param the_name the name of the action.
   */
  private void setUpAction(final String the_name)
  {
    putValue(Action.MNEMONIC_KEY, my_mnemonic);
    putValue(Action.SELECTED_KEY, true);
    putValue(Action.NAME, the_name);

  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    my_panel.setSelected((String) getValue(Action.NAME.toString()));
  }
}
