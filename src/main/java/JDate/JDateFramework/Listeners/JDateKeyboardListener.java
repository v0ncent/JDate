package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

public final class JDateKeyboardListener implements KeyListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateKeyboardListener.class);

    private static Consumer<KeyEvent> keyTypedAction = null;
    private static Consumer<KeyEvent> keyPressedAction = null;
    private static Consumer<KeyEvent> keyReleasedAction = null;

    @Override
    public void keyTyped(KeyEvent e) {
        ListenerUtil.acceptIfNotNull(keyTypedAction, e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ListenerUtil.acceptIfNotNull(keyPressedAction, e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ListenerUtil.acceptIfNotNull(keyReleasedAction, e);
    }

    public static void onKeyTyped(Consumer<KeyEvent> wantedAction) {
        keyTypedAction = wantedAction;
    }

    public static void onKeyPressed(Consumer<KeyEvent> wantedAction) {
        keyPressedAction = wantedAction;
    }

    public static void onKeyReleased(Consumer<KeyEvent> wantedAction) {
        keyReleasedAction = wantedAction;
    }
}
