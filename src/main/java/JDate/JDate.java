// JDate Class
// @Auth v0ncent
package JDate;

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
public final class JDate {
    /** Singleton instance of jDate OBJ **/
    private static JDate jDate = null;

    /** Instance of logger for JDate class */
    @Getter
    private static final Logger LOGGER = LoggerFactory.getLogger(JDate.class);

    /** Name of JFrame window, defaults to "JDate Window"*/
    @Getter
    @Setter
    private String name;
    /** Exit operation of JFrame Window, defualts to EXIT_ON_CLOSE
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

    /**JFrame Instance of JDate OBJ
     * @see JFrame
     * **/
    @Getter
    private final JFrame frame;

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
                    new ImageIcon(Constants.DEFAULT_ICON_PATH,  "JDate default image icon.").getImage()
            );
        }

        return jDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // if o is null or its instance doesnt equal a jdate instance and not a jframe instance
        if (o == null || (getClass() != o.getClass() && !(o instanceof JFrame))) return false;

        // if it is a jframe instance and the passed object isn't a JFrame
        if (o instanceof JFrame && o.getClass() != this.getFrame().getClass()) return false;

        if (o instanceof JFrame) {
            JFrame jFrame = (JFrame) o;
            return exitOperation == jFrame.getDefaultCloseOperation() &&
                    Double.compare(jFrame.getWidth(), width) == 0 &&
                    Double.compare(jFrame.getHeight(), height) == 0 &&
                    isVisible == jFrame.isVisible() &&
                    Objects.equals(name, jFrame.getName()) &&
                    Objects.equals(icon, jFrame.getIconImage()) &&
                    Objects.equals(frame, jDate.frame);
        } else {
            JDate jDate = (JDate) o;
            return exitOperation == jDate.exitOperation &&
                    Double.compare(jDate.width, width) == 0 &&
                    Double.compare(jDate.height, height) == 0 &&
                    isVisible == jDate.isVisible && Double.compare(jDate.userScreenWidth, userScreenWidth) == 0
                    && Double.compare(jDate.userScreenHeight, userScreenHeight) == 0
                    && Objects.equals(name, jDate.name)
                    && Objects.equals(icon, jDate.icon)
                    && Objects.equals(frame, jDate.frame);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exitOperation, width, height, isVisible, icon, frame);
    }
}
