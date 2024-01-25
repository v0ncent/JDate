package JDate.JDateFramework.Listeners;

import java.util.EventObject;
import java.util.function.Consumer;

public final class ListenerUtil {
    /**
     * Checks if a consumer has been initialized and accepts the passed param to call function on.
     * @param consumer Consumer to check is null.
     * @param toAccept Parameter to accept into consumer.
     * @param <T> EventObject and its subtypes
     * @see EventObject
     * @see Consumer
     */
    // so I don't haft to rewrite all these fucking if statements
    public static <T extends EventObject> void acceptIfNotNull(Consumer<T> consumer, T toAccept) {
        if (consumer == null) {
            return;
        }
        consumer.accept(toAccept);
    }
}
