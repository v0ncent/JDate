package io.github.v0ncent;

import io.github.v0ncent.MainWindow.ProjectGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * JDate Main Class.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final JFrame APPLICATION_WINDOW = new JFrame();

    public static void main(String[] args) {
        LOGGER.info("Starting JDate....");
        LOGGER.info(Constants.ApplicationInfo.STARTUP_LOGO);

        APPLICATION_WINDOW.setName(Constants.JFrameConstants.WINDOW_NAME);
        APPLICATION_WINDOW.setTitle(Constants.JFrameConstants.WINDOW_NAME);
        APPLICATION_WINDOW.setSize(Constants.JFrameConstants.WINDOW_SIZE);
        APPLICATION_WINDOW.setResizable(true);
        APPLICATION_WINDOW.setDefaultCloseOperation(Constants.JFrameConstants.CLOSE_OPERATION);

        initComponents();

        // NOTE THAT THIS MUST BE CALLED LAST TO ENSURE ALL NEEDED COMPONENTS ARE ADDED BEFORE RENDERING.
        APPLICATION_WINDOW.setVisible(true);
    }

    /**
     * Adds components to the application window to be rendered on startup.
     */
    private static void initComponents() {
        APPLICATION_WINDOW.add(new ProjectGenerator());
    }

}