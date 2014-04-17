import java.awt.EventQueue;

/*
 * Main. TCSS 305. Spring 2013.
 */

/**
 * Runs the program.
 * 
 * @author Nick Duncan (httpNick), (Nick.b.duncan@gmail.com)
 * @version May 7, 2013
 */
public final class GUIMain
{
  /**
   * Private constructor, to prevent instantiation of this class.
   */
  private GUIMain()
  {
    throw new IllegalStateException();
  }

  /**
   * Creates and makes visible a Power Paint.
   * 
   * @param the_args Command line arguments, ignored.
   */
  public static void main(final String... the_args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        new GUI();
      }
    });
  }

}
