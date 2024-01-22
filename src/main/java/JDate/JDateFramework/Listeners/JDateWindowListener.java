package JDate.JDateFramework.Listeners;

import JDate.JDateFramework.JDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public final class JDateWindowListener implements WindowListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateWindowListener.class);

    // runs when window is created and opened
    @Override
    public void windowOpened(WindowEvent e) {
        LOGGER.info("Opening JDate window.");
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
        LOGGER.info("Window Closed Successfully.");
    }

    // runs when window is minimized
    @Override
    public void windowIconified(WindowEvent e) {
        LOGGER.info("Window Iconified.");
    }

    // runs when window is unminimized
    @Override
    public void windowDeiconified(WindowEvent e) {
        LOGGER.info("Window Deiconified.");
    }

    // runs when window comes into focus
    @Override
    public void windowActivated(WindowEvent e) {
        LOGGER.info("Window Activated");
    }

    // runs when window is minimized / unfocused
    @Override
    public void windowDeactivated(WindowEvent e) {
        LOGGER.info("Window Deactivated");
    }
}
