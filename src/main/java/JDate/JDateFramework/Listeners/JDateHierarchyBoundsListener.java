package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;

public final class JDateHierarchyBoundsListener implements HierarchyBoundsListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateHierarchyBoundsListener.class);

    private static Consumer<HierarchyEvent> ancestorMovedAction = null;
    private static Consumer<HierarchyEvent> ancestorResizedAction = null;

    @Override
    public void ancestorMoved(HierarchyEvent e) {
        ListenerUtil.acceptIfNotNull(ancestorMovedAction, e);
    }

    @Override
    public void ancestorResized(HierarchyEvent e) {
        ListenerUtil.acceptIfNotNull(ancestorResizedAction, e);
    }

    public static void onFocusGained(Consumer<HierarchyEvent> wantedAction) {
        ancestorMovedAction = wantedAction;
    }

    public static void onFocusLost(Consumer<HierarchyEvent> wantedAction) {
        ancestorResizedAction = wantedAction;
    }
}
