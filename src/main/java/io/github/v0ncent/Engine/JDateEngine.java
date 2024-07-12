package io.github.v0ncent.Engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * Singleton Instance of JDateEngine Object.
 */
public class JDateEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDateEngine.class);

    // singleton instance of this object, so we aren't fucking
    // with a million instances of a game engine. that wouldn't be good.
    private static JDateEngine jDateEngine;

    private File[] projectDirectoryContents;

    private File projectDirectory;

    private JDateEngine() {

    }

    /**
     * Loads the passed files into the JDateEngine.
     * @param projectDirectoryContent Files to be loaded into the JDateEngine.
     * @param projectDirectory Source Folder of JDate project.
     */
    public void acceptFiles(File[] projectDirectoryContent, File projectDirectory) {
        this.projectDirectoryContents = projectDirectoryContent;
        this.projectDirectory = projectDirectory;

        LOGGER.info("Engine has accepted project: {}", projectDirectory.getAbsolutePath());
        LOGGER.info("Engine has accepted files: {}", Arrays.toString(projectDirectoryContent));
    }

    /**
     * Gets the singleton instance of the JDateEngine Object.
     * @return The singleton instance of the JDateEngine Object.
     */
    public static JDateEngine getInstance() {
        if (jDateEngine == null) {
            jDateEngine = new JDateEngine();
        }
        return jDateEngine;
    }
}
