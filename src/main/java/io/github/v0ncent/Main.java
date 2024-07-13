package io.github.v0ncent;

import io.github.v0ncent.MainWindow.ProjectGenerator;
import io.github.v0ncent.MainWindow.ProjectLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * JDate Main Class.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final JFrame MAIN_WINDOW = new JFrame();

    public static void main(String[] args) {
        LOGGER.info("Starting JDate....");
        LOGGER.info(Constants.ApplicationInfo.STARTUP_LOGO);

        MAIN_WINDOW.setName(Constants.JFrameConstants.WINDOW_NAME);
        MAIN_WINDOW.setTitle(Constants.JFrameConstants.WINDOW_NAME);
        MAIN_WINDOW.setSize(Constants.JFrameConstants.WINDOW_SIZE);
        MAIN_WINDOW.setResizable(true);
        MAIN_WINDOW.setDefaultCloseOperation(Constants.JFrameConstants.CLOSE_OPERATION);

        initComponents();

        // NOTE THAT THIS MUST BE CALLED LAST TO ENSURE ALL NEEDED COMPONENTS ARE ADDED BEFORE RENDERING.
        MAIN_WINDOW.setVisible(true);
        MAIN_WINDOW.pack();
    }

    /**
     * Adds components to the application window to be rendered on startup.
     */
    private static void initComponents() {
        final ProjectLoader loader = new ProjectLoader();
        final ProjectGenerator generator = new ProjectGenerator();

        // Adding to a split pane for better space management
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, loader, generator);
        splitPane.setDividerLocation(300); // initial divider location
        MAIN_WINDOW.add(splitPane, BorderLayout.CENTER);
    }

}