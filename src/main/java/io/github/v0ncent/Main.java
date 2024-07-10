package io.github.v0ncent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final JFrame applicationWindow = new JFrame();

    public static void main(String[] args) {
        LOGGER.info("Starting JDate....");

        applicationWindow.setName(Constants.JFrameConstants.WINDOW_NAME);
        applicationWindow.setTitle(Constants.JFrameConstants.WINDOW_NAME);
        applicationWindow.setVisible(true);
        applicationWindow.setSize(Constants.JFrameConstants.WINDOW_SIZE);
        applicationWindow.setResizable(true);
        applicationWindow.setDefaultCloseOperation(Constants.JFrameConstants.CLOSE_OPERATION);
    }

}