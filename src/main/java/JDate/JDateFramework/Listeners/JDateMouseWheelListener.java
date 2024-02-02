package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.function.Consumer;

public final class JDateMouseWheelListener implements MouseWheelListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateMouseWheelListener.class);

    private static Consumer<MouseWheelEvent>  mouseWheelEventAction = null;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        ListenerUtil.acceptIfNotNull(mouseWheelEventAction, e);
    }

    public static void onMouseWheelMoved(Consumer<MouseWheelEvent> wantedAction) {
        mouseWheelEventAction = wantedAction;
    }
}
