/*
 * Drawing Area - TCSS 305 Spring 2013
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputAdapter;

/**
 * The main drawing area for the program.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 7, 2013
 */

@SuppressWarnings("serial")
public class DrawingArea extends JPanel
{

  /**
   * The default width of the frame.
   */
  public static final Dimension DEFAULT_SIZE = new Dimension(400, 300);

  /**
   * The default height of the frame.
   */
  public static final int DEFAULT_HEIGHT = 300;

  /**
   * The default color of the frame.
   */
  public static final Color DEFAULT_COLOR = Color.white;

  /**
   * The amount of pixels in between each line for the grid.
   */
  private static final int GRID_SPACING = 10;

  /** Name of pencil tool. */
  private static final String PENCIL = "Pencil";
  /** Name of pencil tool. */
  private static final String LINE = "Line";
  /** Name of pencil tool. */
  private static final String RECTANGLE = "Rectangle";
  /** Name of pencil tool. */
  private static final String ELLIPSE = "Ellipse";

  /**
   * The color to be drawn with.
   */
  private Color my_color = Color.black;

  /**
   * The line width.
   */
  private int my_thickness;

  /** List of thicknesses used to redraw shapes with their respective thickness. */
  private List<Integer> my_thickness_list = new ArrayList<Integer>();

  /** List of colors used to redraw shapes with their respective color. */
  private List<Color> my_color_list = new ArrayList<Color>();

  /**
   * String of the selected shape to be drawn.
   */
  private String my_selected_name;
  /**
   * list of shapes.
   */
  private final List<Shape> my_shape_list;
  /**
   * The current point.
   */
  private Point my_current;
  /**
   * The initial point.
   */
  private Point my_initial;
  /** The most current line. */
  private Line2D.Double my_line = new Line2D.Double();

  /** the current rectangle. */
  private Rectangle2D.Double my_rectangle = new Rectangle2D.Double();

  /** the most current Ellipse. */
  private Ellipse2D.Double my_ellipse = new Ellipse2D.Double();

  /** The most current general path. */
  private GeneralPath my_pencil;

  /** Boolean for the grid. */
  private boolean my_is_grid;

  /**
   * Constructs the drawing area.
   * 
   * @param the_tool_bar the tool bar to used in the program.
   */
  public DrawingArea(final JToolBar the_tool_bar)
  {
    super();
    my_shape_list = new ArrayList<Shape>();
    setUp();

  }

  /**
   * A helper method to setup the DrawingArea.
   */
  private void setUp()
  {
    my_thickness = 1;
    final AnActionListener listener = new AnActionListener();
    addMouseMotionListener(listener);
    addMouseListener(listener);
    setPreferredSize(DEFAULT_SIZE);
    setBackground(DEFAULT_COLOR);
    my_selected_name = PENCIL;
    my_pencil = new GeneralPath();
  }

  /**
   * A method to set color.
   * 
   * @param the_color is the color to be set.
   */
  public void setColor(final Color the_color)
  {
    my_color = the_color;
  }

  /** clears the panel. */
  public void clear()
  {
    my_shape_list.clear();
    my_color_list.clear();
    my_thickness_list.clear();
    my_pencil = new GeneralPath();
    my_line = new Line2D.Double();
    my_rectangle = new Rectangle2D.Double();
    my_ellipse = new Ellipse2D.Double();
    setCurrentPoint(new Point());
    setInitialPoint(new Point());
    repaint();
  }

  /**
   * Sets if the grid is shown or not.
   * 
   * @param the_choice if the grid is being shown.
   */
  public void setGrid(final boolean the_choice)
  {
    my_is_grid = the_choice;
    repaint();
  }

  /**
   * Sets the string of the selected shape.
   * 
   * @param the_selected the name of the selected tool.
   */
  public void setSelected(final String the_selected)
  {
    my_selected_name = the_selected;
  }

  /**
   * Gives the name of the selected tool.
   * 
   * @return the selected tool.
   */
  public String getSelected()
  {
    return my_selected_name;
  }

  /**
   * Sets the current point.
   * 
   * @param the_current point from mouseDragged.
   */
  public void setCurrentPoint(final Point the_current)
  {
    my_current = the_current;
  }

  /**
   * A method to set the initial point.
   * 
   * @param the_initial point from mousePressed.
   */
  public void setInitialPoint(final Point the_initial)
  {
    my_initial = the_initial;
  }

  /**
   * A method to change the Stroke width.
   * 
   * @param the_stroke the amount of stroke.
   */
  public void setStrokeAmount(final int the_stroke)
  {
    my_thickness = the_stroke;
  }

  /**
   * Adds a shape to this panel to be drawn.
   * 
   * @param the_shape the shape to be drawn.
   */
  public void addShape(final Shape the_shape)
  {
    my_shape_list.add(the_shape);
  }

  /**
   * Returns the list of shapes.
   * 
   * @return list of shapes.
   */
  public List<Shape> getShapes()
  {
    final List<Shape> copied_list = new ArrayList<>();
    for (Shape s : my_shape_list)
    {
      copied_list.add(s);
    }
    return copied_list;
  }

