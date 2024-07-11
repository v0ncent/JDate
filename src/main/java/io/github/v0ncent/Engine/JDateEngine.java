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

    private File[] project;

    private JDateEngine() {

    }

    public void acceptFiles(File[] files) {
        this.project = files;

        LOGGER.info("Engine has accepted files: {}", Arrays.toString(files));
    }

    public static JDateEngine getInstance() {
        if (jDateEngine == null) {
            jDateEngine = new JDateEngine();
        }
        return jDateEngine;
    }
}
