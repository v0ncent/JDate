package io.github.v0ncent;

import javax.swing.*;

public final class WindowUtil {
    public static void showErrorWindow(String text) {
        JOptionPane.showMessageDialog(new JFrame(), text, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
