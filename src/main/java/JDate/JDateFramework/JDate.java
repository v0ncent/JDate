// JDate Class
// @Auth v0ncent
package JDate.JDateFramework;

import JDate.Exceptions.NoScenesException;
import JDate.PaintableElements.PaintableElement;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    /** Intro scene is the first scene that gets played within the JDate Framework*/
    @Getter
    @Setter
    private Scene intro = null;

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
    /** List of scenes to play in order*/
    @Getter
    private final ArrayList<Scene> script = new ArrayList<>();

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

    private void logPaintableElements(ArrayList<PaintableElement> elements) {
        for (PaintableElement e : elements) {
            e.getLogger().debug("Loaded: {}", e.getElementName());
        }
    }

    public void addScene(Scene scene) {
        script.add(scene);
    }

    public void run() throws NoScenesException {
        addScene(this.getIntro());

        if (script.size() == 1 && script.get(0) == null) {
            throw new NoScenesException();
        }

        // play each scene in order passed
        for (Scene scene : script) {
            logPaintableElements(scene.getPaintableElements());

            scene.playScene();
        }

    }

    @Override
    public boolean equals(Object o) {
        // since this class will only ever have one instance of it we can just check if it is a jdate instance
        return o instanceof JDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exitOperation, width, height, isVisible, icon, frame);
    }
}
