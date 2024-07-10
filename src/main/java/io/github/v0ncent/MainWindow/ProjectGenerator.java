package io.github.v0ncent.MainWindow;

import io.github.v0ncent.Constants;
import io.github.v0ncent.WindowUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class ProjectGenerator extends JPanel implements ActionListener {
    private final JTextField filePath = new JTextField(20);
    private final JTextField nameField = new JTextField(20);

    public ProjectGenerator() {
        JLabel projectName = new JLabel(Constants.ProjectGeneratorConstants.PROJECT_NAME_LABEL_TEXT);

        nameField.setText(Constants.ProjectGeneratorConstants.PROJECT_NAME_FIELD_PLACEHOLDER_TEXT);

        add(projectName);
        add(nameField);

        add(new JLabel(Constants.ProjectGeneratorConstants.PROJECT_LOCATION_LABEL_TEXT));
        add(filePath);

        JButton choose = new JButton(Constants.ProjectGeneratorConstants.PROJECT_LOCATION_BUTTON_TEXT);
        add(choose);

        choose.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            WindowUtil.showErrorWindow( "No Directory Chosen");
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        filePath.setText(selectedFile.getAbsolutePath());

        // check project name before genning project
        if (nameField.getText() == null ||
                Objects.equals(nameField.getText(), Constants.ProjectGeneratorConstants.PROJECT_NAME_FIELD_PLACEHOLDER_TEXT)
                || nameField.getText().isEmpty()) {

            WindowUtil.showErrorWindow( "You must set a name for your project!");
            return;
        }

        // Create a new project file or directory
        File projectDirectory = new File(selectedFile, nameField.getText());

        if (projectDirectory.exists()) {
            WindowUtil.showErrorWindow( "There is an existing project at " + projectDirectory.getAbsolutePath());
            return;
        }

        if (projectDirectory.mkdirs()) {

            try {
                generateProject(projectDirectory.getAbsolutePath());
            } catch (IOException ex) {
                WindowUtil.showErrorWindow( "Error generating the required project content.");
            }

            WindowUtil.showErrorWindow( "project generated at: " + projectDirectory.getAbsolutePath());
        } else {
            WindowUtil.showErrorWindow( "Error generating the project.");
        }

    }

    private void generateProject(String path) throws IOException {
        // generate folders
        File assetsDirectory = new File(path, Constants.FileContent.ASSETS_DIRECTORY_NAME);
        File musicDirectory = new File(path, Constants.FileContent.MUSIC_DIRECTORY_NAME);
        File savesDirectory = new File(path, Constants.FileContent.SAVES_DIRECTORY_NAME);
        File srcFolder = new File(path, Constants.FileContent.SRC_DIRECTORY_NAME);

        FileWriter writer = new FileWriter(path + "/" + Constants.FileContent.LICENSE_FILE_NAME);
        writer.write(Constants.FileContent.LICENSE_CONTENT);
        writer.close();

        if (!assetsDirectory.mkdirs() || ! musicDirectory.mkdirs() || !savesDirectory.mkdirs() || !srcFolder.mkdirs()) {{
            WindowUtil.showErrorWindow( "Error generating the required project content.");
            return;
        }}

        // gen src content
        File scriptsDirectory = new File(path + "/src", Constants.FileContent.SCRIPTS_DIRECTORY_NAME);

        // write gameJson content
        writer = new FileWriter(path + "/src/" + Constants.FileContent.GAME_FILE_NAME);

        writer.write(Constants.FileContent.GAME_FILE_CONTENT);

        writer.close();

        if (!scriptsDirectory.mkdirs()) {
            WindowUtil.showErrorWindow( "Error generating the required project content.");
        }
    }
}