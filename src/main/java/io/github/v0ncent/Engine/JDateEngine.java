package io.github.v0ncent.Engine;

import io.github.v0ncent.Engine.JDateProject.JDateProject;
import io.github.v0ncent.WindowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Singleton Instance of JDateEngine Object.
 */
public class JDateEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDateEngine.class);

    // singleton instance of this object, so we aren't fucking
    // with a million instances of a game engine. that wouldn't be good.
    private static JDateEngine jDateEngine;

    public boolean hasProject = false;

    // this is the project files processed into one nice object, engine things can now happen with this object.
    private static JDateProject project;

    private JDateEngine() {

    }

    /**
     * Loads the passed files into the JDateEngine.
     * @param project An instance of the JDate Object populated with user passed project content.
     */
    public void acceptFiles(JDateProject project) {
        // populate JDateProject from files passed.
        JDateEngine.project = project;
        this.hasProject = true;

        LOGGER.info("Engine has accepted project: {}", project.getProjectDirectory());
        LOGGER.info("Engine has accepted files: {}", project);

        this.run();
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

    public static JDateProject getProject() {
        return project;
    }

    private void run() {
        if (!hasProject) {
            LOGGER.error("Engine does not currently have a project loaded!");
        }

        try {
            JDateInterpreter.getInstance().interpret(project.getStartingScript());
        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot find starting script. {}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("ERROR parsing line data of script. {}", e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException e) {
            LOGGER.error("ERROR invoking lines of script. {}", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            WindowUtil.showErrorWindow(e.getMessage());
        }
    }
}
