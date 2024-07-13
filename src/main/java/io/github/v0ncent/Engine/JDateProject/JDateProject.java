package io.github.v0ncent.Engine.JDateProject;

import io.github.v0ncent.Constants;
import io.github.v0ncent.Exceptions.InvalidProject;
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

    private final File projectDirectory;

    private final String projectName;

    private File startingScript;

    /**
     * @param assets Contents of Assets Directory.
     * @param music Contents of Music Directory.
     * @param saves Contents of Saves Directory.
     * @param src Contents of src Directory.
     * @param scripts Contents of Scripts Directory.
     * @param gameJSONasFile GameJson config file as a File Object.
     * @param gameJSON An instance of the GameJson Object with parsed json contents.
     * @param functionsAsFile Java Functions file as a File Object.
     * @param functions Compiled Functions file as a Class Object.
     * @param projectDirectory Pointer to the project directory.
     * @throws Exception If there are any problems encountered during file compilation.
     */
    public JDateProject(File[] assets, File[] music, File[] saves, File[] src, File[] scripts, File gameJSONasFile, GameJSON gameJSON, File functionsAsFile, Class<?> functions, File projectDirectory) throws Exception {
        this.assets = assets;
        this.music = music;
        this.saves = saves;
        this.src = src;
        this.scripts = scripts;
        this.gameJSONasFile = gameJSONasFile;
        this.gameJSON = gameJSON;
        this.functionsAsFile = functionsAsFile;
        this.functions = functions;
        this.projectDirectory = projectDirectory;

        if (this.validateFiles().equals(Constants.StatusCodes.FileValidationCodes.FILE_VALIDATION_FAILED)) {
            throw new InvalidProject("Failed to validate files of JDateProject.");
        }

        this.projectName = gameJSON.name();
    }

    /**
     * Validates user editable files are able to be processed and used by engine.
     * @return Status code of file validation.
     */
    private String validateFiles() {
        final String validateGameJsonResult = validateGameJSON();

        if (!Objects.equals(validateGameJsonResult, Constants.StatusCodes.GameJsonValidationCodes.GAME_JSON_OK)) {
            WindowUtil.showErrorWindow("Game JSON validation failed: " + validateGameJsonResult);
            return Constants.StatusCodes.FileValidationCodes.FILE_VALIDATION_FAILED;
        }

        return Constants.StatusCodes.FileValidationCodes.FILE_VALIDATION_OK;
    }

    /**
     * Ensures the GameJson config file has valid entries and is able to be used by engine.
     * @return Status code of game json validation.
     */
    private String validateGameJSON() {
        // validate starting script
        // all the cases I can think of.
        if (getGameJSON().startingScript() == null ||
                !getGameJSON().startingScript().split("\\.")[1].equals("jdate") ||
                getGameJSON().startingScript().isEmpty()
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

        // check if starting script exists in scripts folder.
        for (File script : getScripts()) {

            if (script.isFile() && script.getName().equals(getGameJSON().startingScript())) {
                this.startingScript = script;
            }

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

    public File getProjectDirectory() {
        return projectDirectory;
    }

    public File getStartingScript() {
        return startingScript;
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
                "    projectDirectory=" + projectDirectory + ",\n" +
                "    projectName='" + projectName + '\'' + "\n" +
                "    startingScript = '" + startingScript + "'\n" +
                '}';
    }
}
