package JDate.JDateFramework.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Consumer;

public class JDateFocusListener implements FocusListener {
    private final Logger LOGGER = LoggerFactory.getLogger(JDateFocusListener.class);

    private static Consumer<FocusEvent> focusGainedAction = null;
    private static Consumer<FocusEvent> focusLostAction = null;

    @Override
    public void focusGained(FocusEvent e) {
        if (JDateFocusListener.focusGainedAction != null) {
            JDateFocusListener.focusGainedAction.accept(e);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (JDateFocusListener.focusLostAction != null) {
            JDateFocusListener.focusGainedAction.accept(e);
        }
    }

    public static void onFocusGained(Consumer<FocusEvent> wantedAction) {
        focusGainedAction = wantedAction;
    }

    public static void onFocusLost(Consumer<FocusEvent> wantedAction) {
        focusLostAction = wantedAction;
    }
}
