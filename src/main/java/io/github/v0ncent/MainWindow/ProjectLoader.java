package io.github.v0ncent.MainWindow;

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

        // Title Label
        JLabel titleLabel = new JLabel("Load Project");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Input Panel with choose button and progress bar
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton chooseButton = new JButton("Choose Directory");
        progressBar = new JProgressBar();

        inputPanel.add(chooseButton);
        inputPanel.add(progressBar);

        // File List
        listModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        // Adding components to the main panel
        add(inputPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

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

        public DirectoryLoader(File directory) {
            this.directory = directory;
        }

        @Override
        protected Void doInBackground() {
            loadDirectoryContents(directory, "");
            return null;
        }

        private void loadDirectoryContents(File directory, String indent) {
            File[] files = directory.listFiles();
            if (files != null) {
                Arrays.sort(files);
                setProgress(0);
                int totalFiles = files.length;
                int filesProcessed = 0;
                for (File file : files) {
                    publish(indent + file.getName());
                    if (file.isDirectory()) {
                        loadDirectoryContents(file, indent + "  ");
                    }
                    filesProcessed++;
                    setProgress((int) ((double) filesProcessed / totalFiles * 100));
                }
            }
        }

        @Override
        protected void process(List<String> chunks) {
            for (String name : chunks) {
                listModel.addElement(name);
            }
        }

        @Override
        protected void done() {
            progressBar.setValue(100);
        }
    }
}


