package io.github.v0ncent;

import javax.swing.*;

public final class WindowUtil {
    /**
     * Opens a JOption pane window and displays the given error message.
     * @param text Error message.
     */
    public static void showErrorWindow(String text) {
        JOptionPane.showMessageDialog(new JFrame(), text, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
