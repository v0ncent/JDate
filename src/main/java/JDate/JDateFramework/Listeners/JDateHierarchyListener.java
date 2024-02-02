package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.function.Consumer;

public final class JDateHierarchyListener implements HierarchyListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateHierarchyListener.class);

    private static Consumer<HierarchyEvent> hierarchyEventAction = null;

    @Override
    public void hierarchyChanged(HierarchyEvent e) {
        ListenerUtil.acceptIfNotNull(hierarchyEventAction, e);
    }

    public static void onHierarchyChanged(Consumer<HierarchyEvent> wantedAction) {
        hierarchyEventAction = wantedAction;
    }
}
