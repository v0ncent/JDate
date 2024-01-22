// JDate Class
// @Auth v0ncent
package JDate.JDateFramework;

import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.Listeners.JDateWindowListener;
import JDate.JDateFramework.TimelinePlayer.TimelinePlayer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

    @Getter
    private final JDateWindowListener jDateWindowListener = new JDateWindowListener();

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
     * Runs JDate timeline player framework until program is closed on separate thread.
     * @see Scene
     * @implNote The timeline player plays scenes and transitions in the order in which they are added to the script list
     */
    public void run() throws NoScenesException {
        this.runPlayer(this.getScript());
    }

    /**
     * JDate timeline player method. Paints scenes in order in which they are passed into JFrame window
     * @param script List of Scenes, Transitions to play into JFrame window
     * @throws NoScenesException When no scenes have been added to script list
     * @see Scene
     * @implNote The timeline player plays scenes and transitions in the order in which they are added to the script list
     */
    @Override
    protected void runPlayer(@NotNull ArrayList<Scene> script) throws NoScenesException {
        if (script.size() == 1 && script.get(0) == null) {
            throw new NoScenesException();
        }

        // game loop to play each scene in order of passage, when all scenes played go back to the beginning.
        int i = 0;
        while(this.isShouldRender()) {
            if (i == script.size()) {
                i = 0;
            }

            final Scene scene = script.get(i);
//            this.logPaintableElements(scene.getPaintableElements()); // uncomment when scene.playscene takes longer so we dont spam this log

            scene.playScene();
            i++;
        }
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
