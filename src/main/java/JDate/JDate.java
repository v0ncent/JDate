// JDate Class
// @Auth v0ncent
package JDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * A wrapper of the JFrame Object as a singleton instance
 */
public final class JDate {
    /** Singleton instance of jDate OBJ **/
    private static JDate jDate = null;

    /** Instance of logger for JDate class */
    private static final Logger LOGGER = LoggerFactory.getLogger(JDate.class);

    /** Name of JFrame window, defaults to "JDate Window"*/
    private String name;
    /** Exit operation of JFrame Window, defualts to EXIT_ON_CLOSE
     * @see JFrame
     * */
    private int exitOperation;
    /** Width of JFrame Window, defaults to user's screen width
     * @see Toolkit
     * */
    private double width;
    /** Height of JFrame Window, defaults to user's screen height
     * @see Toolkit
     * */
    private double height;
    /** Width of user's primary screen
     * @see Toolkit
     * */
    private final double userScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /** Height of user's primary screen
     * @see Toolkit
     * */
    private final double userScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**Window Icon, defaults to null
     * @see Image
     * **/
    private Image icon;

    /**JFrame Instance of JDate OBJ
     * @see JFrame
     * **/
    private final JFrame frame;

    /** If the JFrame window is visible or not, defaults to true*/
    private boolean isVisible;

    private JDate(String name, double width, double height, int exitOperation, boolean isVisible, Image icon) {
        this.name = name;
        this.exitOperation = exitOperation;
        this.width = width;
        this.height = height;
        this.isVisible = isVisible;
        this.icon = icon;

        this.frame = new JFrame(name);
        this.frame.setDefaultCloseOperation(exitOperation);
        this.frame.setSize((int) width,(int) height);

        if (icon != null) {
            this.frame.setIconImage(icon);
        }

        this.frame.setVisible(isVisible);
    }

    public static synchronized JDate getInstance() {
        if (jDate == null) {
            jDate = new JDate("JDate Window",
                    Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                    JFrame.EXIT_ON_CLOSE,
                    true,
                    null);
        }

        return jDate;
    }

    // -GETTERS / SETTERS-
    public String getName() {
        return this.name;
    }
    public int getExitOperation() {
        return this.exitOperation;
    }
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
    public boolean isVisible() {
        return this.isVisible;
    }
    public double getUserScreenWidth() {
        return this.userScreenWidth;
    }
    public double getGetUserScreenHeight() {
        return this.userScreenHeight;
    }
    public Image getIcon() {
        return this.icon;
    }
    public JFrame getFrame() {
        return this.frame;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setExitOperation(int exitOperation) {
        this.exitOperation = exitOperation;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
    }
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