  /** {@inheritDoc} */
  @Override
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setPaint(my_color);
    g2d.setStroke(new BasicStroke(my_thickness));
    if (LINE.equals(getSelected()) && my_current != null && my_initial != null)
    {
      my_line.setLine(my_initial, my_current);
      g2d.draw(my_line);
    }
    else if (PENCIL.equals(getSelected()))
    {
      g2d.draw(my_pencil);
    }
    else if (RECTANGLE.equals(getSelected()) && my_current != null
        && my_initial != null && getSelected() != null)
    {
      if (my_current.getX() > my_initial.getX() && my_current.getY() > my_initial.getY())
      {
        my_rectangle.setRect(my_initial.getX(), my_initial.getY(), my_current.getX()

          - my_initial.getX(), my_current.getY() - my_initial.getY());
      }
      else if (my_current.getX() > my_initial.getX() && my_current.getY() < my_initial.getY())
      {
        my_rectangle.setRect(my_initial.getX(), my_current.getY(), my_current.getX()
                                                                   - my_initial.getX(),
                             my_initial.getY() - my_current.getY());
      }
      else if (my_current.getX() < my_initial.getX() && my_current.getY() > my_initial.getY())
      {
        my_rectangle.setRect(my_current.getX(), my_initial.getY(), my_initial.getX()
                                                                   - my_current.getX(),
                             my_current.getY() - my_initial.getY());
      }
      else
      {
        my_rectangle.setRect(my_current.getX(), my_current.getY(), my_initial.getX()
                                                                   - my_current.getX(),
                             my_initial.getY() - my_current.getY());
      }
      g2d.draw(my_rectangle);
    }
    else if (ELLIPSE.equals(getSelected()) && my_current != null && my_initial != null)
    {
      if (my_current.getX() > my_initial.getX() && my_current.getY() > my_initial.getY())
      {
        my_ellipse.setFrame(my_initial.getX(), my_initial.getY(), my_current.getX()

          - my_initial.getX(), my_current.getY() - my_initial.getY());
      }
      else if (my_current.getX() > my_initial.getX() && my_current.getY() < my_initial.getY())
      {
        my_ellipse.setFrame(my_initial.getX(), my_current.getY(), my_current.getX()
                                                                  - my_initial.getX(),
                            my_initial.getY() - my_current.getY());
      }
      else if (my_current.getX() < my_initial.getX() && my_current.getY() > my_initial.getY())
      {
        my_ellipse.setFrame(my_current.getX(), my_initial.getY(), my_initial.getX()
                                                                  - my_current.getX(),
                            my_current.getY() - my_initial.getY());
      }
      else
      {
        my_ellipse.setFrame(my_current.getX(), my_current.getY(), my_initial.getX()
                                                                  - my_current.getX(),
                            my_initial.getY() - my_current.getY());
      }
      g2d.draw(my_ellipse);
    }
    if (!my_shape_list.isEmpty())
    {
      for (int i = 0; i < my_shape_list.size(); i++)
      {
        g2d.setColor(my_color_list.get(i));
        g2d.setStroke(new BasicStroke(my_thickness_list.get(i)));
        g2d.draw(my_shape_list.get(i));
      }
    }
    if (my_is_grid)
    {
      final int wide = getWidth();
      final int height = getHeight();
      g2d.setColor(Color.gray);
      g2d.setStroke(new BasicStroke(1));
      for (int i = 0; i < wide; i = i + GRID_SPACING)
      {
        g2d.drawLine(i, 0, i, height);
      }
      for (int i = 0; i < height; i = i + GRID_SPACING)
      {
        g2d.drawLine(0, i, wide, i);
      }
    }
  }

  /**
   * An inner class that calls doSomething() on the_object.
   */
  class AnActionListener extends MouseInputAdapter
  {
    @Override
    public void mousePressed(final MouseEvent the_event)
    {
      setInitialPoint(the_event.getPoint());
      setCurrentPoint(the_event.getPoint());
      if (PENCIL.equals(getSelected()))
      {
        if (my_pencil.getCurrentPoint() == null)
        {
          my_pencil.moveTo(the_event.getX(), the_event.getY());
        }
      }
      repaint();
    }

    @Override
    public void mouseDragged(final MouseEvent the_event)
    {
      setCurrentPoint(the_event.getPoint());
      if (my_pencil.getCurrentPoint() != null && PENCIL.equals(getSelected()))
      {
        my_pencil.lineTo(the_event.getX(), the_event.getY());
      }
      repaint();
    }

    @Override
    public void mouseReleased(final MouseEvent the_event)
    {
      my_color_list.add(my_color);
      my_thickness_list.add(my_thickness);
      if (LINE.equals(getSelected()))
      {
        addShape(new Line2D.Double(my_initial, my_current));
      }
      else if (RECTANGLE.equals(getSelected()))
      {
        addShape(new Rectangle2D.Double(my_rectangle.getX(), my_rectangle.getY(),
                                        my_rectangle.getWidth(), my_rectangle.getHeight()));
      }
      else if (ELLIPSE.equals(getSelected()))
      {
        addShape(new Ellipse2D.Double(my_ellipse.getX(), my_ellipse.getY(),
                                      my_ellipse.getWidth(), my_ellipse.getHeight()));
      }
      else if (PENCIL.equals(getSelected()))
      {
        addShape(my_pencil);
        my_pencil = new GeneralPath();
      }
    }
  }
}
