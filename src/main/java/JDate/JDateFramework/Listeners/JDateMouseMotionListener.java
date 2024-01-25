package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

public class JDateMouseMotionListener implements MouseMotionListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateMouseMotionListener.class);

    private static Consumer<MouseEvent> mouseDraggedAction = null;
    private static Consumer<MouseEvent> mouseMovedAction = null;

    @Override
    public void mouseDragged(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mouseDraggedAction, e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ListenerUtil.acceptIfNotNull(mouseMovedAction, e);
    }

    public static void onMouseDragged(Consumer<MouseEvent> wantedAction) {
        mouseDraggedAction = wantedAction;
    }

    public static void onMouseMoved(Consumer<MouseEvent> wantedAction) {
        mouseMovedAction = wantedAction;
    }
}
