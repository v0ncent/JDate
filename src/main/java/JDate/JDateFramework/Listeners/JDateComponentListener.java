package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.function.Consumer;

public final class JDateComponentListener implements ComponentListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateComponentListener.class);

    private static Consumer<ComponentEvent> componentResizedAction = null;
    private static Consumer<ComponentEvent> componentMovedAction = null;
    private static Consumer<ComponentEvent> componentShownAction = null;
    private static Consumer<ComponentEvent> componentHiddenAction = null;

    @Override
    public void componentResized(ComponentEvent e) {
        ListenerUtil.acceptIfNotNull(componentResizedAction, e);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        ListenerUtil.acceptIfNotNull(componentMovedAction, e);
    }

    @Override
    public void componentShown(ComponentEvent e) {
        ListenerUtil.acceptIfNotNull(componentShownAction, e);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        ListenerUtil.acceptIfNotNull(componentHiddenAction, e);
    }

    public static void onComponentResized(Consumer<ComponentEvent> wantedAction) {
        componentResizedAction = wantedAction;
    }

    public static void onComponentMoved(Consumer<ComponentEvent> wantedAction) {
        componentMovedAction = wantedAction;
    }

    public static void onComponentShown(Consumer<ComponentEvent> wantedAction) {
        componentShownAction = wantedAction;
    }

    public static void onComponentHidden(Consumer<ComponentEvent> wantedAction) {
        componentHiddenAction = wantedAction;
    }
}
