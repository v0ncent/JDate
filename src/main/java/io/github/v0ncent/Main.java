package io.github.v0ncent;

import io.github.v0ncent.MainWindow.ProjectGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final JFrame APPLICATION_WINDOW = new JFrame();

    public static void main(String[] args) {
        LOGGER.info("Starting JDate....");

        APPLICATION_WINDOW.setName(Constants.JFrameConstants.WINDOW_NAME);
        APPLICATION_WINDOW.setTitle(Constants.JFrameConstants.WINDOW_NAME);
        APPLICATION_WINDOW.setSize(Constants.JFrameConstants.WINDOW_SIZE);
        APPLICATION_WINDOW.setResizable(true);
        APPLICATION_WINDOW.setDefaultCloseOperation(Constants.JFrameConstants.CLOSE_OPERATION);

        initComponents();

        // NOTE THAT THIS MUST BE CALLED LAST
        APPLICATION_WINDOW.setVisible(true);
    }

    private static void initComponents() {
        APPLICATION_WINDOW.add(new ProjectGenerator());
    }

}