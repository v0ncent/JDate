package io.github.v0ncent.Engine;

import io.github.v0ncent.Engine.JDateProject.JDateProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton Instance of JDateEngine Object.
 */
public class JDateEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDateEngine.class);

    // singleton instance of this object, so we aren't fucking
    // with a million instances of a game engine. that wouldn't be good.
    private static JDateEngine jDateEngine;

    // this is the project files processed into one nice object, engine things can now happen with this object.
    private JDateProject project;

    private JDateEngine() {

    }

    /**
     * Loads the passed files into the JDateEngine.
     * @param project An instance of the JDate Object populated with user passed project content.
     */
    public void acceptFiles(JDateProject project) {
        // populate JDateProject from files passed.
        this.project = project;

        LOGGER.info("Engine has accepted project: {}", project.getProjectDirectory());
        LOGGER.info("Engine has accepted files: {}", project);
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
