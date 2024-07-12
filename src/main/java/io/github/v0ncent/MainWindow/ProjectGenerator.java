package io.github.v0ncent.MainWindow;

import io.github.v0ncent.Constants;
import io.github.v0ncent.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Project generator section of the MainWindow's UI.
 */
public class ProjectGenerator extends JPanel implements ActionListener {
    // The actual text boxes on the UI
    private final JTextField filePath = new JTextField(20);
    private final JTextField nameField = new JTextField(20);

    public ProjectGenerator() {
        // so it's surrounded in a nice box
        setLayout(new BorderLayout());

        // Add a nice title on top of its ui section
        JLabel titleLabel = new JLabel(Constants.ProjectGeneratorConstants.PROJECTGEN_PANEL_TITLE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // makes stuff look really nice, somehow :0
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // this is the text next to the text field
        JLabel projectNameLabel = new JLabel(Constants.ProjectGeneratorConstants.PROJECT_NAME_LABEL_TEXT);
        inputPanel.add(projectNameLabel);
        inputPanel.add(nameField);

        // ditto but for proj location
        JLabel projectLocationLabel = new JLabel(Constants.ProjectGeneratorConstants.PROJECT_LOCATION_LABEL_TEXT);
        inputPanel.add(projectLocationLabel);
        inputPanel.add(filePath);

        // da buttons
        JButton chooseButton = new JButton(Constants.ProjectGeneratorConstants.PROJECT_LOCATION_BUTTON_TEXT);
        chooseButton.addActionListener(this);
        inputPanel.add(chooseButton);

        add(inputPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // on button clicked, generate project and content at specified path
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            WindowUtil.showErrorWindow("No Directory Chosen");
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        filePath.setText(selectedFile.getAbsolutePath());

        // check project name before generating project
        if (nameField.getText() == null ||
                Objects.equals(nameField.getText(), Constants.ProjectGeneratorConstants.PROJECT_NAME_FIELD_PLACEHOLDER_TEXT)
                || nameField.getText().isEmpty()) {

            WindowUtil.showErrorWindow("You must set a name for your project!");
            return;
        }

        // Create a new project file or directory
        File projectDirectory = new File(selectedFile, nameField.getText());

        if (projectDirectory.exists()) {
            WindowUtil.showErrorWindow("There is an existing project at " + projectDirectory.getAbsolutePath());
            return;
        }

        // create directory and populate project with content
        if (projectDirectory.mkdirs()) {
            try {
                generateProject(projectDirectory.getAbsolutePath());
            } catch (IOException ex) {
                WindowUtil.showErrorWindow("Error generating the required project content.");
            }

            WindowUtil.showInfoWindow("project generated at: " + projectDirectory.getAbsolutePath());
        } else {
            WindowUtil.showErrorWindow("Error generating the project.");
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

        // check that they actually generated
        if (!assetsDirectory.mkdirs() || !musicDirectory.mkdirs() || !savesDirectory.mkdirs() || !srcFolder.mkdirs()) {
            WindowUtil.showErrorWindow("Error generating the required project content.");
            return;
        }

        // generate src content
        File scriptsDirectory = new File(path + "/src", Constants.FileContent.SCRIPTS_DIRECTORY_NAME);

        // write gameJson content
        writer = new FileWriter(path + "/src/" + Constants.FileContent.GAME_FILE_NAME);
        writer.write(Constants.FileContent.GAME_FILE_CONTENT);

        // create and write java file
        writer = new FileWriter(path + "/src/" + Constants.FileContent.JAVA_FUNCTIONS_FILE_NAME);
        writer.write(Constants.FileContent.JAVA_FUNCTIONS_FILE_CONTENT);

        writer.close();

        // check if directory was generated
        if (!scriptsDirectory.mkdirs()) {
            WindowUtil.showErrorWindow("Error generating the required project content.");
        }
    }
}


