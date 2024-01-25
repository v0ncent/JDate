// JDate Class
// @Auth v0ncent
package JDate.JDateFramework;

import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.Listeners.*;
import JDate.JDateFramework.TimelineElements.TimelineElement;
import JDate.JDateFramework.TimelinePlayer.TimelinePlayer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A wrapper of the JFrame Object as a singleton instance
 */
public final class JDate extends TimelinePlayer implements Constants {
    /** Singleton instance of jDate OBJ **/
    private static JDate jDate = null;

    /** Instance of logger for JDate class */
    @Getter
    private static final Logger LOGGER = LoggerFactory.getLogger(JDate.class);

    /** Name of JFrame window, defaults to "JDate Window"*/
    @Getter
    @Setter
    private String windowName;
    /** Exit operation of JFrame Window, defaults to EXIT_ON_CLOSE
     * @see JFrame
     * */
    @Getter
    @Setter
    private int exitOperation;
    /** Width of JFrame Window, defaults to user's screen width
     * @see Toolkit
     * */
    @Getter
    @Setter
    private double width;
    /** Height of JFrame Window, defaults to user's screen height
     * @see Toolkit
     * */
    @Getter
    @Setter
    private double height;
    /** If the JFrame window is visible or not, defaults to true*/
    @Getter
    @Setter
    private boolean isVisible;
    /**Window Icon, defaults to null
     * @see Image
     * **/
    @Getter
    @Setter
    private Image icon;

    /** Width of user's primary screen
     * @see Toolkit
     * */
    @Getter
    private final double userScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /** Height of user's primary screen
     * @see Toolkit
     * */
    @Getter
    private final double userScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    /** JFrame Instance of JDate OBJ
     * @see JFrame
     * **/
    @Getter
    private final JFrame frame;

    /**
     * JDate's window listener.
     */
    private final JDateWindowListener jDateWindowListener = new JDateWindowListener();

    /**
     * JDate's key listener.
     */
    private final JDateKeyboardListener jDateKeyboardListener = new JDateKeyboardListener();

    /**
     * JDate's component listener.
     */
    private final JDateComponentListener jDateComponentListener = new JDateComponentListener();

    /**
     * JDate's focus listener.
     */
    private final JDateFocusListener jDateFocusListener = new JDateFocusListener();

    /**
     * JDate's hierarchy bounds listener.
     */
    private final JDateHierarchyBoundsListener jDateHierarchyBoundsListener = new JDateHierarchyBoundsListener();

    /**
     * JDate's hierarchy listener.
     */
    private final JDateHierarchyListener jDateHierarchyListener = new JDateHierarchyListener();

    /**
     * JDate's mouse listener listener.
     */
    private final JDateMouseListener jDateMouseListener = new JDateMouseListener();

    /**
     * JDate's mouse motion listener.
     */
    private final JDateMouseMotionListener jDateMouseMotionListener = new JDateMouseMotionListener();

    /**
     * JDate's mousewheel listener.
     */
    private final JDateMouseWheelListener jDateMouseWheelListener = new JDateMouseWheelListener();

    /**
     * Creates a JFrame OBJ from the passed values
     * @param name Name of JFrame window
     * @param width Width of JFrame window
     * @param height Height of JFrame window
     * @param exitOperation Default close method of JFrame window
     * @param isVisible If JFrame window is visible or not
     * @param icon Icon of JFrame window
     */
    private JDate(String name, double width, double height, int exitOperation, boolean isVisible, Image icon) {
        this.windowName = name;
        this.exitOperation = exitOperation;
        this.width = width;
        this.height = height;
        this.isVisible = isVisible;
        this.icon = icon;

        this.frame = new JFrame(name);
        this.frame.setDefaultCloseOperation(exitOperation);
        this.frame.setSize((int) width,(int) height);
        this.frame.addWindowListener(this.jDateWindowListener);
        this.frame.addKeyListener(this.jDateKeyboardListener);
        this.frame.addComponentListener(this.jDateComponentListener);
        this.frame.addFocusListener(this.jDateFocusListener);
        this.frame.addHierarchyBoundsListener(this.jDateHierarchyBoundsListener);
        this.frame.addHierarchyListener(this.jDateHierarchyListener);
        this.frame.addMouseListener(this.jDateMouseListener);
        this.frame.addMouseMotionListener(this.jDateMouseMotionListener);
        this.frame.addMouseWheelListener(this.jDateMouseWheelListener);

        if (icon != null) {
            this.frame.setIconImage(icon);
        }

        this.frame.setVisible(isVisible);
    }

    /**
     * Gets the single instance of JDate OBJ.
     * @return singleton instance of JDate OBJ, if no singleton instance is made obj contains default values
     */
    public static synchronized JDate getInstance() {
        if (jDate == null) {
            jDate = new JDate("JDate Window",
                    Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    JFrame.EXIT_ON_CLOSE,
                    true,
                    new ImageIcon(DEFAULT_ICON_PATH,  "JDate default image icon.").getImage()
            );
        }

        return jDate;
    }

    /**
     * Runs JDate timeline player framework until window is closed.
     * @see TimelineElement
     * @implNote The timeline player plays scenes and transitions in the order in which they are added to the script list
     */
    public void run() throws NoScenesException {
        this.runPlayer();
    }

    @Override
    public boolean equals(Object o) {
        // since this class will only ever have one instance of it we can just check if it is a jdate instance
        return o instanceof JDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowName, exitOperation, width, height, isVisible, icon, frame);
    }

}
