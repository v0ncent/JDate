package io.github.v0ncent.Engine.JDateProject;

import io.github.v0ncent.Constants;
import io.github.v0ncent.WindowUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * An in code representation of an actual JDate Project.
 */
public class JDateProject {
    private final File[] assets;
    private final File[] music;
    private final File[] saves;
    private final File[] src;
    private final File[] scripts;

    private final File gameJSONasFile;
    private final GameJSON gameJSON;

    private final File functionsAsFile;
    private final Class<?> functions;

    private final File[] otherFiles;

    private final File projectDirectory;

    private final String projectName;

    private boolean isWorkable;

    public JDateProject(File[] assets, File[] music, File[] saves, File[] src, File[] scripts, File gameJSONasFile, GameJSON gameJSON, File functionsAsFile, Class<?> functions, File[] otherFiles, File projectDirectory) throws Exception {
        this.assets = assets;
        this.music = music;
        this.saves = saves;
        this.src = src;
        this.scripts = scripts;
        this.gameJSONasFile = gameJSONasFile;
        this.gameJSON = gameJSON;
        this.functionsAsFile = functionsAsFile;
        this.functions = functions;
        this.otherFiles = otherFiles;
        this.projectDirectory = projectDirectory;

        if (this.validateFiles().equals(Constants.StatusCodes.FileValidationCodes.FILE_VALIDATION_FAILED)) {
            this.projectName = null;
            setWorkable(false);
        } else {
            this.projectName = this.gameJSON.name();
            setWorkable(true);
        }
    }

    private String validateFiles() {
        final String validateGameJsonResult = validateGameJSON();

        if (!Objects.equals(validateGameJsonResult, Constants.StatusCodes.GameJsonValidationCodes.GAME_JSON_OK)) {
            WindowUtil.showErrorWindow("Game JSON validation failed: " + validateGameJsonResult);
            return Constants.StatusCodes.FileValidationCodes.FILE_VALIDATION_FAILED;
        }

        return Constants.StatusCodes.FileValidationCodes.FILE_VALIDATION_OK;
    }

    private String validateGameJSON() {
        // validate starting script
        // all the cases I can think of.
        if (getGameJSON().startingScript() == null ||
                !getGameJSON().startingScript().isFile() ||
                !getGameJSON().startingScript().exists() ||
                getGameJSON().startingScript().isDirectory() ||
                !getGameJSON().startingScript().getName().endsWith(Constants.FileContent.JDATE_SCRIPT_EXTENSION)
        ) {
            return Constants.StatusCodes.GameJsonValidationCodes.GAME_JSON_BAD_SCRIPT;
        }

        // validate name
        // all the cases I can think of.
        if (getGameJSON().name() == null ||
                getGameJSON().name().isEmpty()
        ){
            return Constants.StatusCodes.GameJsonValidationCodes.GAME_JSON_BAD_NAME;
        }

        return Constants.StatusCodes.GameJsonValidationCodes.GAME_JSON_OK;
    }

    public File[] getAssets() {
        return assets;
    }

    public File[] getMusic() {
        return music;
    }

    public File[] getSaves() {
        return saves;
    }

    public File[] getSrc() {
        return src;
    }

    public File[] getScripts() {
        return scripts;
    }

    public File getGameJSONasFile() {
        return gameJSONasFile;
    }

    public GameJSON getGameJSON() {
        return gameJSON;
    }

    public File getFunctionsAsFile() {
        return functionsAsFile;
    }

    public Class<?> getFunctions() {
        return functions;
    }

    public String getProjectName() {
        return projectName;
    }

    public File[] getOtherFiles() {
        return otherFiles;
    }

    public File getProjectDirectory() {
        return projectDirectory;
    }

    public boolean isWorkable() {
        return isWorkable;
    }

    public void setWorkable(boolean workable) {
        isWorkable = workable;
    }

    @Override
    public String toString() {
        return "JDateProject{\n" +
                "    assets=" + Arrays.toString(assets) + ",\n" +
                "    music=" + Arrays.toString(music) + ",\n" +
                "    saves=" + Arrays.toString(saves) + ",\n" +
                "    src=" + Arrays.toString(src) + ",\n" +
                "    scripts=" + Arrays.toString(scripts) + ",\n" +
                "    gameJSONasFile=" + gameJSONasFile + ",\n" +
                "    gameJSON=" + gameJSON + ",\n" +
                "    functionsAsFile=" + functionsAsFile + ",\n" +
                "    functions=" + functions + ",\n" +
                "    otherFiles=" + Arrays.toString(otherFiles) + ",\n" +
                "    projectDirectory=" + projectDirectory + ",\n" +
                "    projectName='" + projectName + '\'' + "\n" +
                '}';
    }
}
