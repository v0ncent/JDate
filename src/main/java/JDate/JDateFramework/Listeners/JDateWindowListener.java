package JDate.JDateFramework.Listeners;

import JDate.JDateFramework.JDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.function.Consumer;

public final class JDateWindowListener implements WindowListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateWindowListener.class);

    private static Consumer<ComponentEvent> windowIconifiedAction = null;

    // runs when window is created and opened
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // runs when close button is hit
    @Override
    public void windowClosing(WindowEvent e) {
        LOGGER.info("Attempting to close window.");
        JDate.getInstance().setShouldRender(false); // stop TimeLinePlayer from rendering / end game loop.
    }

    // runs when window is closed
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // runs when window is minimized
    @Override
    public void windowIconified(WindowEvent e) {
        if (JDateWindowListener.windowIconifiedAction != null) {
            JDateWindowListener.windowIconifiedAction.accept(e);
        }
    }

    // runs when window is unminimized
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // runs when window comes into focus
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // runs when window is minimized / unfocused
    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void mapWindowIconified(Consumer<ComponentEvent> wantedAction) {
        windowIconifiedAction = wantedAction;
    }
}
