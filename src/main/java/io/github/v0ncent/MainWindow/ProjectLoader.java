package io.github.v0ncent.MainWindow;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.v0ncent.Constants;
import io.github.v0ncent.Engine.JDateEngine;
import io.github.v0ncent.Engine.JDateProject.GameJSON;
import io.github.v0ncent.Engine.JDateProject.JDateProject;
import io.github.v0ncent.Engine.Util.JDateProjectUtil;
import io.github.v0ncent.WindowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;

/**
 * The project loader section of the MainWindow's UI.
 */
public class ProjectLoader extends JPanel implements ActionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectLoader.class);

    // The list of elements at the end when it's done looking at all the files
    private final DefaultListModel<String> listModel;

    private final JProgressBar progressBar;

    public ProjectLoader() {
        setLayout(new BorderLayout());

        // the actual part where you are choosing the directory
        JPanel inputPanel = new JPanel();
        JButton chooseButton = new JButton(Constants.ProjectLoaderConstants.CHOOSE_BUTTON_TEXT);
        progressBar = new JProgressBar();

        inputPanel.add(chooseButton);
        inputPanel.add(progressBar);

        listModel = new DefaultListModel<>();

        // scroll pane is if its long af you can get a scroll bar
        JList<String> fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        chooseButton.addActionListener(this);
    }

    /**
     * Loads the given directory into the DirectoryLoader subroutine.
     * @param directory Chosen directory.
     */
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

        // if it's a valid file chosen (this case a directory)
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            loadDirectory(selectedDirectory);
        }
    }

    /**
     * Subroutine for loading directory contents.
     */
    private class DirectoryLoader extends SwingWorker<Void, String> {
        private final File directory;

        // logical object for determining if we are given a valid project or not
        private final JDateProjectUtil project = new JDateProjectUtil();

        // project files
        private final HashMap<String, Object> projectFiles = new HashMap<>();

        public DirectoryLoader(File directory) {
            this.directory = directory;
        }

        @Override
        protected Void doInBackground() {
            loadDirectoryContents(directory, "-");
            return null;
        }

        /**
         * Loops through all contents of the directory and increases progress bar whilst doing so.
         * @param directory Chosen directory to load contents of.
         * @param indent How the file tree should be spaced when listed in UI.
         */
        private void loadDirectoryContents(File directory, String indent) {
            // loop through all the files and slowly increase progress bar as you do :0
            // check if we have needed JDate project files too
            File[] files = directory.listFiles();

            if (files != null) {
                setProgress(0);

                int totalFiles = files.length;
                int filesProcessed = 0;

                for (File file : files) {
                    publish(indent + file.getName());

                    // check if it's a JDate necessary file and cache the files to be passed to our engine.
                    switch (file.getName()) {
                        case Constants.FileContent.ASSETS_DIRECTORY_NAME -> {
                            project.setHasAssetsFolder(true);
                            projectFiles.put(Constants.FileContent.ASSETS_DIRECTORY_NAME, file.listFiles());
                        }
                        case Constants.FileContent.MUSIC_DIRECTORY_NAME -> {
                            project.setHasMusicFolder(true);
                            projectFiles.put(Constants.FileContent.MUSIC_DIRECTORY_NAME, file.listFiles());
                        }
                        case Constants.FileContent.SAVES_DIRECTORY_NAME -> {
                            project.setHasSavesFolder(true);
                            projectFiles.put(Constants.FileContent.SAVES_DIRECTORY_NAME, file.listFiles());
                        }
                        case Constants.FileContent.SRC_DIRECTORY_NAME -> {
                            project.setHasSRCFolder(true);
                            projectFiles.put(Constants.FileContent.SRC_DIRECTORY_NAME, file.listFiles());
                        }
                        case Constants.FileContent.SCRIPTS_DIRECTORY_NAME -> {
                            project.setSrcFolderHasScriptsFolder(true);
                            projectFiles.put(Constants.FileContent.SCRIPTS_DIRECTORY_NAME, file.listFiles());
                        }
                        case Constants.FileContent.GAME_FILE_NAME -> {
                            project.setSrcContainsGameJson(true);
                            projectFiles.put(Constants.FileContent.GAME_FILE_NAME, file);
                        }
                        case Constants.FileContent.JAVA_FUNCTIONS_FILE_NAME -> {
                            project.setSrcContainsFunctionsJavaFile(true);
                            projectFiles.put(Constants.FileContent.JAVA_FUNCTIONS_FILE_NAME, file);
                        }
                        default -> {}
                    }

                    if (file.isDirectory()) {
                        loadDirectoryContents(file, indent + "|_");
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

        /**
         * Takes a given Java Functions project file and compiles it to byte code, creates output directory for compiled files if it has
         * not been created yet.
         * @param pathToFunctionsFile Path to users Java Functions file.
         */
        private void compileFunctionsFile(String pathToFunctionsFile) {
            // compile java file via java compiler
            final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            // if we haven't compiled a functions file before, create the jawn
            final File compiledResultsDir = new File(Constants.FileContent.COMPILED_FUNCTIONS_CLASS_DIRECTORY);

            if (!compiledResultsDir.exists()) {

                if (!compiledResultsDir.mkdirs()) {
                    WindowUtil.showErrorWindow("Failed to create needed directory " + compiledResultsDir.getAbsolutePath());
                }

            }

            int compilationResult = compiler.run(null, null, null, "-d", Constants.FileContent.COMPILED_FUNCTIONS_CLASS_DIRECTORY, pathToFunctionsFile);

            if (compilationResult != 0) {
                WindowUtil.showErrorWindow("Compilation of Functions Class failed.");
            }
        }

        /**
         * Loads the users Java Functions file into a class object.
         * @param pathToFunctionsFile Path to compiled functions file.
         * @return The functions file loaded into a class object.
         * @see Class
         * @throws MalformedURLException If the passed file path is an invalid url / file path.
         * @throws ClassNotFoundException If the compiled functions file does not exist.
         */
        private Class<?> loadClass(String pathToFunctionsFile) throws MalformedURLException, ClassNotFoundException {
            compileFunctionsFile(pathToFunctionsFile);

            File classFile = new File(Constants.FileContent.COMPILED_FUNCTIONS_CLASS_DIRECTORY);
            URL[] url = {classFile.toURI().toURL()};

            URLClassLoader classLoader = new URLClassLoader(url);
            return classLoader.loadClass(Constants.FileContent.JAVA_FUNCTIONS_FILE_NAME.replace(".java", ""));
        }

        @Override
        protected void done() {
            progressBar.setValue(100);

            // at end of subroutine, if it's a valid jdate project, pass to our engine.
            if (project.isJDateProject()) {

                try {
                    LOGGER.info("{}", new ObjectMapper().readValue((File) projectFiles.get(Constants.FileContent.GAME_FILE_NAME), GameJSON.class).toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    JDateEngine.getInstance().acceptFiles(
                            new JDateProject(
                                    (File[]) projectFiles.get(Constants.FileContent.ASSETS_DIRECTORY_NAME),
                                    (File[]) projectFiles.get(Constants.FileContent.MUSIC_DIRECTORY_NAME),
                                    (File[]) projectFiles.get(Constants.FileContent.SAVES_DIRECTORY_NAME),
                                    (File[]) projectFiles.get(Constants.FileContent.SRC_DIRECTORY_NAME),
                                    (File[]) projectFiles.get(Constants.FileContent.SCRIPTS_DIRECTORY_NAME),
                                    (File) projectFiles.get(Constants.FileContent.GAME_FILE_NAME),
                                    new ObjectMapper().readValue((File) projectFiles.get(Constants.FileContent.GAME_FILE_NAME), GameJSON.class),
                                    (File) projectFiles.get(Constants.FileContent.JAVA_FUNCTIONS_FILE_NAME),
                                    // compile users function file to usable class
                                    loadClass(((File) projectFiles.get(Constants.FileContent.JAVA_FUNCTIONS_FILE_NAME)).getAbsolutePath()),
                                    directory
                            )
                    );

                } catch (Exception e ) {
                    WindowUtil.showErrorWindow("Error loading project files: " + e.getMessage());
                    e.printStackTrace();
                }

            } else {
                WindowUtil.showErrorWindow(String.format("Not a valid JDate project, missing: %s", JDateProjectUtil.getMissingElement(project)));
            }
        }

    }

}
