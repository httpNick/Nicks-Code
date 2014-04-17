/*
 * TCSS 305. Spring 2013.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

/**
 * The JToolBar for PowerPaint.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 9, 2013
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
  /**
   * Array of the thickness options to be made.
   */
  private static final int[] RADIO_BUTTONS = {1, 2, 4};
  /** The Option pane displayed when help button is clicked. */
  private static JOptionPane my_help_pane;
  /** the file menu. */
  private final JMenu my_file_menu = new JMenu("File");
  /** the file options. */
  private final JMenu my_options_menu = new JMenu("Options");
  /** the file tools. */
  private final JMenu my_tools_menu = new JMenu("Tools");
  /** the help menu. */
  private final JMenu my_help_menu = new JMenu("Help");
  /** the button group for tool menu items. */
  private final ButtonGroup my_tool_group = new ButtonGroup();
  /** the button group for thickness menu items. */
  private final ButtonGroup my_thickness_group = new ButtonGroup();
  /** A button to exit the program. */
  private final JMenuItem my_quit_button = new JMenuItem("Quit");
  /** A button to clear the DrawingArea. */
  private final JMenuItem my_clear_button = new JMenuItem("Clear");
  /** A CheckBox button to go grid mode. */
  private final JCheckBox my_grid = new JCheckBox("Grid");
  /** A menu item for the Thickness radio buttons. */
  private final JMenu my_thickness = new JMenu("Thickness");
  /** the button to pull up the about index. */
  private final JMenuItem my_about = new JMenuItem("About...");

  /**
   * Constructs the menu bar.
   * 
   * @param the_frame the JFrame which will contain this JMenuBar
   */
  public MenuBar(final JFrame the_frame)
  {
    super();
    setUpMenu(the_frame);

  }

  /**
   * Sets up the menu bar.
   * 
   * @param the_frame the Jframe the menu is attached to.
   */
  private void setUpMenu(final JFrame the_frame)
  {
    my_file_menu.setMnemonic(KeyEvent.VK_F);
    my_options_menu.setMnemonic(KeyEvent.VK_O);
    my_tools_menu.setMnemonic(KeyEvent.VK_T);
    my_help_menu.setMnemonic(KeyEvent.VK_H);
    my_about.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        showErrorPane("TCSS 305 - PowerPaint", "About");
      }
    });

    my_quit_button.setMnemonic(KeyEvent.VK_Q);
    my_clear_button.setMnemonic(KeyEvent.VK_C);
    my_quit_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        the_frame.dispose();
      }
    });

    my_grid.setMnemonic(KeyEvent.VK_G);
    my_thickness.setMnemonic(KeyEvent.VK_T);
    my_file_menu.add(my_quit_button);
    my_file_menu.addSeparator();
    my_file_menu.add(my_clear_button);

    my_options_menu.add(my_grid);
    my_options_menu.add(my_thickness);

    my_about.setMnemonic(KeyEvent.VK_A);
    my_help_menu.add(my_about);

    add(my_file_menu);
    add(my_options_menu);
    add(my_tools_menu);
    add(my_help_menu);
  }

  /**
   * Creates a radio button menu item, associates an action with the button,
   * adds the button to a button group, adds the button to the Tools menu.
   * 
   * @param the_action the Action to associate with the new button being
   *          created.
   */
  public void createToolMenuButton(final Action the_action)
  {
    final JRadioButtonMenuItem created_button = new JRadioButtonMenuItem(the_action);
    if ("Pencil".equals(created_button.getName()))
    {
      created_button.setSelected(true);
    }
    my_tool_group.add(created_button);
    my_tools_menu.add(created_button);
  }

  /**
   * Displays the about.
   * 
   * @param the_message the message
   * @param the_title of the pane.
   */
  private static void showErrorPane(final String the_message, final String the_title)
  {
    my_help_pane = new JOptionPane(the_message, JOptionPane.INFORMATION_MESSAGE);
    final JDialog dialog = my_help_pane.createDialog(the_title);
    dialog.setAlwaysOnTop(true);
    dialog.setVisible(true);
  }

  /**
   * Sets up the clear button to work for the specified Jpanel.
   * 
   * @param the_panel the JPanel to be cleared.
   */
  public void setUpClearButton(final DrawingArea the_panel)
  {
    my_clear_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        the_panel.clear();
      }
    });
  }

  /**
   * Sets up the grid button to work for the specified JPanel.
   * 
   * @param the_panel the JPanel to be gridded.
   */
  public void setUpGridButton(final DrawingArea the_panel)
  {
    my_grid.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        if (my_grid.isSelected())
        {
          the_panel.setGrid(true);
        }
        else
        {
          the_panel.setGrid(false);
          the_panel.repaint();
        }
      }
    });
  }

  /**
   * Creates a radio button menu item, associates an action with the button,
   * adds the button to a button group, adds the button to the options menu's
   * thickness menu.
   * 
   * @param the_name of the button.
   * @param the_area the area in which the thickness is changed on.
   * @param the_amount the # the button holds.
   */
  private void createThicknessButton(final String the_name, final DrawingArea the_area,
                                     final int the_amount)
  {
    final JRadioButtonMenuItem created_button = new JRadioButtonMenuItem(the_name);
    created_button.setSelected(true);
    created_button.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        the_area.setStrokeAmount(the_amount);
      }
    });
    created_button.setMnemonic(String.valueOf(the_amount).charAt(0));
    my_thickness_group.add(created_button);
    my_thickness.add(created_button);
  }

  /**
   * Creates radio buttons for thickness.
   * 
   * @param the_area the panel in which the thickness will be changed.
   */
  public void createThicknessButtons(final DrawingArea the_area)
  {

    for (int i = 0; i < RADIO_BUTTONS.length; i++)
    {
      createThicknessButton(String.valueOf(RADIO_BUTTONS[i]), the_area, RADIO_BUTTONS[i]);
    }
  }

  /**
   * Creates a button, associates it with an action, adds the button to a button
   * group, adds the button to the Tool's menu.
   * 
   * @param the_action Action to associate with the new button being created.
   */
  public void createColorMenuButton(final Action the_action)
  {
    final JMenuItem created_button = new JMenuItem(the_action);
    created_button.setBackground(Color.BLACK);
    created_button.setSelected(false);
    my_tool_group.add(created_button);
    my_tools_menu.add(created_button);
    my_tools_menu.addSeparator();
  }
}
