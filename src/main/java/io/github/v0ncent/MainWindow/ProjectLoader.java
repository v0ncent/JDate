package io.github.v0ncent.MainWindow;

import io.github.v0ncent.Constants;
import io.github.v0ncent.Engine.JDateEngine;
import io.github.v0ncent.Engine.Util.JDateProject;
import io.github.v0ncent.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ProjectLoader extends JPanel implements ActionListener {
    private final DefaultListModel<String> listModel;
    private final JProgressBar progressBar;

    public ProjectLoader() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JButton chooseButton = new JButton("Load Project");
        progressBar = new JProgressBar();

        inputPanel.add(chooseButton);
        inputPanel.add(progressBar);

        listModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        chooseButton.addActionListener(this);
    }

    private void loadDirectory(File directory) {
        listModel.clear();

        if (directory.exists() && directory.isDirectory()) {

            DirectoryLoader loader = new DirectoryLoader(directory);
            loader.execute();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid directory path", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            loadDirectory(selectedDirectory);
        }
    }

    private class DirectoryLoader extends SwingWorker<Void, String> {
        private final File directory;

        // logical object for determining if we are given a valid project or not
        private final JDateProject project = new JDateProject();

        // project files
        private File[] files;

        public DirectoryLoader(File directory) {
            this.directory = directory;
            this.files = directory.listFiles();
        }

        @Override
        protected Void doInBackground() {
            loadDirectoryContents(directory, "");
            return null;
        }

        private void loadDirectoryContents(File directory, String indent) {
            File[] files = directory.listFiles();

            if (files != null) {
                setProgress(0);

                int totalFiles = files.length;
                int filesProcessed = 0;

                for (File file : files) {
                    publish(indent + file.getName());

                    // check if it's a JDate necessary file
                    switch (file.getName()) {
                        case Constants.FileContent.ASSETS_DIRECTORY_NAME -> project.setHasAssetsFolder(true);
                        case Constants.FileContent.MUSIC_DIRECTORY_NAME -> project.setHasMusicFolder(true);
                        case Constants.FileContent.SAVES_DIRECTORY_NAME -> project.setHasSavesFolder(true);
                        case Constants.FileContent.SRC_DIRECTORY_NAME -> project.setHasSRCFolder(true);
                        case Constants.FileContent.SCRIPTS_DIRECTORY_NAME -> project.setSrcFolderHasScriptsFolder(true);
                        case Constants.FileContent.GAME_FILE_NAME -> project.setSrcContainsGameJson(true);
                        default -> {}
                    }

                    if (file.isDirectory()) {
                        loadDirectoryContents(file, indent + "  ");
                    }

                    filesProcessed++;
                    setProgress((int) ((double) filesProcessed / totalFiles * 100));
                }

            }
        }

        protected void process(List<String> chunks) {
            for (String name : chunks) {
                listModel.addElement(name);
            }
        }

        @Override
        protected void done() {
            progressBar.setValue(100);

            // at end of subroutine, if it's a valid jdate project, pass to our engine.
            if (project.isJDateProject()) {
                JDateEngine.getInstance().acceptFiles(files);
            } else {
                WindowUtil.showErrorWindow(String.format("Not a valid JDate project, missing: %s", JDateProject.getMissingElement(project)));
            }
        }

    }

}
