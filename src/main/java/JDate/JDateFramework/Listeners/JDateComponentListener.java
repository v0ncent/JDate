package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.function.Consumer;

public class JDateComponentListener implements ComponentListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateComponentListener.class);

    private static Consumer<ComponentEvent> componentResizedAction = null;
    private static Consumer<ComponentEvent> componentMovedAction = null;
    private static Consumer<ComponentEvent> componentShownAction = null;
    private static Consumer<ComponentEvent> componentHiddenAction = null;

    @Override
    public void componentResized(ComponentEvent e) {
        if (JDateComponentListener.componentResizedAction != null) {
            JDateComponentListener.componentResizedAction.accept(e);
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        if (JDateComponentListener.componentMovedAction != null) {
            JDateComponentListener.componentMovedAction.accept(e);
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
        if (JDateComponentListener.componentShownAction != null) {
            JDateComponentListener.componentShownAction.accept(e);
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        if (JDateComponentListener.componentHiddenAction != null) {
            JDateComponentListener.componentHiddenAction.accept(e);
        }
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
