package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class JDateMouseListener implements MouseListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateMouseListener.class);

    private static Consumer<MouseEvent> mouseClickedAction = null;
    private static Consumer<MouseEvent> mousePressedAction = null;
    private static Consumer<MouseEvent> mouseReleasedAction = null;
    private static Consumer<MouseEvent> mouseEnteredAction = null;
    private static Consumer<MouseEvent> mouseExitedAction = null;

    @Override
    public void mouseClicked(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mouseClickedAction, e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mousePressedAction, e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mouseReleasedAction, e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mouseEnteredAction, e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mouseExitedAction, e);
    }

    public static void onMouseClicked(Consumer<MouseEvent> wantedAction) {
        mouseClickedAction = wantedAction;
    }

    public static void onMousePressed(Consumer<MouseEvent> wantedAction) {
        mousePressedAction = wantedAction;
    }

    public static void onMouseReleasedAction(Consumer<MouseEvent> wantedAction) {
        mouseReleasedAction = wantedAction;
    }

    public static void onMouseEntered(Consumer<MouseEvent> wantedAction) {
        mouseEnteredAction = wantedAction;
    }

    public static void onMouseExited(Consumer<MouseEvent> wantedAction) {
        mouseExitedAction = wantedAction;
    }
}
