package JDate.JDateFramework.Listeners;

import JDate.JDateFramework.JDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.function.Consumer;

public final class JDateWindowListener implements WindowListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateWindowListener.class);

    private static Consumer<WindowEvent> windowOpenedAction = null;
    private static Consumer<WindowEvent> windowClosingAction = null;
    private static Consumer<WindowEvent> windowClosedAction = null;
    private static Consumer<WindowEvent> windowIconifiedAction = null;
    private static Consumer<WindowEvent> windowDeIconifiedAction = null;
    private static Consumer<WindowEvent> windowActivatedAction = null;
    private static Consumer<WindowEvent> windowDeActivatedAction = null;

    // runs when window is created and opened
    @Override
    public void windowOpened(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowOpenedAction, e);
    }

    // runs when close button is hit
    @Override
    public void windowClosing(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowClosingAction, e);
    }

    // runs when window is closed
    @Override
    public void windowClosed(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowClosedAction, e);

        LOGGER.info("Attempting to end JDate Timeline loop...");
        JDate.getInstance().setShouldRender(false); // stop TimeLinePlayer from rendering / end game loop.
    }

    // runs when window is minimized
    @Override
    public void windowIconified(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowIconifiedAction, e);
    }

    // runs when window is unminimized
    @Override
    public void windowDeiconified(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowDeIconifiedAction, e);
    }

    // runs when window comes into focus
    @Override
    public void windowActivated(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowActivatedAction, e);
    }

    // runs when window is minimized / unfocused
    @Override
    public void windowDeactivated(WindowEvent e) {
        ListenerUtil.acceptIfNotNull(windowDeActivatedAction, e);
    }

    public static void onWindowOpenedAction(Consumer<WindowEvent> wantedAction) {
        windowOpenedAction = wantedAction;
    }

    public static void onWindowClosing(Consumer<WindowEvent> wantedAction) {
        windowClosingAction = wantedAction;
    }

    public static void onWindowClosedAction(Consumer<WindowEvent> wantedAction) {
        windowClosedAction = wantedAction;
    }

    public static void onMapWindowIconified(Consumer<WindowEvent> wantedAction) {
        windowIconifiedAction = wantedAction;
    }

    public static void onWindowDeIconifiedAction(Consumer<WindowEvent> wantedAction) {
        windowDeIconifiedAction = wantedAction;
    }

    public static void onWindowActivatedAction(Consumer<WindowEvent> wantedAction) {
        windowActivatedAction = wantedAction;
    }

    public static void onWindowDeActivatedAction(Consumer<WindowEvent> wantedAction) {
        windowDeActivatedAction = wantedAction;
    }
}
